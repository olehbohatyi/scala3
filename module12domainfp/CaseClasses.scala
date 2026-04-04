package module12domainfp

// Summary: case class is Scala's default data modeling tool for immutable domain records.
// It automatically provides constructor params as vals, equals/hashCode, toString, and copy.
// Case classes also support pattern matching and destructuring out of the box.
// Prefer case class for plain immutable data; use regular class for custom mutable behavior.
// In FP-style domain modeling, most entities and value objects are case classes.

@main def caseClasses(): Unit =

    // =========================================================
    // Basic case class
    // =========================================================

    case class User(id: Long, name: String, email: String)

    val alice = User(1L, "Alice", "alice@example.com")
    val alice2 = User(1L, "Alice", "alice@example.com")

    println(alice.id)          // 1
    println(alice.name)        // Alice
    println(alice)             // User(1,Alice,alice@example.com)

    // structural equality — compares field values, not object identity
    println(alice == alice2)   // true

    // =========================================================
    // copy — create a modified version immutably
    // =========================================================

    val renamed = alice.copy(name = "Alice Cooper")
    val changedEmail = alice.copy(email = "alice.cooper@example.com")

    println(alice)
    println(renamed)
    println(changedEmail)

    // original remains unchanged
    println(alice.name)        // Alice

    // =========================================================
    // Case classes are immutable by default
    // =========================================================

    case class Money(amount: BigDecimal, currency: String)

    val price = Money(BigDecimal("19.99"), "EUR")
    val discount = price.copy(amount = price.amount - BigDecimal("5.00"))
    println(price)
    println(discount)

    // =========================================================
    // Destructuring
    // =========================================================

    val User(id, name, email) = alice
    println(s"id=$id, name=$name, email=$email")

    // =========================================================
    // Nested case classes for domain modeling
    // =========================================================

    case class Address(city: String, country: String)
    case class Customer(id: Long, name: String, address: Address)
    case class OrderLine(sku: String, quantity: Int, unitPrice: BigDecimal):
        def total: BigDecimal = unitPrice * quantity
    case class Order(id: String, customer: Customer, lines: List[OrderLine]):
        def total: BigDecimal = lines.map(_.total).sum

    val order = Order(
        id = "ord-001",
        customer = Customer(10, "Bob", Address("Berlin", "DE")),
        lines = List(
            OrderLine("scala-book", 2, BigDecimal("39.90")),
            OrderLine("fp-course", 1, BigDecimal("99.00"))
        )
    )

    println(order)
    println(order.total)

    // =========================================================
    // Companion apply means no new needed
    // =========================================================

    val quick = User(2L, "Carol", "carol@example.com")
    println(quick)

    // =========================================================
    // Additional: value-object style case classes
    // =========================================================

    case class Email(value: String):
        require(value.contains("@"), s"invalid email: $value")
        def domain: String = value.split("@").lastOption.getOrElse("")

    val mentorEmail = Email("mentor@example.com")
    println(mentorEmail.domain)   // example.com

    case class FullName(first: String, last: String):
        def show: String = s"$first $last"

    val fullName = FullName("Martin", "Odersky")
    println(fullName.show)
