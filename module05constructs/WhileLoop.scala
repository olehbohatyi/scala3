package module05constructs

// Summary: while is the only looping construct in Scala that requires mutation.
// The condition is checked BEFORE each iteration — if false from the start, body never runs.
// Scala 3 uses 'do' instead of braces: while condition do body
// In idiomatic Scala, while is rare — prefer recursion or collection methods.
// Use while when: interfacing with Java iterators, reading streams, or performance loops.

@main def whileLoop(): Unit =

    // =========================================================
    // Basic while loop — Scala 3 syntax with 'do'
    // =========================================================

    var i = 1
    while i <= 5 do
        println(s"  i = $i")
        i += 1   // mutation required — without this it loops forever

    // =========================================================
    // while with multi-statement body (indentation block)
    // =========================================================

    var count = 0
    var total = 0
    while count < 10 do
        count += 1
        total += count
    println(s"sum 1..10 = $total")   // 55

    // =========================================================
    // Condition checked BEFORE body — may never run
    // =========================================================

    // start with i > limit, so the body never executes
    var j = 10
    while j < 5 do
        println("this never prints")
        j += 1
    println(s"j after zero-iteration while = $j")   // 10 — unchanged

    // =========================================================
    // do/while equivalent — run body at LEAST once
    // =========================================================

    // Scala 3 has no do/while keyword, but we can simulate it:
    // run the body once, then check the condition
    // https://docs.scala-lang.org/scala3/reference/dropped-features/do-while.html
    var k = 10
    var ranOnce = false
    // body executes first
    while
        ranOnce = true
        println(s"ran at least once, k=$k")
        k += 1
        k < 5
    do ()   // condition is false from the start, but body ran once

    println(s"ranOnce = $ranOnce")   // true

    // =========================================================
    // Classic: reading from an iterator (Java interop pattern)
    // =========================================================

    // simulating a Java-style iterator with a list
    val items = Iterator("alpha", "beta", "gamma", "delta")
    while items.hasNext do
        val item = items.next()
        println(s"  read: $item")

    // =========================================================
    // while vs FP — knowing both for interviews
    // =========================================================

    // imperative: find first number > 50 in a range
    var found = -1
    var n = 1
    while n <= 100 && found == -1 do
        if n * n > 50 then found = n
        n += 1
    println(s"first n where n*n > 50: $found")   // 8

    // FP equivalent — more readable, no mutation
    val foundFP = (1 to 100).find(n => n * n > 50)
    println(s"same via .find: $foundFP")           // Some(8)

    // =========================================================
    // Additional: while for accumulating with early exit
    // =========================================================

    // sum numbers until total exceeds 100
    var running = 0
    var num     = 1
    while running <= 100 do
        running += num
        num += 1
    println(s"first sum > 100 reached at step ${num - 1}: total=$running")

    // =========================================================
    // Additional: countdown with while
    // =========================================================

    print("countdown: ")
    var t = 5
    while t > 0 do
        print(s"$t ")
        t -= 1
    println("Go!")
