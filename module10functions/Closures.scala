package module10functions

// Summary: A closure is a function that captures (closes over) variables from its enclosing scope.
// The captured variable becomes part of the function's environment — it "remembers" the value.
// Closures over val: safe — the value is immutable, always the same.
// Closures over var: the function sees the CURRENT value at call time — can be surprising.
// Closures are how Scala implements adders, counters, and generators without classes.

@main def closures(): Unit =

    // =========================================================
    // Basic closure — captures a val from outer scope
    // =========================================================

    val multiplier = 3
    // this lambda "closes over" multiplier from the enclosing scope
    val timesThree: Int => Int = n => n * multiplier

    println(timesThree(5))    // 15
    println(timesThree(10))   // 30

    // multiplier is captured — even though it's not a parameter
    val factor = 7
    val timesFactor = (n: Int) => n * factor
    println(timesFactor(6))   // 42

    // =========================================================
    // Closure created by a function — captures the parameter
    // =========================================================

    // each call to makeAdder creates a NEW closure with its own 'n'
    def makeAdder(n: Int): Int => Int = x => x + n

    val add3  = makeAdder(3)
    val add10 = makeAdder(10)
    val add99 = makeAdder(99)

    println(add3(5))    // 8   — captured n=3
    println(add10(5))   // 15  — captured n=10
    println(add99(5))   // 104 — captured n=99

    // 'n' in each closure is independent — different closures, different environments
    println(add3(100) + add10(100))  // 103 + 110 = 213

    // =========================================================
    // Closure captures a var — sees the CURRENT value at call time
    // =========================================================

    var base = 100
    val addBase = (n: Int) => n + base   // closes over 'base'

    println(addBase(5))   // 105  (base=100)

    base = 200            // mutate the captured var
    println(addBase(5))   // 205  (base=200 now!)

    // this is why closing over vars can be dangerous — hidden mutation
    base = 100            // reset for clarity

    // =========================================================
    // Counter factory — stateful closure via var
    // =========================================================

    // each call creates a fresh local 'count' — independent counters
    def makeCounter(): () => Int =
        var count = 0
        () =>
            count += 1
            count

    val c1 = makeCounter()
    val c2 = makeCounter()

    println(c1())   // 1
    println(c1())   // 2
    println(c1())   // 3
    println(c2())   // 1  — c2 has its OWN count, independent of c1
    println(c2())   // 2
    println(c1())   // 4  — c1 continues from where it left off

    // =========================================================
    // Accumulator — closure that builds up a value
    // =========================================================

    def makeAccumulator(initial: Int): Int => Int =
        var total = initial
        n =>
            total += n
            total

    val acc = makeAccumulator(0)
    println(acc(10))   // 10
    println(acc(20))   // 30
    println(acc(5))    // 35

    // =========================================================
    // Generator — closure that produces a sequence
    // =========================================================

    def fibonacci(): () => Int =
        var (a, b) = (0, 1)
        () =>
            val result = a
            val next   = a + b
            a = b
            b = next
            result

    val fib = fibonacci()
    val fibSeq = List.fill(10)(fib())
    println(s"fibonacci: $fibSeq")   // List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)

    // =========================================================
    // Closure in collection methods
    // =========================================================

    // the lambda passed to map, filter, etc. can close over outer vals
    val threshold = 5
    val nums      = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val bigNums = nums.filter(n => n > threshold)   // closes over threshold
    println(s"bigNums: $bigNums")   // List(6, 7, 8, 9, 10)

    val prefix = "item"
    val labels = nums.take(3).map(n => s"$prefix-$n")   // closes over prefix
    println(s"labels:  $labels")    // List(item-1, item-2, item-3)

    // =========================================================
    // Additional: memoize — closure as a cache
    // =========================================================

    import scala.collection.mutable
    def memoize[A, B](f: A => B): A => B =
        val cache = mutable.Map[A, B]()
        // returned lambda closes over both 'f' and 'cache'
        a => cache.getOrElseUpdate(a, f(a))

    def slowSquare(n: Int): Int =
        Thread.sleep(0)   // simulate slow computation
        n * n

    val fastSquare = memoize(slowSquare)
    println(fastSquare(7))    // 49 — computed and cached
    println(fastSquare(7))    // 49 — from cache (no recomputation)
    println(fastSquare(12))   // 144
