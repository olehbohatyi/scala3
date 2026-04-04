package module01setup

// Summary: Demonstrates why Scala 3 is worth learning.
// Key selling points: FP + OOP fusion, expressive & concise syntax,
// consistency across the language, and powerful collection operations.
// All examples run in the REPL or as a standalone program.

@main def whyScala3(): Unit =

    // --- Expressiveness: concise readable syntax ---
    // val creates an immutable value (like 'const' in JS, 'final' in Java)
    val language = "Scala 3"
    val year     = 2021

    // s-interpolation: embed expressions directly in strings
    println(s"$language was released in $year")

    // --- Conciseness: a complete method in a single line ---
    // no 'return' keyword needed — the last expression is the return value
    def min(a: Int, b: Int): Int = if a < b then a else b

    println(s"min(7, 3) = ${min(7, 3)}")   // 3
    println(s"min(2, 9) = ${min(2, 9)}")   // 2

    // --- FP + OOP fusion: collections with functional methods ---
    // book example: old-style Java loop replaced by filter + map
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // filter: keeps only elements where the predicate returns true
    val evens = numbers.filter(_ % 2 == 0)
    println(s"Evens:          $evens")         // List(2, 4, 6, 8, 10)

    // map: transforms every element
    val doubled = evens.map(_ * 2)
    println(s"Evens doubled:  $doubled")        // List(4, 8, 12, 16, 20)

    // chaining: pipeline reads left to right, no temp variables needed
    val result = numbers.filter(_ > 5).map(_ * 3)
    println(s"Over 5, *3:     $result")         // List(18, 21, 24, 27, 30)

    // --- Consistency: all collection types share the same creation syntax ---
    // no special syntax — they are just classes with an apply() method
    val list   = List(1, 2, 3)
    val vector = Vector(1, 2, 3)
    val set    = Set(1, 2, 3)
    val map    = Map("a" -> 1, "b" -> 2)

    println(s"List:   $list")
    println(s"Vector: $vector")
    println(s"Set:    $set")
    println(s"Map:    $map")

    // --- Additional: Scala 3 runs on JVM, compiles to JS, native ---
    // this means one language for backend, frontend, CLI scripts
    println(s"\nScala 3 = JVM + Scala.js + Scala Native")
    println("FP libraries: Cats, ZIO (world-class, used in production)")
