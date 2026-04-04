package module11domainoop

// Summary: object defines a singleton — exactly one instance exists.
// Companion object = object with the same name as a class in the same file.
// Companions can access each other's private members and commonly hold factory methods.
// apply lets you construct without new; unapply supports pattern matching and extraction.
// Use objects for stateless utilities, registries, constants, and smart constructors.

@main def objectsAndCompanions(): Unit =

    // =========================================================
    // Plain singleton object
    // =========================================================

    object AppConfig:
        val appName: String = "Scala Interview Notes"
        val version: String = "1.0"
        def banner: String = s"$appName v$version"

    println(AppConfig.appName)
    println(AppConfig.banner)

    // =========================================================
    // Companion object with apply factory
    // =========================================================

    class Email private (val local: String, val domain: String):
        def value: String = s"$local@$domain"
        override def toString: String = value

    object Email:
        def apply(local: String, domain: String): Email =
            require(local.nonEmpty, "local part must be non-empty")
            require(domain.contains('.'), "domain must contain a dot")
            new Email(local.trim.toLowerCase, domain.trim.toLowerCase)

        def fromString(value: String): Option[Email] =
            value.split("@") match
                case Array(local, domain) if local.nonEmpty && domain.contains('.') =>
                    Some(Email(local, domain))
                case _ => None

    val support = Email("support", "example.com")
    println(support)
    println(Email.fromString("mentor@example.com"))
    println(Email.fromString("broken-email"))

    // =========================================================
    // Companion object can access private members
    // =========================================================

    class Token private (private val raw: String):
        private def masked: String = raw.take(4) + "***"
        override def toString: String = s"Token(${masked})"

    object Token:
        def issue(raw: String): Token = new Token(raw)
        def debug(token: Token): String = s"raw=${token.raw}, masked=${token.masked}"

    val token = Token.issue("abcd-1234-secret")
    println(token)
    println(Token.debug(token))

    // =========================================================
    // unapply for extraction in pattern matching
    // =========================================================

    object FullName:
        def apply(first: String, last: String): String = s"$first $last"
        def unapply(value: String): Option[(String, String)] =
            value.split(" ").toList match
                case first :: last :: Nil => Some((first, last))
                case _ => None

    val full = FullName("Ada", "Lovelace")
    val description = full match
        case FullName(first, last) => s"first=$first, last=$last"
        case _ => "invalid full name"
    println(description)

    // =========================================================
    // Smart constructors returning Option or Either-style data
    // =========================================================

    class Age private (val value: Int):
        override def toString: String = s"Age($value)"

    object Age:
        def from(value: Int): Option[Age] =
            if value >= 0 && value <= 130 then Some(new Age(value)) else None

    println(Age.from(25))
    println(Age.from(-5))

    // =========================================================
    // Utility object namespace
    // =========================================================

    object Strings:
        def snakeCase(value: String): String =
            value.trim.toLowerCase.replace(' ', '_')
        def initials(value: String): String =
            value.split(" ").filter(_.nonEmpty).map(_.head.toUpper).mkString

    println(Strings.snakeCase("Scala Course Creator"))
    println(Strings.initials("Martin Odersky"))
