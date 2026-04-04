package module12domainfp

// Summary: Option models an optional value without using null.
// Some(value) means present; None means absent.
// Prefer map, flatMap, fold, getOrElse, or pattern matching over unsafe .get.
// Option composes naturally with collections and for-comprehensions.
// In domain models, use Option only when absence is a valid business concept.

@main def optionBasics(): Unit =

    // =========================================================
    // Creating Option values
    // =========================================================

    val email1: Option[String] = Some("alice@example.com")
    val email2: Option[String] = None
    val fromNullable1 = Option("scala@example.com")
    val fromNullable2 = Option(null)

    println(email1)
    println(email2)
    println(fromNullable1)
    println(fromNullable2)

    // =========================================================
    // Safe access with getOrElse
    // =========================================================

    println(email1.getOrElse("missing@example.com"))
    println(email2.getOrElse("missing@example.com"))

    // =========================================================
    // map — transform only if present
    // =========================================================

    val domains = List(email1, email2).map(_.map(_.split("@").last))
    println(domains)

    val upper = email1.map(_.toUpperCase)
    val upperMissing = email2.map(_.toUpperCase)
    println(upper)
    println(upperMissing)

    // =========================================================
    // flatMap — avoid nested Option
    // =========================================================

    def extractDomain(email: String): Option[String] =
        email.split("@").toList match
            case _ :: domain :: Nil => Some(domain)
            case _                  => None

    println(email1.flatMap(extractDomain))
    println(email2.flatMap(extractDomain))

    // =========================================================
    // fold — handle both cases in one expression
    // =========================================================

    def renderEmail(email: Option[String]): String =
        email.fold("no email configured")(value => s"email=$value")

    println(renderEmail(email1))
    println(renderEmail(email2))

    // =========================================================
    // for-comprehension with Option
    // =========================================================

    val maybeFirst: Option[String] = Some("Ada")
    val maybeLast: Option[String] = Some("Lovelace")
    val maybeNickname: Option[String] = None

    val fullName = for
        first <- maybeFirst
        last  <- maybeLast
    yield s"$first $last"

    val nicknameLabel = for
        first <- maybeFirst
        nick  <- maybeNickname
    yield s"$first ($nick)"

    println(fullName)
    println(nicknameLabel)

    // =========================================================
    // find and headOption produce Option
    // =========================================================

    val numbers = List(1, 2, 3, 4, 5)
    println(numbers.find(_ > 3))
    println(numbers.find(_ > 10))
    println(numbers.headOption)
    println(List.empty[Int].headOption)

    // =========================================================
    // Additional: avoid unsafe .get
    // =========================================================

    // email2.get   // throws NoSuchElementException when None
    println("Prefer getOrElse/map/fold/pattern matching over .get")
