package module10functions

// Summary: Currying transforms a function of N arguments into N nested functions of 1 argument.
// In Scala: multiple parameter lists  def f(a: Int)(b: Int) — each () is one "curry level".
// Calling with fewer lists returns a function waiting for the rest.
// Key use cases: DSLs, type inference helpers, implicit/using parameters, custom control flow.
// Currying and partial application are related but distinct — know the difference for interviews.

@main def currying(): Unit =

    // =========================================================
    // Curried function syntax — multiple parameter lists
    // =========================================================

    // single-list (normal)
    def addNormal(a: Int, b: Int): Int = a + b

    // curried — two parameter lists
    def addCurried(a: Int)(b: Int): Int = a + b

    println(addNormal(3, 4))    // 7
    println(addCurried(3)(4))   // 7  — same result, different call syntax

    // =========================================================
    // Partial application of a curried function
    // =========================================================

    // supply the first list → returns a function waiting for the second
    val add10: Int => Int = addCurried(10)
    val add5:  Int => Int = addCurried(5)

    println(add10(7))    // 17
    println(add5(7))     // 12

    // =========================================================
    // Practical: curried multiplier and clamper
    // =========================================================

    def multiply(factor: Int)(n: Int): Int = n * factor
    def between(lo: Int)(hi: Int)(n: Int): Boolean = n >= lo && n <= hi

    val triple  = multiply(3)
    val times10 = multiply(10)

    println(triple(5))              // 15
    println(List(1,2,3).map(triple))   // List(3, 6, 9)
    println(List(1,2,3).map(times10))  // List(10, 20, 30)

    val isAdult    = between(18)(120)
    val isValidPct = between(0)(100)

    println(isAdult(25))     // true
    println(isAdult(15))     // false
    println(isValidPct(101)) // false

    // =========================================================
    // Currying vs partial application — key difference
    // =========================================================

    // Partial application: use _ to fix some args of a NORMAL function
    def addNorm(a: Int, b: Int): Int = a + b
    val pa: Int => Int = addNorm(10, _)    // partial application

    // Currying: multiple parameter lists — apply one list at a time
    def addCur(a: Int)(b: Int): Int = a + b
    val ca: Int => Int = addCur(10)        // currying — no _ needed

    println(pa(5))   // 15
    println(ca(5))   // 15   — same result, different mechanism

    // =========================================================
    // Currying for type inference — a common Scala pattern
    // =========================================================

    // when the compiler can't infer the type from the first param list,
    // a second list helps: the first list fixes the type, the second uses it
    def foldWith[A, B](list: List[A])(init: B)(f: (B, A) => B): B =
        list.foldLeft(init)(f)

    val sum     = foldWith(List(1, 2, 3, 4, 5))(0)(_ + _)
    val product = foldWith(List(1, 2, 3, 4, 5))(1)(_ * _)
    val concat  = foldWith(List("a", "b", "c"))("")(_ + _)

    println(s"sum=$sum  product=$product  concat=$concat")  // 15  120  abc

    // =========================================================
    // Currying for custom control structures (DSL pattern)
    // =========================================================

    // 'repeat' looks like a language keyword: repeat(3) { code }
    def repeat(n: Int)(body: => Unit): Unit =
        for _ <- 1 to n do body

    repeat(3) {
        println("  hello from repeat!")
    }

    // 'timeit' — measures execution time
    def timeit(label: String)(block: => Unit): Long =
        val start = System.nanoTime()
        block
        val end   = System.nanoTime()
        val ms    = (end - start) / 1_000_000
        println(s"$label took ${ms}ms")
        ms

    timeit("sum 1..1M") {
        val _ = (1 to 1_000_000).sum
    }

    // =========================================================
    // Converting between curried and uncurried
    // =========================================================

    // .curried — convert a normal function to curried form
    val addUncurried: (Int, Int) => Int = (a, b) => a + b
    val addCurriedFn: Int => Int => Int = addUncurried.curried

    val incr = addCurriedFn(1)
    println(incr(41))   // 42

    // .uncurried (via Function.uncurried) — back to normal
    val addBack: (Int, Int) => Int = Function.uncurried(addCurriedFn)
    println(addBack(3, 4))   // 7

    // =========================================================
    // Additional: curried function with 3 levels
    // =========================================================

    def buildUrl(protocol: String)(host: String)(path: String): String =
        s"$protocol://$host/$path"

    // fix protocol
    val https = buildUrl("https")
    val http  = buildUrl("http")

    // fix protocol + host
    val myApi = https("api.example.com")

    println(myApi("users"))          // https://api.example.com/users
    println(myApi("users/42"))       // https://api.example.com/users/42
    println(http("localhost:8080")("health"))  // http://localhost:8080/health
