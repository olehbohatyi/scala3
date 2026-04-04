package module13projects

import scala.collection.immutable.ListMap

// Summary: This mini-project models a ticket board with workflow states and assignment strategy.
// Domain data is immutable, while the board service owns the mutable collection of tickets.
// A trait isolates assignment logic so different strategies can be swapped in later.
// Status transitions and summary metrics make this a strong systems-design interview example.
// It demonstrates how to keep business rules readable through enums, case classes, and pure helpers.

@main def supportTicketApp(): Unit =

    // =========================================================
    // Domain model
    // =========================================================

    enum Priority(val rank: Int):
        case Low extends Priority(1)
        case Medium extends Priority(2)
        case High extends Priority(3)
        case Critical extends Priority(4)

    enum Status:
        case Open, InProgress, Resolved, Closed

    case class Ticket(
        id: String,
        title: String,
        priority: Priority,
        assignee: String,
        status: Status,
        tags: Set[String]
    )

    trait AssignmentStrategy:
        def assign(title: String, priority: Priority): String

    class RoundRobinAssignmentStrategy(agents: Vector[String]) extends AssignmentStrategy:
        require(agents.nonEmpty, "agents must be non-empty")
        private var nextIndex: Int = 0

        def assign(title: String, priority: Priority): String =
            val agent = agents(nextIndex % agents.size)
            nextIndex += 1
            agent

    // =========================================================
    // Application service
    // =========================================================

    class TicketBoard(val strategy: AssignmentStrategy):
        private var tickets: Vector[Ticket] = Vector.empty

        def create(id: String, title: String, priority: Priority, tags: Set[String]): Either[String, Ticket] =
            if id.trim.isEmpty then Left("ticket id must be non-empty")
            else if title.trim.isEmpty then Left("title must be non-empty")
            else if tickets.exists(_.id == id) then Left(s"duplicate ticket id: $id")
            else
                val ticket = Ticket(id, title, priority, strategy.assign(title, priority), Status.Open, tags)
                tickets = tickets :+ ticket
                Right(ticket)

        def changeStatus(id: String, newStatus: Status): Either[String, Ticket] =
            tickets.indexWhere(_.id == id) match
                case -1 => Left(s"unknown ticket id: $id")
                case index =>
                    val updated = tickets(index).copy(status = newStatus)
                    tickets = tickets.updated(index, updated)
                    Right(updated)

        def all: Vector[Ticket] = tickets

        def summaryByStatus: ListMap[Status, Int] =
            val ordered = Status.values.toList.map(status => status -> tickets.count(_.status == status))
            ListMap.from(ordered)

    // =========================================================
    // Pure helpers
    // =========================================================

    def openCriticalTickets(items: Seq[Ticket]): Seq[Ticket] =
        items.filter(ticket => ticket.status != Status.Closed && ticket.priority == Priority.Critical)

    def assigneeLoad(items: Seq[Ticket]): Map[String, Int] =
        items.groupMapReduce(_.assignee)(_ => 1)(_ + _)

    // =========================================================
    // Demo scenario
    // =========================================================

    val board = new TicketBoard(new RoundRobinAssignmentStrategy(Vector("Nina", "Omar", "Priya")))

    println(board.create("tic-1", "Login page returns 500", Priority.Critical, Set("backend", "auth")))
    println(board.create("tic-2", "Improve dashboard labels", Priority.Low, Set("frontend", "ux")))
    println(board.create("tic-3", "Payment retries stuck", Priority.High, Set("payments", "ops")))

    println(board.changeStatus("tic-1", Status.InProgress))
    println(board.changeStatus("tic-2", Status.Resolved))

    println(board.summaryByStatus)
    println(openCriticalTickets(board.all))
    println(assigneeLoad(board.all))
