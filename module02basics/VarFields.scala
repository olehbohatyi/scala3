package module02basics

// Summary: var declares a mutable variable — its value can be reassigned.
// The golden rule: start with val, switch to var only when mutation is needed.
// The reassigned value must match the original type (Scala is strongly typed).
// In practice, var is rare in idiomatic Scala — collections and FP remove the need.

@main def varFields(): Unit =

    // --- Basic var declaration and reassignment ---
    // book example: a person's name that can change
    var name = "Reginald Kenneth Dwight"
    println(s"born as:  $name")

    // reassignment is allowed — same variable, new value
    name = "Elton John"
    println(s"known as: $name")

    // --- Type must stay the same after reassignment ---
    // the compiler infers 'name' as String and enforces it
    // name = 42   // compile error: type mismatch (Int vs String)

    // --- Counter: the classic use case for var ---
    // looping and accumulating require mutation
    var counter = 0
    for _ <- 1 to 5 do
        counter += 1   // += is syntactic sugar for counter = counter + 1
    println(s"counter after 5 increments = $counter")   // 5

    // --- Accumulator pattern ---
    var total = 0.0
    val prices = List(9.99, 4.50, 14.99, 2.00)
    for p <- prices do
        total += p
    println(f"total = $total%.2f")   // 31.48

    // --- While loop: var is required here ---
    // the loop condition depends on the mutable variable changing
    var i = 1
    while i <= 3 do
        println(s"  while i=$i")
        i += 1

    // --- The FP alternative: avoid var entirely ---
    // idiomatic Scala uses sum instead of a mutable accumulator
    val totalFP = prices.sum
    println(f"total (FP) = $totalFP%.2f")   // same result, no var needed

    // --- Additional: swap values (requires var) ---
    var a = 10
    var b = 20
    val tmp = a
    a = b
    b = tmp
    println(s"after swap: a=$a, b=$b")   // a=20, b=10

    // --- Summary: when to reach for var ---
    // 1. Loop counters that cannot be expressed with for/map/fold
    // 2. Accumulating results imperatively (prefer fold/sum/reduce in FP)
    // 3. Interfacing with mutable Java APIs
    // 4. Performance-critical inner loops (rare)
    println("\nRule: val by default, var only when truly necessary")
