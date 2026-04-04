package module07collections

// Summary: A Range is a lazy sequence of evenly-spaced integers — no memory allocated.
// Ranges are created with 'to' (inclusive), 'until' (exclusive), and 'by' (step).
// They are Iterable — use in for loops, map, filter, or convert to other collections.
// Ranges support all sequence methods: sum, max, filter, map, etc.
// Char ranges work too: 'a' to 'z' generates letters.

@main def ranges(): Unit =

    // =========================================================
    // Creating ranges — to, until, by
    // =========================================================

    // 'to' — inclusive on both ends
    val r1 = 1 to 10
    println(s"1 to 10     = $r1")         // Range 1 to 10
    println(s"as list     = ${r1.toList}") // List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // 'until' — exclusive on the right end
    val r2 = 1 until 10
    println(s"1 until 10  = ${r2.toList}") // List(1, 2, 3, 4, 5, 6, 7, 8, 9)

    // 'by' — custom step size
    val odds  = 1 to 20 by 2
    val evens = 2 to 20 by 2
    println(s"odds  = ${odds.toList}")     // List(1, 3, 5, 7, 9, 11, 13, 15, 17, 19)
    println(s"evens = ${evens.toList}")    // List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)

    // step of 5
    val fives = 0 to 50 by 5
    println(s"fives = ${fives.toList}")    // List(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50)

    // counting DOWN — negative step
    val countdown = 10 to 1 by -1
    println(s"down  = ${countdown.toList}") // List(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)

    // =========================================================
    // Range properties — it's just a compact descriptor
    // =========================================================

    val big = 1 to 1_000_000
    println(s"\nbig range size  = ${big.size}")    // 1000000
    println(s"memory?         = Range, not a List — lazy!")
    println(s"big.head        = ${big.head}")      // 1
    println(s"big.last        = ${big.last}")      // 1000000

    // =========================================================
    // Using Range in for loops
    // =========================================================

    // standard for loop over a range
    for i <- 1 to 5 do print(s"$i ")
    println()

    // nested loops using ranges
    for
        i <- 1 to 3
        j <- 1 to 3
    do print(f"(${i},${j}) ")
    println()

    // =========================================================
    // Applying collection methods directly on Range
    // =========================================================

    // Ranges support map, filter, sum, etc. without converting first
    val sumTo100 = (1 to 100).sum
    println(s"\nsum 1..100  = $sumTo100")    // 5050

    val evenSum = (2 to 100 by 2).sum
    println(s"even sum    = $evenSum")        // 2550

    val squares = (1 to 5).map(n => n * n)
    println(s"squares     = $squares")        // Vector(1, 4, 9, 16, 25)

    val bigNums = (1 to 20).filter(_ > 15)
    println(s"filter >15  = $bigNums")        // Vector(16, 17, 18, 19, 20)

    // =========================================================
    // Converting Range to other collections
    // =========================================================

    val asList   = (1 to 5).toList
    val asVector = (1 to 5).toVector
    val asSet    = (1 to 5).toSet
    val asArray  = (1 to 5).toArray

    println(s"\ntoList   = $asList")
    println(s"toVector = $asVector")
    println(s"toSet    = $asSet")
    println(s"toArray  = ${asArray.mkString("[", ", ", "]")}")

    // =========================================================
    // Char ranges — letters and ASCII
    // =========================================================

    val letters = ('a' to 'z').toList
    println(s"\nletters  = ${letters.mkString}")   // abcdefghijklmnopqrstuvwxyz

    val upper = ('A' to 'Z').toList
    println(s"upper    = ${upper.mkString}")        // ABCDEFGHIJKLMNOPQRSTUVWXYZ

    // every other letter
    val everyOther = ('a' to 'z' by 2).toList
    println(s"every 2nd= ${everyOther.mkString}")   // acegikmoqsuwy

    // =========================================================
    // Creating collections FROM ranges — List.tabulate and fill
    // =========================================================

    // List.fill(n)(value) — n copies of a value
    val tenZeros = List.fill(10)(0)
    println(s"\nfill     = $tenZeros")

    // List.tabulate(n)(f) — element at index i = f(i)
    val powers = List.tabulate(8)(i => math.pow(2, i).toInt)
    println(s"tabulate = $powers")   // List(1, 2, 4, 8, 16, 32, 64, 128)

    // Vector.tabulate
    val grid = Vector.tabulate(3, 3)((row, col) => row * 3 + col)
    println(s"2D tabulate = $grid")  // Vector(Vector(0,1,2),Vector(3,4,5),Vector(6,7,8))

    // =========================================================
    // Additional: Range in practical use — indices for a List
    // =========================================================

    val items = List("alpha", "beta", "gamma", "delta")

    // iterate by index using indices property
    for i <- items.indices do
        println(s"  [$i] ${items(i)}")

    // items.indices returns: Range 0 until 4
    println(s"indices = ${items.indices}")
