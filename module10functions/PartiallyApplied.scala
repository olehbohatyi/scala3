package module10functions

// Summary: A partially applied function fixes (supplies) some arguments of a function,
// returning a new function that takes the remaining arguments.
// Use _ as a placeholder for the argument(s) you want to supply later.
// Eta-expansion: converting a method (def) into a function value (val/lambda).
// Partially applied functions reduce repetition and enable reusability.

@main def partiallyApplied(): Unit =

    // =========================================================
    // Basic partially applied function
    // =========================================================

    def add(a: Int, b: Int): Int = a + b

    // fix the first argument — _ is the placeholder for the second
    val add10: Int => Int = add(10, _)
    val add100: Int => Int = add(100, _)

    println(add10(5))    // 15
    println(add10(20))   // 30
    println(add100(5))   // 105

    // fix the SECOND argument — _ is the placeholder for the first
    val addToFive: Int => Int = add(_, 5)
    println(addToFive(3))   // 8
    println(addToFive(20))  // 25

    // =========================================================
    // Partially applied with 3-parameter function
    // =========================================================

    def clamp(min: Int, max: Int, value: Int): Int =
        math.min(math.max(value, min), max)

    // fix min and max — create a specialised clamper
    val clamp0to100: Int => Int = clamp(0, 100, _)
    val clampByte:   Int => Int = clamp(0, 255, _)

    println(clamp0to100(-5))    // 0
    println(clamp0to100(50))    // 50
    println(clamp0to100(150))   // 100
    println(clampByte(300))     // 255

    // =========================================================
    // Partial application with String methods
    // =========================================================

    def format(template: String, value: String): String = template.replace("{}", value)

    val greetUser    = format("Hello, {}!", _)
    val errorMessage = format("Error: {} not found", _)

    println(greetUser("Alice"))       // Hello, Alice!
    println(greetUser("Bob"))         // Hello, Bob!
    println(errorMessage("config"))   // Error: config not found

    // =========================================================
    // Using partially applied functions with map/filter
    // =========================================================

    def isGreaterThan(threshold: Int, n: Int): Boolean = n > threshold
    def scaleBy(factor: Double, n: Int): Double = n * factor

    val nums = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // partially apply to create predicates/transforms for the collection API
    val biggerThan5 = isGreaterThan(5, _)
    val doubled     = scaleBy(2.0, _)

    println(nums.filter(biggerThan5))    // List(6, 7, 8, 9, 10)
    println(nums.map(doubled))           // List(2.0, 4.0, ..., 20.0)

    // inline partial application — common style
    println(nums.filter(isGreaterThan(3, _)))   // List(4, 5, 6, 7, 8, 9, 10)
    println(nums.map(scaleBy(0.5, _)))          // List(0.5, 1.0, 1.5, ...)

    // =========================================================
    // Eta-expansion — converting a def into a function value
    // =========================================================

    def square(n: Int): Int = n * n

    // passing def directly: Scala auto eta-expands it
    val squares = nums.map(square)    // square is eta-expanded to n => square(n)
    println(s"squares: $squares")

    // explicit eta-expansion with trailing _
    val squareFn: Int => Int = square _   // or just `square` when type is known
    println(squareFn(7))    // 49

    // eta-expansion of a multi-param method gives a multi-param function
    val addFn: (Int, Int) => Int = add   // eta-expanded
    println(addFn(3, 4))   // 7

    // =========================================================
    // Practical: logging function with fixed prefix
    // =========================================================

    def log(level: String, message: String): Unit =
        println(s"[$level] $message")

    val logInfo  = log("INFO", _)
    val logError = log("ERROR", _)
    val logDebug = log("DEBUG", _)

    logInfo("Application started")
    logError("Connection refused")
    logDebug("Processing item 42")

    // =========================================================
    // Additional: partial application vs closure
    // =========================================================

    // partially applied function (using _) — works on def
    val pa = add(5, _)

    // closure (lambda capturing a val) — same observable behaviour
    val cl = (x: Int) => add(5, x)

    // both are identical in effect:
    println(pa(3))   // 8
    println(cl(3))   // 8

    // difference: partial application is a Scala syntactic feature
    // closures are more general — can capture any outer variable
