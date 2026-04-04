package module07collections

// Summary: map transforms every element — returns a new collection of the same size.
// filter keeps only elements where the predicate returns true — size may shrink.
// Both always return a NEW collection — the original is never modified.
// Chaining map + filter is the idiomatic Scala replacement for imperative loops.
// collect = filter + map in one pass using partial functions (pattern matching).

@main def mapAndFilter(): Unit =

    val nums   = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val words  = List("hello", "scala", "is", "great", "and", "fun")

    // =========================================================
    // map — transform every element
    // =========================================================

    // double every number
    val doubled = nums.map(_ * 2)
    println(s"doubled   = $doubled")    // List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)

    // square every number
    val squared = nums.map(n => n * n)
    println(s"squared   = $squared")    // List(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)

    // convert numbers to strings
    val asStr = nums.map(n => s"num-$n")
    println(s"asStr     = $asStr")

    // transform strings
    val upper = words.map(_.toUpperCase)
    println(s"upper     = $upper")

    val lengths = words.map(_.length)
    println(s"lengths   = $lengths")    // List(5, 5, 2, 5, 3, 3)

    // map can change the element type
    val parsed = List("1", "2", "3", "4").map(_.toInt)
    println(s"parsed    = $parsed")     // List(1, 2, 3, 4)

    // =========================================================
    // filter — keep elements matching a predicate
    // =========================================================

    // book example: keep elements greater than 5
    val bigNums = nums.filter(_ > 5)
    println(s"\nbigNums   = $bigNums")   // List(6, 7, 8, 9, 10)

    val evens = nums.filter(_ % 2 == 0)
    println(s"evens     = $evens")       // List(2, 4, 6, 8, 10)

    val odds  = nums.filter(_ % 2 != 0)
    println(s"odds      = $odds")        // List(1, 3, 5, 7, 9)

    // filterNot — keep elements where predicate is FALSE
    val noEvens = nums.filterNot(_ % 2 == 0)
    println(s"noEvens   = $noEvens")     // List(1, 3, 5, 7, 9)

    // filter on strings
    val longWords = words.filter(_.length > 3)
    println(s"longWords = $longWords")   // List(hello, scala, great)

    // =========================================================
    // Combining map + filter — the Scala idiom
    // =========================================================

    // book example: filter then map in a pipeline
    val result = nums.filter(_ > 5).map(_ * 3)
    println(s"\nfilter>5 then *3 = $result")   // List(18, 21, 24, 27, 30)

    // equivalent for expression
    val resultFor = for n <- nums if n > 5 yield n * 3
    println(s"same via for     = $resultFor")

    // process strings: keep long words, uppercase them
    val processed = words.filter(_.length > 3).map(_.toUpperCase)
    println(s"long+upper = $processed")

    // =========================================================
    // collect — filter + map in ONE pass (uses partial function)
    // =========================================================

    // collect applies a partial function — elements that don't match are dropped
    val mixed: List[Any] = List(1, "hello", 2, "world", 3, true)

    // extract only the integers
    val ints = mixed.collect { case n: Int => n }
    println(s"\ncollect ints   = $ints")   // List(1, 2, 3)

    // extract and transform strings only
    val strings = mixed.collect { case s: String => s.toUpperCase }
    println(s"collect strings = $strings")  // List(HELLO, WORLD)

    // collect with a guard
    val bigEvens = nums.collect { case n if n > 4 && n % 2 == 0 => n * 10 }
    println(s"collect bigEvens = $bigEvens")  // List(60, 80, 100)

    // =========================================================
    // partition — split into two Lists in one pass
    // =========================================================

    // returns (matchingElements, nonMatchingElements)
    val (evenList, oddList) = nums.partition(_ % 2 == 0)
    println(s"\nevens = $evenList")   // List(2, 4, 6, 8, 10)
    println(s"odds  = $oddList")     // List(1, 3, 5, 7, 9)

    // =========================================================
    // groupBy — group elements by a key function
    // =========================================================

    // returns Map[key, List[element]]
    val grouped = words.groupBy(_.length)
    println(s"\ngrouped by length = $grouped")
    // Map(5 -> List(hello, scala, great), 2 -> List(is), 3 -> List(and, fun))

    // =========================================================
    // Additional: map on a Map collection
    // =========================================================

    val prices = Map("apple" -> 1.5, "banana" -> 0.75, "cherry" -> 3.0)

    // map over Map entries — each entry is a (key, value) tuple
    val discounted = prices.map((item, price) => item -> price * 0.9)
    println(s"\ndiscounted = $discounted")

    // filter a Map
    val cheap = prices.filter((_, price) => price < 2.0)
    println(s"cheap      = $cheap")   // Map(apple -> 1.5, banana -> 0.75)
