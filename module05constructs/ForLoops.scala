package module05constructs

// Summary: for loops in Scala are used for SIDE EFFECTS (printing, updating state).
// They iterate over any collection or range using the <- generator syntax.
// 'do' replaces the opening brace in Scala 3 — no parentheses around the generator.
// For loops return Unit — if you need a result, use for EXPRESSIONS (see ForExpressions).
// Nested for loops are written by stacking generators (no extra 'for' keyword needed).

@main def forLoops(): Unit =

    // =========================================================
    // Basic for loop over a Range
    // =========================================================

    // 1 to 5 is inclusive on both ends: 1, 2, 3, 4, 5
    println("1 to 5:")
    for i <- 1 to 5 do
        println(s"  i = $i")

    // 1 until 5 is exclusive on the right: 1, 2, 3, 4
    println("1 until 5:")
    for i <- 1 until 5 do
        print(s"$i ")
    println()

    // step with 'by' — every 2nd element
    println("1 to 10 by 2:")
    for i <- 1 to 10 by 2 do
        print(s"$i ")   // 1 3 5 7 9
    println()

    // counting down with negative step
    println("5 to 1 by -1:")
    for i <- 5 to 1 by -1 do
        print(s"$i ")   // 5 4 3 2 1
    println()

    // =========================================================
    // Iterating over a List
    // =========================================================

    val fruits = List("apple", "banana", "cherry")
    for fruit <- fruits do
        println(s"  fruit: $fruit")

    // with index using zipWithIndex
    println("with index:")
    for (fruit, idx) <- fruits.zipWithIndex do
        println(s"  [$idx] $fruit")

    // =========================================================
    // Iterating over a Map
    // =========================================================

    val capitals = Map("Germany" -> "Berlin", "France" -> "Paris", "Spain" -> "Madrid")
    println("capitals:")
    for (country, capital) <- capitals do
        println(s"  $country -> $capital")

    // =========================================================
    // Nested for loops — stacked generators
    // =========================================================

    // multiplication table 1-3
    println("multiplication table:")
    for i <- 1 to 3 do
        for j <- 1 to 3 do
            print(f"${i * j}%4d")
        println()

    // =========================================================
    // for with val inside the body
    // =========================================================

    // you can declare vals inside the for body
    val numbers = List(1, 2, 3, 4, 5)
    println("squares:")
    for n <- numbers do
        val squared = n * n    // local to this iteration
        println(s"  $n² = $squared")

    // =========================================================
    // for over a String — iterates over Chars
    // =========================================================

    val word = "Scala"
    print("chars: ")
    for ch <- word do
        print(s"$ch ")   // S c a l a
    println()

    // =========================================================
    // Additional: looping with a side-effecting accumulator
    // =========================================================

    // imperative sum — shows where var is acceptable
    var sum = 0
    for n <- 1 to 100 do
        sum += n
    println(s"sum 1..100 = $sum")   // 5050

    // FP alternative — no for loop needed
    val sumFP = (1 to 100).sum
    println(s"sum 1..100 (FP) = $sumFP")   // 5050

    // =========================================================
    // Additional: for loop over nested lists
    // =========================================================

    val matrix = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    println("matrix:")
    for row <- matrix do
        for cell <- row do
            print(f"$cell%3d")
        println()
