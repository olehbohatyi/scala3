package module01setup

// Summary: Scala supports //, /* */, and /** */ comment styles.
// In this course every comment uses // — one per line, no block comments.
// Code blocks {} are expressions: the last line is the block's return value.
// Blocks are used to: (1) scope variables, (2) compute a complex initial value,
// (3) reuse the same variable name in different scopes within one file.

@main def commentsAndCodeBlocks(): Unit =

    // --- Comment styles (we only use //) ---
    // single-line comment starts with //
    val a = 1   // inline comment after code is fine
    // multi-line comments are written as consecutive // lines
    // never as /* ... */ in this course

    println(s"a = $a")

    // --- Code block: {} wraps multiple expressions, returns the last one ---
    // the block is evaluated, a + b is computed, result holds 30
    val result = {
        val x = 10
        val y = 20
        x + y     // this is the value assigned to 'result'
    }
    println(s"block result = $result")   // 30

    // --- Block as a value for complex initialisation ---
    // useful when the value requires several intermediate steps
    val greeting = {
        val firstName = "Oleh"
        val lastName  = "Bohatyi"
        s"Hello, $firstName $lastName!"   // final string is returned
    }
    println(greeting)   // Hello, Oleh Bohatyi!

    // --- Variable scoping: same name reused safely in separate blocks ---
    // this is the pattern used in the book's GitHub repository
    // to keep variable names consistent with the text

    // first block: 'items' is scoped here only
    {
        val items = List(1, 2, 3)
        println(s"first  items = $items")
    }

    // second block: 'items' is a completely different variable
    {
        val items = List(10, 20, 30)
        println(s"second items = $items")
    }

    // --- Additional: block inside a def ---
    // here the block computes a result and the def returns it
    def circleArea(radius: Double): Double =
        val pi = 3.14159265358979
        pi * radius * radius   // last expression returned

    println(s"area r=5  : ${circleArea(5)}")    // ~78.54
    println(s"area r=10 : ${circleArea(10)}")   // ~314.16

    // --- Additional: println is a side effect, its value is Unit ---
    // Unit is Scala's equivalent of void — a block can have side effects
    // before its return value
    val computed = {
        println("side-effect: computing...")   // Unit — discarded
        val temp = 6 * 7
        temp + 2   // 44 — this is what 'computed' holds
    }
    println(s"computed = $computed")   // 44
