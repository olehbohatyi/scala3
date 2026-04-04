package module11domainoop

// Summary: A class defines data + behavior and is instantiated with new.
// Constructor parameters become fields only if marked with val or var.
// val = public read-only field; var = public mutable field; plain parameter = constructor-only.
// Methods live inside the class body and can use constructor parameters and fields.
// A class body also acts as the primary constructor in Scala.

@main def classesAndConstructors(): Unit =

    // =========================================================
    // Basic class with val and var fields
    // =========================================================

    class User(val name: String, var age: Int):
        def isAdult: Boolean = age >= 18
        def birthday(): Unit = age += 1
        override def toString: String = s"User(name=$name, age=$age)"

    val alice = new User("Alice", 30)
    println(alice.name)        // Alice
    println(alice.age)         // 30
    println(alice.isAdult)     // true
    alice.birthday()
    println(alice.age)         // 31
    println(alice)             // User(name=Alice, age=31)

    // =========================================================
    // Plain constructor parameter — NOT a field
    // =========================================================

    class SecretNote(text: String):
        def masked: String = "*" * text.length

    val note = new SecretNote("top-secret")
    println(note.masked)       // **********
    // note.text               // does not compile — text is not a field

    // =========================================================
    // Class body runs during construction
    // =========================================================

    class Service(val name: String):
        println(s"constructing service: $name")
        def start(): String = s"$name started"

    val payments = new Service("payments")
    println(payments.start())

    // =========================================================
    // Validation in the primary constructor
    // =========================================================

    class Percentage(private var rawValue: Int):
        require(rawValue >= 0 && rawValue <= 100, s"percentage out of range: $rawValue")
        def value: Int = rawValue
        def update(newValue: Int): Unit =
            require(newValue >= 0 && newValue <= 100, s"percentage out of range: $newValue")
            rawValue = newValue
        override def toString: String = s"Percentage($rawValue)"

    val progress = new Percentage(75)
    println(progress.value)    // 75
    progress.update(90)
    println(progress)          // Percentage(90)

    // =========================================================
    // Auxiliary constructor with def this(...)
    // =========================================================

    class Person(val firstName: String, val lastName: String, var age: Int):
        def this(fullName: String) =
            this(
                fullName.split(" ").headOption.getOrElse("Unknown"),
                fullName.split(" ").drop(1).mkString(" "),
                0
            )
        def fullName: String = s"$firstName $lastName".trim
        override def toString: String = s"Person(fullName=$fullName, age=$age)"

    val bob = new Person("Bob", "Martin", 72)
    val carol = new Person("Carol Danvers")
    println(bob)
    println(carol)

    // =========================================================
    // Private fields with public methods
    // =========================================================

    class BankAccount(owner: String, private var balance: BigDecimal):
        require(balance >= 0, "balance must be non-negative")

        def deposit(amount: BigDecimal): Unit =
            require(amount > 0, "deposit must be positive")
            balance += amount

        def withdraw(amount: BigDecimal): Boolean =
            require(amount > 0, "withdraw amount must be positive")
            if amount <= balance then
                balance -= amount
                true
            else false

        def currentBalance: BigDecimal = balance
        override def toString: String = s"BankAccount(owner=$owner, balance=$balance)"

    val account = new BankAccount("Dana", BigDecimal(100))
    account.deposit(BigDecimal(40))
    println(account.withdraw(BigDecimal(90)))
    println(account.currentBalance)
    println(account.withdraw(BigDecimal(1000)))
    println(account)

    // =========================================================
    // Methods with named and default arguments
    // =========================================================

    class EmailBuilder(val from: String):
        def build(to: String, subject: String = "No subject", urgent: Boolean = false): String =
            val prefix = if urgent then "[URGENT] " else ""
            s"from=$from; to=$to; subject=${prefix + subject}"

    val email = new EmailBuilder("mentor@example.com")
    println(email.build("student@example.com"))
    println(email.build("team@example.com", urgent = true, subject = "Release today"))
