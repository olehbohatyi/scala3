package module13projects

import scala.collection.immutable.ListMap

// Summary: This mini-project combines OOP service design with FP-style immutable domain data.
// Transactions are modeled as immutable case classes, while the tracker is a stateful service.
// Validation uses Either instead of exceptions for domain-level failures.
// Reporting is implemented with collection pipelines such as groupMapReduce and sortBy.
// This is a good interview example of "mutable shell, immutable core" design.

@main def expenseTrackerApp(): Unit =

    // =========================================================
    // Domain model
    // =========================================================

    case class Transaction(id: String, category: String, amount: BigDecimal, note: String)

    sealed trait BudgetStatus:
        def label: String

    object BudgetStatus:
        case object UnderLimit extends BudgetStatus:
            val label: String = "under-limit"

        case class OverLimit(extra: BigDecimal) extends BudgetStatus:
            def label: String = s"over-limit by $extra"

    trait BudgetPolicy:
        def evaluate(spent: BigDecimal, limit: BigDecimal): BudgetStatus

    object DefaultBudgetPolicy extends BudgetPolicy:
        def evaluate(spent: BigDecimal, limit: BigDecimal): BudgetStatus =
            if spent <= limit then BudgetStatus.UnderLimit
            else BudgetStatus.OverLimit(spent - limit)

    // =========================================================
    // Stateful application service
    // =========================================================

    class ExpenseTracker(val monthlyLimit: BigDecimal, val policy: BudgetPolicy):
        private var transactions: Vector[Transaction] = Vector.empty

        def add(tx: Transaction): Either[String, Unit] =
            if tx.id.trim.isEmpty then Left("transaction id must be non-empty")
            else if tx.category.trim.isEmpty then Left("category must be non-empty")
            else if tx.amount <= 0 then Left("amount must be positive")
            else if tx.note.trim.isEmpty then Left("note must be non-empty")
            else
                transactions = transactions :+ tx
                Right(())

        def all: Vector[Transaction] = transactions

        def totalSpent: BigDecimal = transactions.map(_.amount).sum

        def status: BudgetStatus = policy.evaluate(totalSpent, monthlyLimit)

        def byCategory: ListMap[String, BigDecimal] =
            val totals = transactions.groupMapReduce(_.category)(_.amount)(_ + _)
            ListMap.from(totals.toList.sortBy { case (_, amount) => -amount.toDouble })

        def highestExpense: Option[Transaction] =
            transactions.sortBy(_.amount).lastOption

    // =========================================================
    // Pure helper functions
    // =========================================================

    def averageExpense(items: Seq[Transaction]): BigDecimal =
        if items.isEmpty then BigDecimal(0)
        else items.map(_.amount).sum / BigDecimal(items.size)

    def renderSummary(tracker: ExpenseTracker): String =
        val topLine = tracker.highestExpense
            .map(tx => s"top=${tx.category}:${tx.amount}")
            .getOrElse("top=none")
        s"total=${tracker.totalSpent}, avg=${averageExpense(tracker.all)}, status=${tracker.status.label}, $topLine"

    // =========================================================
    // Demo scenario
    // =========================================================

    val tracker = new ExpenseTracker(BigDecimal(500), DefaultBudgetPolicy)

    val seedData = List(
        Transaction("t-1", "Books", BigDecimal(49.90), "Scala interview guide"),
        Transaction("t-2", "Tools", BigDecimal(19.99), "Domain modeling notes"),
        Transaction("t-3", "Courses", BigDecimal(149.00), "Advanced FP course"),
        Transaction("t-4", "Coffee", BigDecimal(12.50), "Deep work session")
    )

    seedData.foreach(tx => println(tracker.add(tx)))
    println(tracker.add(Transaction("t-5", "", BigDecimal(10), "invalid entry")))

    println(renderSummary(tracker))
    println(tracker.byCategory)
    println(tracker.highestExpense)
