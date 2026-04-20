package module15givensandusing


// Summary: Named givens make contextual values easier to reference explicitly when needed.
// Local overrides are useful when one block should use a different policy or configuration.
// This pattern keeps contextual programming flexible without making behavior mysterious.
// It is especially helpful when multiple valid policies exist for the same type class or capability.
// This file demonstrates named givens and local override blocks side by side.

trait Formatter:
    def format(value: String): String

object Formatters:
    given upperFormatter: Formatter with
        def format(value: String): String = value.trim.toUpperCase

    given lowerFormatter: Formatter with
        def format(value: String): String = value.trim.toLowerCase

def render(value: String)(using formatter: Formatter): String =
    formatter.format(value)

@main def namedGivensAndLocalOverrides(): Unit =

    // =========================================================
    // A named given can be passed explicitly
    // =========================================================

    println(render(" Scala 3 ")(using Formatters.upperFormatter))
    println(render(" Scala 3 ")(using Formatters.lowerFormatter))

    // =========================================================
    // A local given override changes one block only
    // =========================================================

    val localResult =
        given Formatter with
            def format(value: String): String = s"[${value.trim}]"
        render(" Scala 3 ")

    println(localResult)
