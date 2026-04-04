package module10functions

// Summary: By-name parameters delay evaluation until the value is actually used.
// This is useful for control abstractions, lazy fallback values, and structured logging helpers.
// The parameter behaves like a computation that can be invoked many times inside the method body.
// Scala uses this feature in several APIs where eager evaluation would be wasteful or misleading.
// This file demonstrates delayed evaluation and a small timing wrapper.

@main def byNameParameters(): Unit =

    // =========================================================
    // A by-name parameter is evaluated only when needed
    // =========================================================

    def choose(debug: Boolean, value: => String): String =
        if debug then s"debug:$value" else "debug disabled"

    def expensiveMessage(): String =
        println("computing message")
        "scala-3"

    println(choose(debug = false, expensiveMessage()))
    println(choose(debug = true, expensiveMessage()))

    // =========================================================
    // A small utility wrapper using by-name evaluation
    // =========================================================

    def measure[A](block: => A): (A, Long) =
        val start = System.nanoTime()
        val result = block
        val end = System.nanoTime()
        (result, end - start)

    val (sum, elapsed) = measure((1 to 1000).sum)
    println(sum)
    println(elapsed > 0)
