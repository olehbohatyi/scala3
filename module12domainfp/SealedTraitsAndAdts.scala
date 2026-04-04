package module12domainfp

// Summary: A sealed trait defines a closed family of subtypes in the same file.
// This enables Algebraic Data Types (ADTs): product types + sum types.
// Product type = case class combining fields; sum type = sealed trait with alternatives.
// ADTs make illegal states harder to represent and improve exhaustive pattern matching.
// This is the core technique for robust functional domain modeling in Scala.

@main def sealedTraitsAndAdts(): Unit =

    // =========================================================
    // Sum type for payment status
    // =========================================================

    sealed trait PaymentStatus
    object PaymentStatus:
        case object Pending extends PaymentStatus
        case object Paid extends PaymentStatus
        case class Failed(reason: String) extends PaymentStatus
        case object Refunded extends PaymentStatus

    import PaymentStatus.*

    def render(status: PaymentStatus): String = status match
        case Pending        => "payment is pending"
        case Paid           => "payment completed"
        case Failed(reason) => s"payment failed: $reason"
        case Refunded       => "payment refunded"

    val statuses = List(Pending, Paid, Failed("insufficient funds"), Refunded)
    statuses.foreach(s => println(render(s)))

    // =========================================================
    // Product type + sum type together = ADT domain model
    // =========================================================

    case class Product(id: String, price: BigDecimal)
    case class Customer(id: String, email: String)

    sealed trait Order
    object Order:
        case class Draft(id: String, customer: Customer, items: List[Product]) extends Order
        case class Submitted(id: String, customer: Customer, items: List[Product], submittedAt: String) extends Order
        case class Cancelled(id: String, customer: Customer, reason: String) extends Order

    import Order.*

    def total(order: Order): BigDecimal = order match
        case Draft(_, _, items)           => items.map(_.price).sum
        case Submitted(_, _, items, _)    => items.map(_.price).sum
        case Cancelled(_, _, _)           => BigDecimal(0)

    val customer = Customer("c-1", "student@example.com")
    val items = List(Product("scala-book", BigDecimal(30)), Product("course", BigDecimal(120)))
    val draft: Order = Draft("o-1", customer, items)
    val submitted: Order = Submitted("o-2", customer, items, "2026-04-04T10:00:00")
    val cancelled: Order = Cancelled("o-3", customer, "duplicate request")

    println(total(draft))
    println(total(submitted))
    println(total(cancelled))

    // =========================================================
    // Modeling commands/events with ADTs
    // =========================================================

    sealed trait Command
    object Command:
        case class CreateUser(name: String, email: String) extends Command
        case class ChangeEmail(userId: Long, newEmail: String) extends Command
        case class DeactivateUser(userId: Long) extends Command

    import Command.*

    def handle(command: Command): String = command match
        case CreateUser(name, email)      => s"create user name=$name email=$email"
        case ChangeEmail(userId, newEmail)=> s"change email for $userId to $newEmail"
        case DeactivateUser(userId)       => s"deactivate user $userId"

    println(handle(CreateUser("Alice", "alice@example.com")))
    println(handle(ChangeEmail(1, "new@example.com")))
    println(handle(DeactivateUser(1)))

    // =========================================================
    // Additional: making illegal states unrepresentable
    // =========================================================

    // Better than: case class Payment(cardNumber: Option[String], iban: Option[String], paypal: Option[String])
    // ADT enforces exactly one payment method.
    sealed trait PaymentMethod
    object PaymentMethod:
        case class Card(last4: String) extends PaymentMethod
        case class Wire(ibanSuffix: String) extends PaymentMethod
        case class PayPal(email: String) extends PaymentMethod

    import PaymentMethod.*

    def label(method: PaymentMethod): String = method match
        case Card(last4)      => s"card ****$last4"
        case Wire(suffix)     => s"wire ****$suffix"
        case PayPal(email)    => s"paypal $email"

    List[PaymentMethod](Card("1234"), Wire("6789"), PayPal("pp@example.com"))
        .foreach(m => println(label(m)))
