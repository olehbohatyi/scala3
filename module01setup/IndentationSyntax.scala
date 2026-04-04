package module01setup

// Summary: Scala 3 replaces curly braces with significant indentation.
// This is the single biggest syntax change from Scala 2 to Scala 3.
// Rules: use 4 spaces per indent level (2 is also common, pick one).
// Keywords 'then', 'do', 'yield' mark the start of an indented block.
// Curly braces still compile, but are not idiomatic in Scala 3.

@main def indentationSyntax(): Unit =

    // --- if/then/else: 'then' replaces opening brace ---
    // Scala 3 style — recommended
    val x = 42
    if x > 0 then
        println(s"$x is positive")
    else if x < 0 then
        println(s"$x is negative")
    else
        println("zero")

    // Scala 2 style — still compiles but not idiomatic in Scala 3:
    // if (x > 0) {
    //     println("positive")
    // } else {
    //     println("non-positive")
    // }

    // --- for loop: 'do' replaces the opening brace ---
    // iterates over a range 1, 2, 3
    for i <- 1 to 3 do
        println(s"  loop i = $i")

    // --- def: body indented below the signature ---
    // the last expression is automatically the return value
    def square(n: Int): Int =
        val result = n * n
        result   // returned — no 'return' keyword needed

    println(s"square(5) = ${square(5)}")   // 25
    println(s"square(9) = ${square(9)}")   // 81

    // --- Nested indentation: each level adds another 4 spaces ---
    def classify(n: Int): String =
        if n > 0 then
            if n > 100 then
                "large positive"
            else
                "small positive"
        else if n < 0 then
            "negative"
        else
            "zero"

    println(classify(5))     // small positive
    println(classify(200))   // large positive
    println(classify(-3))    // negative
    println(classify(0))     // zero

    // --- Additional: single-line if is fine for trivial checks ---
    // parentheses around the condition are NOT required in Scala 3
    val label = if x % 2 == 0 then "even" else "odd"
    println(s"$x is $label")

    // --- Additional: while loop also uses 'do' ---
    var count = 0
    while count < 3 do
        print(s"count=$count ")
        count += 1
    println()   // newline after the inline prints
