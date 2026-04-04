package module12domainfp

import scala.util.{Failure, Success, Try}

// Summary: Try captures exceptions as values: Success(value) or Failure(exception).
// Use Try when dealing with exception-throwing APIs and you want to stay expression-oriented.
// map/flatMap operate on Success and propagate Failure automatically.
// recover and recoverWith let you handle failures functionally.
// Prefer Either for domain errors, Try for technical/exception-based boundaries.

@main def tryBasics(): Unit =

    // =========================================================
    // Creating Try values
    // =========================================================

    val parsedOk = Try("123".toInt)
    val parsedBad = Try("abc".toInt)

    println(parsedOk)
    println(parsedBad)

    // =========================================================
    // Pattern matching on Try
    // =========================================================

    def render(result: Try[Int]): String = result match
        case Success(value) => s"success=$value"
        case Failure(err)   => s"failure=${err.getClass.getSimpleName}: ${err.getMessage}"

    println(render(parsedOk))
    println(render(parsedBad))

    // =========================================================
    // map / flatMap / filter
    // =========================================================

    println(parsedOk.map(_ * 2))
    println(parsedBad.map(_ * 2))

    def reciprocal(n: Int): Try[Double] =
        Try(1.0 / n).filter(_ => n != 0)

    println(parsedOk.flatMap(reciprocal))
    println(Try(0).flatMap(reciprocal))

    // =========================================================
    // recover and recoverWith
    // =========================================================

    val fallback = parsedBad.recover {
        case _: NumberFormatException => -1
    }
    println(fallback)

    val fallbackTry = parsedBad.recoverWith {
        case _: NumberFormatException => Try(0)
    }
    println(fallbackTry)

    // =========================================================
    // Chaining multiple risky operations
    // =========================================================

    def parseInt(value: String): Try[Int] = Try(value.toInt)
    def divide(a: Int, b: Int): Try[Int] = Try(a / b)

    val computation1 = for
        a <- parseInt("100")
        b <- parseInt("5")
        c <- divide(a, b)
    yield c

    val computation2 = for
        a <- parseInt("100")
        b <- parseInt("0")
        c <- divide(a, b)
    yield c

    val computation3 = for
        a <- parseInt("oops")
        b <- parseInt("5")
        c <- divide(a, b)
    yield c

    println(computation1)
    println(computation2)
    println(computation3)

    // =========================================================
    // Additional: converting Try to Option / Either-like view
    // =========================================================

    println(parsedOk.toOption)
    println(parsedBad.toOption)

    val asEither1 = parsedOk.toEither.left.map(_.getMessage)
    val asEither2 = parsedBad.toEither.left.map(_.getMessage)
    println(asEither1)
    println(asEither2)
