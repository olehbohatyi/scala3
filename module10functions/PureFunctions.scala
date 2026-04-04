package module10functions

// Summary: A pure function has two properties:
// 1. Same input ALWAYS produces same output (deterministic).
// 2. No side effects — does not read/write external state, no I/O, no mutation.
// Pure functions are the building blocks of FP — easy to test, reason about, and compose.
// Referential transparency: you can replace a pure function call with its result anywhere.
// Impure functions: println, random, read from DB, mutate a var — these have side effects.

@main def pureFunctions(): Unit =

    // =========================================================
    // Basic function syntax recap
    // =========================================================

    // def name(params): ReturnType = body
    def add(a: Int, b: Int): Int = a + b
    def square(n: Int): Int = n * n
    def greeting(name: String): String = s"Hello, $name!"

    println(add(3, 4))        // 7
    println(square(5))        // 25
    println(greeting("Scala")) // Hello, Scala!

    // single-expression: return type can be inferred (but explicit is better practice)
    def multiply(a: Int, b: Int) = a * b   // inferred Int

    // block body: last expression is the return value
    def absValue(n: Int): Int =
        if n >= 0 then n else -n

    println(absValue(-7))   // 7

    // =========================================================
    // Pure functions — deterministic, no side effects
    // =========================================================

    // PURE: always returns the same result for the same input
    def circumference(r: Double): Double = 2 * math.Pi * r
    def isEven(n: Int): Boolean = n % 2 == 0
    def toUpperWords(s: String): List[String] = s.split(" ").map(_.toUpperCase).toList

    println(circumference(5))             // 31.41... (always the same)
    println(isEven(4))                    // true
    println(toUpperWords("hello world"))  // List(HELLO, WORLD)

    // =========================================================
    // Referential transparency — pure function call = its result
    // =========================================================

    // add(2, 3) can be replaced by 5 anywhere in the code
    // these three are completely equivalent:
    val r1 = add(2, 3) + add(2, 3)   // two calls
    val r2 = 5 + 5                    // replaced with result
    val r3 = add(2, 3) + 5            // mixed
    println(s"r1=$r1  r2=$r2  r3=$r3")  // all 10

    // =========================================================
    // IMPURE functions — have side effects (for contrast)
    // =========================================================

    // IMPURE: reads external mutable state
    var counter = 0
    def increment(): Int =
        counter += 1    // side effect: mutates external var
        counter
    println(increment())  // 1
    println(increment())  // 2  ← different result, same "input"!

    // IMPURE: does I/O (printing is a side effect)
    def printAndReturn(n: Int): Int =
        println(s"  side-effect: printing $n")   // side effect
        n * 2

    // IMPURE: uses random
    def randomBetween(lo: Int, hi: Int): Int =
        lo + scala.util.Random.nextInt(hi - lo + 1)

    // =========================================================
    // Pure alternative: pass state explicitly instead of mutating
    // =========================================================

    // IMPURE version uses external var (see above)
    // PURE version: pass and return the state
    def pureIncrement(current: Int): Int = current + 1

    val s0 = 0
    val s1 = pureIncrement(s0)   // 1
    val s2 = pureIncrement(s1)   // 2
    val s3 = pureIncrement(s2)   // 3
    println(s"s0=$s0  s1=$s1  s2=$s2  s3=$s3")   // all predictable

    // =========================================================
    // Why pure functions? — testability
    // =========================================================

    // pure functions need NO test setup — no mocks, no DB, no state
    assert(add(2, 3) == 5)
    assert(isEven(4) == true)
    assert(isEven(7) == false)
    assert(absValue(-5) == 5)
    assert(circumference(1) == 2 * math.Pi)
    println("All assertions passed — pure functions are trivially testable!")

    // =========================================================
    // Functions with default parameter values
    // =========================================================

    def greet(name: String, greeting: String = "Hello"): String =
        s"$greeting, $name!"

    println(greet("Alice"))             // Hello, Alice!  (default used)
    println(greet("Bob", "Hi"))         // Hi, Bob!
    println(greet(greeting = "Hey", name = "Carol"))  // named args, any order

    // =========================================================
    // Recursive pure functions
    // =========================================================

    // factorial — pure, no mutation, no side effects
    def factorial(n: Int): Long =
        if n <= 1 then 1L
        else n * factorial(n - 1)

    for i <- 0 to 10 do
        print(s"${factorial(i)} ")
    println()

    // tail-recursive version — annotate to verify the compiler optimises it
    import scala.annotation.tailrec

    @tailrec
    def factTail(n: Int, acc: Long = 1L): Long =
        if n <= 1 then acc
        else factTail(n - 1, acc * n)

    println(factTail(10))   // 3628800
    println(factTail(20))   // 2432902008176640000

    // =========================================================
    // Additional: composing pure functions
    // =========================================================

    // pure functions compose perfectly — output of one feeds into the next
    def double(n: Int): Int  = n * 2
    def addOne(n: Int): Int  = n + 1
    def negate(n: Int): Int  = -n

    // manual composition
    val result = negate(addOne(double(5)))   // negate(addOne(10)) = negate(11) = -11
    println(s"negate(addOne(double(5))) = $result")

    // using andThen / compose (Function1 methods)
    val pipeline = double.andThen(addOne).andThen(negate)
    println(s"pipeline(5) = ${pipeline(5)}")   // -11
