package module05constructs

// Summary: In Scala, if/then/else is an EXPRESSION — it returns a value.
// This is the core of Expression-Oriented Programming (EOP).
// EOP means: almost every construct (if, match, try, for) produces a value.
// Consequence: val x = if condition then a else b — no need for var + mutation.
// Key interview point: Scala has NO ternary operator (? :) — if/else IS the ternary.

@main def ifElseAndEOP(): Unit =

    // =========================================================
    // Basic if/then/else — Scala 3 syntax
    // =========================================================

    val x = 42

    // standard multi-line form with 'then'
    if x > 0 then
        println("positive")
    else if x < 0 then
        println("negative")
    else
        println("zero")

    // single-line form — fine for simple conditions
    if x > 100 then println("large") else println("small")

    // =========================================================
    // if/else as an EXPRESSION (EOP) — returns a value
    // =========================================================

    // book example: assign result of if/else to a val
    // no ternary operator needed — if/else IS the ternary
    val label = if x > 0 then "positive" else "non-positive"
    println(s"x=$x is $label")

    // the two branches must return compatible types
    val abs = if x >= 0 then x else -x
    println(s"abs($x) = $abs")

    // use inside string interpolation
    println(s"$x is ${if x % 2 == 0 then "even" else "odd"}")

    // =========================================================
    // EOP replaces the Java/C pattern of: declare var, then assign
    // =========================================================

    // BAD (imperative style — avoid in Scala):
    // var result = ""
    // if x > 50 then result = "big" else result = "small"

    // GOOD (expression-oriented style):
    val result = if x > 50 then "big" else "small"
    println(s"result = $result")

    // =========================================================
    // Multi-line if/else expression — blocks return their last value
    // =========================================================

    val score = 87
    val grade = if score >= 90 then
        "A"
    else if score >= 80 then
        "B"
    else if score >= 70 then
        "C"
    else
        "F"
    println(s"score=$score -> grade=$grade")   // B

    // =========================================================
    // EOP: if/else with side effects AND a return value
    // =========================================================

    // blocks can mix side effects with a return value
    val status = if x > 0 then
        println("computing positive status...")    // side effect
        "active"                                   // return value
    else
        println("computing non-positive status...") // side effect
        "inactive"                                  // return value
    println(s"status = $status")

    // =========================================================
    // Unit: the return type when a branch has no meaningful value
    // =========================================================

    // if without else returns Unit — the "no value" type
    // this is fine for side-effect-only code
    val unit = if x > 0 then println("has value")
    println(s"unit value = $unit")   // ()  <- Unit's only value

    // =========================================================
    // Additional: nested if in a function
    // =========================================================

    def bmi(weight: Double, height: Double): String =
        val index = weight / (height * height)
        if index < 18.5 then "Underweight"
        else if index < 25.0 then "Normal"
        else if index < 30.0 then "Overweight"
        else "Obese"

    println(s"BMI(70kg, 1.75m) = ${bmi(70, 1.75)}")    // Normal
    println(s"BMI(100kg, 1.70m) = ${bmi(100, 1.70)}")   // Obese

    // =========================================================
    // Additional: EOP summary — constructs that return values
    // =========================================================

    // ALL of these are expressions in Scala (unlike Java/C):
    // if/else, match, try/catch, for/yield, blocks { }
    // This means you can always assign their result to a val.

    val tryResult =
        try "123".toInt
        catch case _: NumberFormatException => -1
    println(s"tryResult = $tryResult")   // 123
