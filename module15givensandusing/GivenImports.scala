package module15givensandusing

// Summary: Scala 3 lets you import givens explicitly and locally where they are needed.
// This improves readability compared with broad wildcard imports and makes implicit behavior clearer.
// Local given imports are especially useful when different policies are needed in different blocks.
// Ordering is a simple and practical way to demonstrate this behavior.
// This file shows how imports can change behavior without changing business logic.

object ReverseIntOrdering:
    given Ordering[Int] with
        def compare(x: Int, y: Int): Int = y.compareTo(x)

object CaseInsensitiveTextOrdering:
    given Ordering[String] with
        def compare(x: String, y: String): Int = x.compareToIgnoreCase(y)

@main def givenImports(): Unit =

    // =========================================================
    // Default givens already exist in the standard library
    // =========================================================

    val defaultNumbers = List(3, 1, 2).sorted
    println(defaultNumbers)

    // =========================================================
    // A local import can change behavior for one expression block
    // =========================================================

    val reverseNumbers =
        import ReverseIntOrdering.given
        List(3, 1, 2).sorted

    val sortedWords =
        import CaseInsensitiveTextOrdering.given
        List("scala", "Java", "kotlin", "Akka").sorted

    println(reverseNumbers)
    println(sortedWords)
