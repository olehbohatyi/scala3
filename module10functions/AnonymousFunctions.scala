package module10functions

// Summary: Anonymous functions are small unnamed function values used inline.
// They are heavily used with collections, higher-order functions, and small local transformations.
// Scala offers concise syntax for them, especially when the parameter names are obvious.
// Clear anonymous functions reduce boilerplate, but overly dense ones hurt readability.
// This file shows several common anonymous-function styles.

@main def anonymousFunctions(): Unit =

    // =========================================================
    // Inline lambda syntax with one and multiple parameters
    // =========================================================

    val numbers = List(1, 2, 3, 4)
    println(numbers.map(n => n * n))
    println(numbers.filter(_ % 2 == 0))

    val pairs = List(("scala", 3), ("java", 2))
    println(pairs.map((name, version) => s"$name-$version"))
