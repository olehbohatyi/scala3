package module15givensandusing

// Summary: A given defines a contextual value that the compiler can supply when needed.
// This is Scala 3's clearer replacement for many older implicit-value patterns.
// Given instances work especially well for capabilities, type classes, and shared policies.
// Consuming code asks for the capability by type instead of wiring it manually every time.
// This file shows a simple type-class-style example based on a Show capability.

trait Show[A]:
    def show(value: A): String

object Show:
    given Show[Int] with
        def show(value: Int): String = s"int:$value"

    given Show[Boolean] with
        def show(value: Boolean): String = if value then "yes" else "no"

    given Show[String] with
        def show(value: String): String = s"\"$value\""

def render[A](value: A)(using show: Show[A]): String =
    show.show(value)

@main def givenInstances(): Unit =

    // =========================================================
    // Import givens into scope
    // =========================================================

    import Show.given

    println(render(42))
    println(render(true))
    println(render("Scala 3"))

    // =========================================================
    // Givens are resolved by required type
    // =========================================================

    val rendered = List(1, 2, 3).map(render(_))
    println(rendered)
