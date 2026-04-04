package module12domainfp

// Summary: Either models a value that can be one of two possibilities.
// By convention, Left = error and Right = success.
// Scala's Either is right-biased, so map/flatMap operate on Right by default.
// Use Either when you need to preserve error information, not just absence.
// Either is a common domain-level replacement for exceptions in business logic.

@main def eitherBasics(): Unit =

    // =========================================================
    // Basic Right / Left values
    // =========================================================

    val ok: Either[String, Int] = Right(42)
    val bad: Either[String, Int] = Left("not a number")

    println(ok)
    println(bad)

    // =========================================================
    // map and flatMap work on Right
    // =========================================================

    println(ok.map(_ * 2))
    println(bad.map(_ * 2))

    def reciprocal(n: Int): Either[String, Double] =
        if n == 0 then Left("division by zero") else Right(1.0 / n)

    println(ok.flatMap(reciprocal))
    println(Right(0).flatMap(reciprocal))

    // =========================================================
    // Domain validation example
    // =========================================================

    case class User(name: String, age: Int)

    def validateName(name: String): Either[String, String] =
        if name.trim.nonEmpty then Right(name.trim) else Left("name must be non-empty")

    def validateAge(age: Int): Either[String, Int] =
        if age >= 18 then Right(age) else Left("user must be at least 18")

    def buildUser(name: String, age: Int): Either[String, User] =
        for
            validName <- validateName(name)
            validAge  <- validateAge(age)
        yield User(validName, validAge)

    println(buildUser("Alice", 30))
    println(buildUser("", 30))
    println(buildUser("Bob", 16))

    // =========================================================
    // fold — handle Left and Right explicitly
    // =========================================================

    def render(result: Either[String, Int]): String =
        result.fold(
            err => s"error=$err",
            value => s"value=$value"
        )

    println(render(ok))
    println(render(bad))

    // =========================================================
    // Converting Option to Either
    // =========================================================

    val maybePort: Option[Int] = Some(8080)
    val maybeMissing: Option[Int] = None

    println(maybePort.toRight("missing port"))
    println(maybeMissing.toRight("missing port"))

    // =========================================================
    // Additional: parsing and business rules pipeline
    // =========================================================

    def parseInt(value: String): Either[String, Int] =
        value.toIntOption.toRight(s"invalid int: $value")

    def positive(n: Int): Either[String, Int] =
        if n > 0 then Right(n) else Left(s"must be positive: $n")

    val result1 = for
        n <- parseInt("25")
        p <- positive(n)
    yield p * 2

    val result2 = for
        n <- parseInt("oops")
        p <- positive(n)
    yield p * 2

    println(result1)
    println(result2)
