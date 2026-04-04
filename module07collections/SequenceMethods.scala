package module07collections

// Summary: Scala collections have a rich API covering almost every operation you need.
// Knowing these methods is the difference between idiomatic Scala and Java-in-Scala.
// fold/reduce aggregate a collection into a single value.
// flatMap = map + flatten — essential for working with Option, List-of-Lists, etc.
// These methods are used identically in ZIO, Cats, and all FP libraries.

@main def sequenceMethods(): Unit =

    val nums  = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val words = List("hello", "world", "scala", "rocks")

    // =========================================================
    // take / drop / slice — extract sub-sequences
    // =========================================================

    println(s"take(3)      = ${nums.take(3)}")         // List(1, 2, 3)
    println(s"drop(3)      = ${nums.drop(3)}")         // List(4, 5, 6, 7, 8, 9, 10)
    println(s"takeRight(3) = ${nums.takeRight(3)}")    // List(8, 9, 10)
    println(s"dropRight(3) = ${nums.dropRight(3)}")    // List(1, 2, 3, 4, 5, 6, 7)
    println(s"slice(2,5)   = ${nums.slice(2, 5)}")     // List(3, 4, 5)  [2..4]

    // takeWhile / dropWhile — stop at first non-matching element
    println(s"takeWhile<5  = ${nums.takeWhile(_ < 5)}")  // List(1, 2, 3, 4)
    println(s"dropWhile<5  = ${nums.dropWhile(_ < 5)}")  // List(5, 6, 7, 8, 9, 10)

    // splitAt — split into two at an index
    val (front, back) = nums.splitAt(4)
    println(s"splitAt(4)   = $front  |  $back")

    // =========================================================
    // head / tail / last / init — structural access
    // =========================================================

    println(s"\nhead  = ${nums.head}")         // 1
    println(s"tail  = ${nums.tail}")           // List(2..10)
    println(s"last  = ${nums.last}")           // 10
    println(s"init  = ${nums.init}")           // List(1..9)

    // safe options — no exception on empty list
    println(s"headOption  = ${nums.headOption}")         // Some(1)
    println(s"lastOption  = ${nums.lastOption}")         // Some(10)
    println(s"headOption  = ${List.empty[Int].headOption}") // None

    // =========================================================
    // exists / forall / contains / count
    // =========================================================

    println(s"\nexists >8    = ${nums.exists(_ > 8)}")       // true
    println(s"forall >0    = ${nums.forall(_ > 0)}")         // true
    println(s"forall >5    = ${nums.forall(_ > 5)}")         // false
    println(s"contains 7   = ${nums.contains(7)}")           // true
    println(s"count even   = ${nums.count(_ % 2 == 0)}")     // 5

    // =========================================================
    // find / indexOf — searching
    // =========================================================

    // find returns the FIRST element matching the predicate
    println(s"\nfind >5      = ${nums.find(_ > 5)}")         // Some(6)
    println(s"find >100    = ${nums.find(_ > 100)}")         // None

    println(s"indexOf 5    = ${nums.indexOf(5)}")            // 4
    println(s"indexOf 99   = ${nums.indexOf(99)}")           // -1  (not found)

    // =========================================================
    // sum / product / min / max / minBy / maxBy
    // =========================================================

    println(s"\nsum     = ${nums.sum}")                              // 55
    println(s"product = ${nums.product}")                           // 3628800
    println(s"min     = ${nums.min}")                               // 1
    println(s"max     = ${nums.max}")                               // 10

    // minBy / maxBy — compare by a function
    println(s"minBy length = ${words.minBy(_.length)}")             // scala / rocks
    println(s"maxBy length = ${words.maxBy(_.length)}")             // hello / world

    // =========================================================
    // flatten — collapse one level of nesting
    // =========================================================

    val nested = List(List(1, 2), List(3, 4), List(5, 6))
    println(s"\nflatten  = ${nested.flatten}")   // List(1, 2, 3, 4, 5, 6)

    // flatten also works on List of Options — removes None
    val options = List(Some(1), None, Some(3), None, Some(5))
    println(s"flatten opts= ${options.flatten}") // List(1, 3, 5)

    // =========================================================
    // flatMap — map + flatten in one step
    // =========================================================

    // map then flatten
    val flat1 = nums.take(4).flatMap(n => List(n, n * 10))
    println(s"flatMap      = $flat1")   // List(1, 10, 2, 20, 3, 30, 4, 40)

    // flatMap on strings — split each word into chars
    val chars = words.take(2).flatMap(w => w.toList)
    println(s"chars        = $chars")   // List(h,e,l,l,o,w,o,r,l,d)

    // =========================================================
    // foldLeft / foldRight / reduce — aggregate to a single value
    // =========================================================

    // foldLeft: starts from the LEFT with an initial accumulator value
    // signature: foldLeft(initial)((acc, elem) => newAcc)
    val sumFold = nums.foldLeft(0)((acc, n) => acc + n)
    println(s"\nfoldLeft sum = $sumFold")    // 55

    // build a String from a list
    val sentence = words.foldLeft("")((acc, w) => if acc.isEmpty then w else s"$acc $w")
    println(s"foldLeft str = $sentence")    // hello world scala rocks

    // foldRight: starts from the RIGHT
    val reversed = nums.take(5).foldRight(List.empty[Int])((n, acc) => n :: acc)
    println(s"foldRight    = $reversed")    // List(1, 2, 3, 4, 5)

    // reduce: like foldLeft but uses the first element as the initial value
    // throws if the list is empty — use reduceOption for safety
    val sumReduce = nums.reduce(_ + _)
    println(s"reduce sum   = $sumReduce")   // 55

    val maxReduce = nums.reduceOption(_ max _)
    println(s"reduceOption = $maxReduce")   // Some(10)

    // =========================================================
    // zip / unzip / zipWithIndex
    // =========================================================

    val a = List(1, 2, 3)
    val b = List("one", "two", "three")

    val zipped = a.zip(b)
    println(s"\nzip          = $zipped")   // List((1,one),(2,two),(3,three))

    val (ns, ss) = zipped.unzip
    println(s"unzip nums   = $ns")         // List(1, 2, 3)
    println(s"unzip strs   = $ss")         // List(one, two, three)

    // zipWithIndex — pair each element with its position
    words.zipWithIndex.foreach((w, i) => println(s"  [$i] $w"))

    // =========================================================
    // mkString — join elements into a String
    // =========================================================

    println(s"\nmkString     = ${nums.take(5).mkString}")          // 12345
    println(s"mkString sep = ${nums.take(5).mkString(", ")}")      // 1, 2, 3, 4, 5
    println(s"mkString full= ${nums.take(5).mkString("[", ",", "]")}") // [1,2,3,4,5]

    // =========================================================
    // Additional: sliding and grouped windows
    // =========================================================

    // sliding(n): a moving window of size n
    val windows = nums.take(5).sliding(3).toList
    println(s"\nsliding(3)   = $windows")   // List(List(1,2,3),List(2,3,4),List(3,4,5))

    // grouped(n): fixed-size chunks (no overlap)
    val chunks = nums.grouped(3).toList
    println(s"grouped(3)   = $chunks")      // List(List(1,2,3),List(4,5,6),List(7,8,9),List(10))
