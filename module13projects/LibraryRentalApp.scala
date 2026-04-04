package module13projects

import java.time.LocalDate
import java.time.temporal.ChronoUnit

// Summary: This mini-project models a small library workflow using immutable domain records.
// A library service coordinates state transitions such as checkout and return.
// Fine calculation is delegated to a policy trait, which makes the design extensible.
// Dates and overdue logic make the example feel realistic for portfolio and interview discussion.
// The code demonstrates workflow modeling, validation, and reporting in one compact example.

@main def libraryRentalApp(): Unit =

    // =========================================================
    // Domain model
    // =========================================================

    enum BookStatus:
        case Available, Loaned

    case class Book(id: String, title: String, author: String, status: BookStatus = BookStatus.Available)
    case class Member(id: String, name: String)
    case class Loan(bookId: String, memberId: String, dueDate: LocalDate)

    trait FinePolicy:
        def calculate(daysLate: Long): BigDecimal

    object DefaultFinePolicy extends FinePolicy:
        def calculate(daysLate: Long): BigDecimal =
            if daysLate <= 0 then BigDecimal(0)
            else BigDecimal(daysLate) * BigDecimal("0.50")

    // =========================================================
    // Application service
    // =========================================================

    class Library(val finePolicy: FinePolicy):
        private var books: Map[String, Book] = Map.empty
        private var loans: Map[String, Loan] = Map.empty

        def addBook(book: Book): Either[String, Unit] =
            if book.id.trim.isEmpty then Left("book id must be non-empty")
            else if books.contains(book.id) then Left(s"duplicate book id: ${book.id}")
            else
                books = books.updated(book.id, book)
                Right(())

        def checkout(bookId: String, member: Member, today: LocalDate, loanDays: Long): Either[String, Unit] =
            books.get(bookId) match
                case None => Left(s"unknown book id: $bookId")
                case Some(book) if book.status == BookStatus.Loaned => Left(s"book already loaned: $bookId")
                case Some(book) =>
                    books = books.updated(bookId, book.copy(status = BookStatus.Loaned))
                    loans = loans.updated(bookId, Loan(bookId, member.id, today.plusDays(loanDays)))
                    Right(())

        def returnBook(bookId: String, returnedAt: LocalDate): Either[String, BigDecimal] =
            (books.get(bookId), loans.get(bookId)) match
                case (Some(book), Some(loan)) =>
                    val rawDaysLate = ChronoUnit.DAYS.between(loan.dueDate, returnedAt)
                    val daysLate = if rawDaysLate > 0 then rawDaysLate else 0L
                    val fine = finePolicy.calculate(daysLate)
                    books = books.updated(bookId, book.copy(status = BookStatus.Available))
                    loans = loans - bookId
                    Right(fine)
                case _ => Left(s"book is not currently loaned: $bookId")

        def inventory: List[Book] = books.values.toList.sortBy(_.title)
        def activeLoans: List[Loan] = loans.values.toList.sortBy(_.dueDate)

    // =========================================================
    // Pure helpers
    // =========================================================

    def availableTitles(items: List[Book]): List[String] =
        items.filter(_.status == BookStatus.Available).map(_.title)

    def loanedTitles(items: List[Book]): List[String] =
        items.filter(_.status == BookStatus.Loaned).map(_.title)

    // =========================================================
    // Demo scenario
    // =========================================================

    val library = new Library(DefaultFinePolicy)
    val member = Member("m-1", "Sara")
    val today = LocalDate.of(2026, 4, 4)

    println(library.addBook(Book("b-1", "Programming in Scala", "Odersky")))
    println(library.addBook(Book("b-2", "Functional Programming in Scala", "Chiusano")))
    println(library.addBook(Book("b-3", "Scala Puzzlers", "Suereth")))

    println(availableTitles(library.inventory))
    println(library.checkout("b-2", member, today, 7))
    println(loanedTitles(library.inventory))
    println(library.activeLoans)
    println(library.returnBook("b-2", today.plusDays(10)))
    println(availableTitles(library.inventory))
