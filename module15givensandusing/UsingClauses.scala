package module15givensandusing

// Summary: A using clause declares that a method needs contextual parameters.
// Callers normally do not pass those values explicitly because the compiler finds matching givens.
// This is useful for shared services such as formatters, clocks, loggers, and policies.
// The design keeps business methods focused on what they need rather than how dependencies are threaded.
// This file demonstrates two greeting styles swapped by importing different givens.

trait GreetingStyle:
    def greet(name: String): String

object FormalStyle:
    given formalGreetingStyle: GreetingStyle with
        def greet(name: String): String = s"Good day, $name."

object CasualStyle:
    given casualGreetingStyle: GreetingStyle with
        def greet(name: String): String = s"Hey, $name!"

def welcome(name: String)(using style: GreetingStyle): String =
    style.greet(name)

@main def usingClauses(): Unit =

    // =========================================================
    // One import gives one behavior
    // =========================================================

    val formalMessage =
        import FormalStyle.given
        welcome("Scala")

    val casualMessage =
        import CasualStyle.given
        welcome("Scala")

    println(formalMessage)
    println(casualMessage)

    // =========================================================
    // using can also be passed explicitly when needed
    // =========================================================

    val explicit = welcome("Team Lead")(using FormalStyle.formalGreetingStyle)
    println(explicit)
