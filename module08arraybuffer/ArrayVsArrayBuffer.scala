package module08arraybuffer

import scala.collection.mutable.ArrayBuffer

// Summary: Array is Scala's wrapper around Java's primitive array — fixed size, mutable values.
// ArrayBuffer is a resizable array — dynamic size, mutable values.
// Array gives the best raw performance but cannot grow or shrink.
// Use Array for interop with Java APIs and performance-critical code.
// Use ArrayBuffer when you need to build up a collection and then freeze it.

@main def arrayVsArrayBuffer(): Unit =

    // =========================================================
    // Array — fixed size, backed by JVM primitive array
    // =========================================================

    // create with explicit size — all elements initialised to default (0 for Int)
    val arr1 = new Array[Int](5)
    println(s"empty Array[Int]: ${arr1.mkString("[", ", ", "]")}")  // [0, 0, 0, 0, 0]

    // create with values — size is fixed at creation time
    val arr2 = Array(10, 20, 30, 40, 50)
    println(s"with values:      ${arr2.mkString("[", ", ", "]")}")  // [10, 20, 30, 40, 50]

    // Array of Strings
    val strs = Array("hello", "world", "scala")
    println(s"strings:          ${strs.mkString("[", ", ", "]")}")

    // =========================================================
    // Accessing and updating Array elements
    // =========================================================

    // read by index — O(1)
    println(s"\narr2(0) = ${arr2(0)}")   // 10
    println(s"arr2(3) = ${arr2(3)}")   // 40

    // UPDATE by index — in place mutation, same as ArrayBuffer
    arr2(2) = 99
    println(s"after arr2(2)=99: ${arr2.mkString("[", ", ", "]")}")  // [10, 20, 99, 40, 50]

    // =========================================================
    // Array CANNOT grow or shrink — fixed at creation
    // =========================================================

    // arr2 += 60   // this does NOT compile — Array has no += method
    // The only way to "resize" is to create a new Array
    val bigger = arr2 :+ 60   // returns a new Array
    println(s"new bigger: ${bigger.mkString("[", ", ", "]")}")

    // =========================================================
    // Array supports all functional methods — returns Array
    // =========================================================

    val nums = Array(1, 2, 3, 4, 5)

    val doubled  = nums.map(_ * 2)
    val evens    = nums.filter(_ % 2 == 0)
    val total    = nums.sum
    val sorted   = Array(3, 1, 4, 1, 5).sorted   // new sorted Array

    println(s"\nnums doubled: ${doubled.mkString(", ")}")  // 2, 4, 6, 8, 10
    println(s"evens:        ${evens.mkString(", ")}")      // 2, 4
    println(s"sum:          $total")                        // 15
    println(s"sorted:       ${sorted.mkString(", ")}")     // 1, 1, 3, 4, 5

    // =========================================================
    // Converting between Array, ArrayBuffer, List
    // =========================================================

    val list   = List(1, 2, 3)
    val array  = list.toArray
    val buffer = list.to(ArrayBuffer)

    println(s"\nList  -> Array:       ${array.mkString("[", ", ", "]")}")
    println(s"List  -> ArrayBuffer: $buffer")
    println(s"Array -> List:        ${array.toList}")
    println(s"Array -> ArrayBuffer: ${array.to(ArrayBuffer)}")
    println(s"ArrayBuffer -> Array: ${buffer.toArray.mkString("[", ", ", "]")}")

    // =========================================================
    // 2D Array — array of arrays (matrix)
    // =========================================================

    // create a 3x3 matrix
    val matrix = Array.ofDim[Int](3, 3)

    // fill with values
    for
        i <- 0 until 3
        j <- 0 until 3
    do
        matrix(i)(j) = i * 3 + j + 1

    // print matrix
    println("\n3x3 matrix:")
    for row <- matrix do
        println(s"  ${row.mkString("[", ", ", "]")}")

    // =========================================================
    // Comparison: when to use which
    // =========================================================

    // | Collection   | Mutable? | Resizable? | Best for                        |
    // |--------------|----------|------------|---------------------------------|
    // | List         |    No    |     No     | FP, recursion, immutable default |
    // | Vector       |    No    |     No     | General immutable, fast access  |
    // | ArrayBuffer  |   Yes    |    Yes     | Building up data imperatively   |
    // | Array        |   Yes    |     No     | Java interop, raw performance   |

    println("\nSee comparison table in source comments")

    // =========================================================
    // Additional: Array.fill and Array.tabulate
    // =========================================================

    val filled    = Array.fill(5)("*")
    val tabulated = Array.tabulate(6)(i => i * i)

    println(s"fill:     ${filled.mkString("[", ", ", "]")}")       // [*, *, *, *, *]
    println(s"tabulate: ${tabulated.mkString("[", ", ", "]")}")    // [0, 1, 4, 9, 16, 25]

    // =========================================================
    // Additional: sort Array in place (mutation)
    // =========================================================

    val unsorted = Array(5, 3, 8, 1, 9, 2)
    println(s"\nbefore sort: ${unsorted.mkString(", ")}")
    scala.util.Sorting.quickSort(unsorted)   // sorts IN PLACE
    println(s"after sort:  ${unsorted.mkString(", ")}")
