package module08arraybuffer

import scala.collection.mutable.ArrayBuffer

// Summary: ArrayBuffer is the go-to mutable, resizable sequence in Scala.
// It is backed by an array under the hood — O(1) random access, O(1) amortized append.
// Unlike List (immutable), ArrayBuffer is MUTATED IN PLACE — no new collection returned.
// Prefer immutable collections by default; reach for ArrayBuffer when mutation is needed.
// Must import: scala.collection.mutable.ArrayBuffer

@main def arrayBufferBasics(): Unit =

    // =========================================================
    // Creating an ArrayBuffer
    // =========================================================

    // empty buffer with explicit type parameter
    val buf1 = ArrayBuffer[Int]()
    println(s"empty:   $buf1")   // ArrayBuffer()

    // buffer with initial values — type inferred
    val buf2 = ArrayBuffer(1, 2, 3, 4, 5)
    println(s"initial: $buf2")   // ArrayBuffer(1, 2, 3, 4, 5)

    // buffer of Strings
    val names = ArrayBuffer("Alice", "Bob", "Carol")
    println(s"names:   $names")

    // =========================================================
    // Initial population from other collections
    // =========================================================

    // from a List using .to()
    val fromList = List(10, 20, 30).to(ArrayBuffer)
    println(s"from List:   $fromList")   // ArrayBuffer(10, 20, 30)

    // from a Range
    val fromRange = (1 to 5).to(ArrayBuffer)
    println(s"from Range:  $fromRange")  // ArrayBuffer(1, 2, 3, 4, 5)

    // from Vector
    val fromVec = Vector("x", "y", "z").to(ArrayBuffer)
    println(s"from Vector: $fromVec")

    // ArrayBuffer.fill — n copies of a value
    val zeros = ArrayBuffer.fill(4)(0)
    println(s"fill:        $zeros")   // ArrayBuffer(0, 0, 0, 0)

    // ArrayBuffer.tabulate — element at index i = f(i)
    val squares = ArrayBuffer.tabulate(5)(n => n * n)
    println(s"tabulate:    $squares")  // ArrayBuffer(0, 1, 4, 9, 16)

    // =========================================================
    // Accessing elements — same as List/Vector
    // =========================================================

    val nums = ArrayBuffer(10, 20, 30, 40, 50)

    // by index — O(1) random access (faster than List!)
    println(s"\nnums(0) = ${nums(0)}")   // 10
    println(s"nums(2) = ${nums(2)}")   // 30
    println(s"nums(4) = ${nums(4)}")   // 50

    println(s"head    = ${nums.head}")  // 10
    println(s"last    = ${nums.last}")  // 50
    println(s"length  = ${nums.length}") // 5

    // =========================================================
    // Updating elements IN PLACE — this is mutation!
    // =========================================================

    val scores = ArrayBuffer(85, 90, 78, 92, 88)
    println(s"\nbefore update: $scores")

    // update by index using = assignment
    scores(2) = 100
    println(s"after update:  $scores")   // ArrayBuffer(85, 90, 100, 92, 88)

    // update multiple elements
    scores(0) = 95
    scores(4) = 99
    println(s"more updates:  $scores")   // ArrayBuffer(95, 90, 100, 92, 99)

    // =========================================================
    // Size and emptiness checks
    // =========================================================

    println(s"\nsize     = ${scores.size}")      // 5
    println(s"isEmpty  = ${scores.isEmpty}")     // false
    println(s"nonEmpty = ${scores.nonEmpty}")    // true
    println(s"empty?   = ${ArrayBuffer[Int]().isEmpty}") // true

    // =========================================================
    // Iterating — same methods as immutable collections
    // =========================================================

    // foreach
    names.foreach(n => print(s"$n "))
    println()

    // for loop
    for n <- nums do print(s"$n ")
    println()

    // zipWithIndex
    for (name, i) <- names.zipWithIndex do
        println(s"  [$i] $name")

    // =========================================================
    // Converting back to immutable
    // =========================================================

    val mutable = ArrayBuffer(3, 1, 4, 1, 5)
    val immList = mutable.toList
    val immVec  = mutable.toVector

    println(s"\ntoList   = $immList")    // List(3, 1, 4, 1, 5)
    println(s"toVector = $immVec")      // Vector(3, 1, 4, 1, 5)
    println(s"original = $mutable")     // unchanged ArrayBuffer
