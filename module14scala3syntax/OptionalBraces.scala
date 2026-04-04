package module14scala3syntax

// Summary: Scala 3 promotes significant indentation and optional braces.
// Control structures and method bodies can be written with cleaner indentation-based syntax.
// Code blocks still return the value of their last expression, just like in earlier Scala.
// end markers are optional but useful when nested code gets longer.
// This file demonstrates the everyday syntax style you should be comfortable reading in Scala 3.

@main def optionalBraces(): Unit =

    // =========================================================
    // if/then/else with indentation
    // =========================================================

    def classify(score: Int): String =
        if score >= 90 then "A"
        else if score >= 80 then "B"
        else if score >= 70 then "C"
        else "D"
    end classify

    println(classify(95))
    println(classify(82))
    println(classify(71))

    // =========================================================
    // for/yield with indentation
    // =========================================================

    val evenSquares =
        for
            n <- 1 to 10
            if n % 2 == 0
        yield n * n

    println(evenSquares.toList)

    // =========================================================
    // while/do with indentation
    // =========================================================

    var counter = 0
    while counter < 3 do
        println(s"counter=$counter")
        counter += 1

    // =========================================================
    // Expression blocks still return their last value
    // =========================================================

    val computed =
        val base = 40
        val bonus = 2
        base + bonus

    println(computed)

    // =========================================================
    // end markers can make nested logic easier to scan
    // =========================================================

    def describeNumber(n: Int): String =
        if n < 0 then
            "negative"
        else if n == 0 then
            "zero"
        else
            "positive"
    end describeNumber

    println(describeNumber(-5))
    println(describeNumber(0))
    println(describeNumber(7))
