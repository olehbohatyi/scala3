package module05constructs

// Summary: for/yield is a for EXPRESSION — it builds and returns a new collection.
// The result type mirrors the input collection type (List -> List, Vector -> Vector).
// Multiple generators produce a cartesian product (all combinations).
// if guards inside for filter out elements before yielding.
// For expressions are syntactic sugar for nested flatMap + map + filter calls.

@main def forExpressions(): Unit =

    // =========================================================
    // Basic for/yield — transforms a collection into a new one
    // =========================================================

    val numbers = List(1, 2, 3, 4, 5)

    // yield doubles every element — returns a NEW List
    val doubled = for n <- numbers yield n * 2
    println(s"doubled: $doubled")       // List(2, 4, 6, 8, 10)

    // yield can return a different type
    val asStrings = for n <- numbers yield s"item-$n"
    println(s"strings: $asStrings")     // List(item-1, ..., item-5)

    // yield with a computed expression
    val squares = for n <- numbers yield n * n
    println(s"squares: $squares")       // List(1, 4, 9, 16, 25)

    // =========================================================
    // Equivalence: for/yield == map
    // =========================================================

    // these three are identical — for/yield is syntactic sugar for map
    val a = for n <- numbers yield n * 10
    val b = numbers.map(n => n * 10)
    val c = numbers.map(_ * 10)
    println(s"for/yield : $a")
    println(s".map lambda: $b")
    println(s".map short : $c")   // all three: List(10, 20, 30, 40, 50)

    // =========================================================
    // if GUARD — filters elements before yielding
    // =========================================================

    // only yield even numbers
    val evens = for n <- numbers if n % 2 == 0 yield n
    println(s"evens: $evens")   // List(2, 4)

    // combine transform + guard: square only odds
    val squaredOdds = for n <- numbers if n % 2 != 0 yield n * n
    println(s"squared odds: $squaredOdds")   // List(1, 9, 25)

    // equivalent using filter + map
    val squaredOddsFP = numbers.filter(_ % 2 != 0).map(n => n * n)
    println(s"same via FP:  $squaredOddsFP") // List(1, 9, 25)

    // =========================================================
    // Multiple generators — Cartesian product (all combinations)
    // =========================================================

    // every combination of x and y
    val pairs = for
        x <- 1 to 3
        y <- 1 to 3
    yield (x, y)
    println(s"all pairs:   $pairs")
    // Vector((1,1),(1,2),(1,3),(2,1),(2,2),(2,3),(3,1),(3,2),(3,3))

    // =========================================================
    // Multiple generators + guard — filter the combinations
    // =========================================================

    // book example: pairs where x != y
    val distinctPairs = for
        x <- 1 to 3
        y <- 1 to 3
        if x != y
    yield (x, y)
    println(s"x!=y pairs:  $distinctPairs")
    // Vector((1,2),(1,3),(2,1),(2,3),(3,1),(3,2))

    // Pythagorean triples up to 20
    val triples = for
        a <- 1 to 20
        b <- a to 20        // b >= a avoids duplicates
        c <- b to 20        // c >= b
        if a * a + b * b == c * c
    yield (a, b, c)
    println(s"Pythagorean triples: $triples")

    // =========================================================
    // for/yield over a List of Strings
    // =========================================================

    val words = List("hello", "world", "scala", "rocks")

    // capitalise each word
    val capped = for w <- words yield w.capitalize
    println(s"capped: $capped")   // List(Hello, World, Scala, Rocks)

    // keep only words longer than 4 chars, uppercase them
    val long = for w <- words if w.length > 4 yield w.toUpperCase
    println(s"long:   $long")     // List(HELLO, WORLD, SCALA, ROCKS)

    // =========================================================
    // for/yield with nested generator — flatten nested lists
    // =========================================================

    val nested = List(List(1, 2), List(3, 4), List(5, 6))

    // second generator iterates over the INNER list
    val flat = for
        inner <- nested
        elem  <- inner
    yield elem
    println(s"flattened: $flat")   // List(1, 2, 3, 4, 5, 6)

    // equivalent: nested.flatten
    println(s"flatten:   ${nested.flatten}")

    // =========================================================
    // Additional: for expression preserves collection type
    // =========================================================

    val vec    = Vector(1, 2, 3)
    val vecOut = for v <- vec yield v * 100
    println(s"Vector in -> Vector out: $vecOut")   // Vector(100, 200, 300)

    val range    = 1 to 5
    val rangeOut = for r <- range yield r * r
    println(s"Range in -> IndexedSeq out: $rangeOut")   // Vector(1, 4, 9, 16, 25)
