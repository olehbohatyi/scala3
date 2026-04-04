package module07collections

// Summary: List is the default immutable, singly-linked sequence in Scala.
// All "update" operations return a NEW List — the original is never modified.
// Internally List is built from Cons cells: head :: tail, terminated by Nil.
// Prepend (0 +: xs) is O(1); append (xs :+ n) is O(n) — use prepend + reverse.
// List is ideal for recursive algorithms and functional transformations.

@main def listClass(): Unit =

    // =========================================================
    // Creating a List
    // =========================================================

    // apply syntax — the standard way
    val nums = List(1, 2, 3, 4, 5)
    println(s"nums = $nums")

    // using :: (cons) operator and Nil — how List is really built
    // 1 :: 2 :: 3 :: Nil  is identical to List(1, 2, 3)
    val built = 1 :: 2 :: 3 :: Nil
    println(s"built = $built")

    // empty list
    val empty: List[Int] = List()
    val alsoEmpty: List[Int] = Nil
    println(s"empty = $empty, isNil = $alsoEmpty")

    // List of Strings
    val fruits = List("apple", "banana", "cherry")
    println(s"fruits = $fruits")

    // List.fill — repeat a value n times
    val zeros = List.fill(5)(0)
    println(s"zeros = $zeros")   // List(0, 0, 0, 0, 0)

    // List.tabulate — create by index function
    val squares = List.tabulate(6)(n => n * n)
    println(s"squares = $squares")   // List(0, 1, 4, 9, 16, 25)

    // =========================================================
    // Accessing elements
    // =========================================================

    // head and tail — the fundamental List operations
    println(s"head = ${nums.head}")   // 1
    println(s"tail = ${nums.tail}")   // List(2, 3, 4, 5)

    // by index — O(n), use Vector if random access is frequent
    println(s"nums(0) = ${nums(0)}")   // 1
    println(s"nums(2) = ${nums(2)}")   // 3

    // last and init — O(n) on List
    println(s"last = ${nums.last}")   // 5
    println(s"init = ${nums.init}")   // List(1, 2, 3, 4)

    // safe head/last — returns Option, no exception on empty list
    println(s"headOption = ${nums.headOption}")   // Some(1)
    println(s"headOption = ${empty.headOption}")  // None

    // =========================================================
    // List size and checks
    // =========================================================

    println(s"length   = ${nums.length}")    // 5
    println(s"size     = ${nums.size}")      // 5 (same as length)
    println(s"isEmpty  = ${nums.isEmpty}")   // false
    println(s"nonEmpty = ${nums.nonEmpty}")  // true

    // =========================================================
    // Updating — all ops return a NEW List
    // =========================================================

    // append one element at the end — O(n), returns new List
    val appended = nums :+ 6
    println(s"appended  = $appended")    // List(1, 2, 3, 4, 5, 6)
    println(s"nums unchanged = $nums")   // List(1, 2, 3, 4, 5) — original untouched

    // prepend one element at the front — O(1)
    val prepended = 0 +: nums
    println(s"prepended = $prepended")   // List(0, 1, 2, 3, 4, 5)

    // prepend with :: (cons) — identical to +:
    val consed = 0 :: nums
    println(s"consed    = $consed")      // List(0, 1, 2, 3, 4, 5)

    // update one element by index — returns new List
    val updated = nums.updated(2, 99)
    println(s"updated(2,99) = $updated") // List(1, 2, 99, 4, 5)

    // concatenate two Lists with :::  or  ++
    val moreNums = List(6, 7, 8)
    println(s"nums ::: more = ${nums ::: moreNums}")  // List(1..8)
    println(s"nums ++ more  = ${nums ++ moreNums}")   // same result

    // =========================================================
    // Pattern matching on List structure (preview of Module 12)
    // =========================================================

    def describe(xs: List[Int]): String = xs match
        case Nil       => "empty"
        case h :: Nil  => s"one element: $h"
        case h :: t    => s"head=$h, tail=$t"

    println(describe(List()))          // empty
    println(describe(List(42)))        // one element: 42
    println(describe(List(1, 2, 3)))   // head=1, tail=List(2, 3)

    // =========================================================
    // Additional: sorting and reversing
    // =========================================================

    val unsorted = List(3, 1, 4, 1, 5, 9, 2, 6)
    println(s"sorted   = ${unsorted.sorted}")    // List(1, 1, 2, 3, 4, 5, 6, 9)
    println(s"reversed = ${unsorted.reverse}")   // List(6, 2, 9, 5, 1, 4, 1, 3)
    println(s"distinct = ${unsorted.distinct}")  // List(3, 1, 4, 5, 9, 2, 6)

    // sort with custom comparator
    val words = List("banana", "apple", "cherry", "date")
    println(s"by length = ${words.sortBy(_.length)}")      // date, apple, banana, cherry
    println(s"descending= ${words.sortWith(_.length > _.length)}")
