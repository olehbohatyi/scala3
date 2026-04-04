package module07collections

// Summary: Scala has several immutable sequence types — choose based on access pattern.
// List:     fast prepend/head/tail, slow random access — use for FP recursion.
// Vector:   fast random access AND fast append/prepend — the general-purpose default.
// LazyList: elements computed on demand — infinite sequences, streaming data.
// ArraySeq: immutable but backed by array — best random access, fixed size.
// Rule of thumb: when in doubt, use Vector. Use List only for recursive algorithms.

@main def otherSequences(): Unit =

    // =========================================================
    // Vector — the general-purpose immutable sequence
    // =========================================================

    val v = Vector(1, 2, 3, 4, 5)
    println(s"Vector = $v")

    // same API as List — methods are identical
    println(s"head = ${v.head}")
    println(s"tail = ${v.tail}")
    println(s"v(3) = ${v(3)}")    // fast O(log n) random access

    // updating by index is also O(log n) — returns new Vector
    val updated = v.updated(2, 99)
    println(s"updated = $updated")    // Vector(1, 2, 99, 4, 5)

    // append and prepend — both fast on Vector
    val v2 = v :+ 6
    val v3 = 0 +: v
    println(s"append  = $v2")     // Vector(1, 2, 3, 4, 5, 6)
    println(s"prepend = $v3")     // Vector(0, 1, 2, 3, 4, 5)

    // =========================================================
    // List vs Vector — performance characteristics
    // =========================================================

    // List:   prepend 0+: xs  = O(1)   xs :+ n  = O(n)  xs(i) = O(n)
    // Vector: prepend 0+: xs  = O(logn) xs :+ n = O(logn) xs(i) = O(logn)

    // List is better when: building by prepend + head/tail recursion
    // Vector is better when: frequent random access or append operations

    val list   = List(1, 2, 3)
    val vector = Vector(1, 2, 3)

    // both support the same functional methods
    println(s"list map   = ${list.map(_ * 2)}")   // List(2, 4, 6)
    println(s"vector map = ${vector.map(_ * 2)}") // Vector(2, 4, 6)

    // for expressions produce the same collection type as input
    val doubled  = for x <- list   yield x * 2   // List
    val vDoubled = for x <- vector yield x * 2   // Vector
    println(s"for list   = $doubled")
    println(s"for vector = $vDoubled")

    // =========================================================
    // LazyList (was Stream in Scala 2) — computed on demand
    // =========================================================

    // elements are only evaluated when accessed (lazy evaluation)
    val lazy1 = LazyList(1, 2, 3, 4, 5)
    println(s"LazyList head = ${lazy1.head}")   // 1
    println(s"LazyList take = ${lazy1.take(3).toList}")  // List(1, 2, 3)

    // infinite LazyList — only safe because elements are computed lazily
    // LazyList.from(n) generates: n, n+1, n+2, ...
    val naturals = LazyList.from(1)
    println(s"first 5 naturals: ${naturals.take(5).toList}")  // List(1,2,3,4,5)

    // infinite LazyList of even numbers via filter — safe!
    val evens = LazyList.from(1).filter(_ % 2 == 0)
    println(s"first 5 evens:    ${evens.take(5).toList}")     // List(2,4,6,8,10)

    // LazyList.iterate(seed)(f) — repeatedly apply f
    val powers = LazyList.iterate(1)(_ * 2)   // 1, 2, 4, 8, 16, ...
    println(s"powers of 2:      ${powers.take(8).toList}")

    // =========================================================
    // ArraySeq — immutable, array-backed, best random access
    // =========================================================

    import scala.collection.immutable.ArraySeq
    val arr = ArraySeq(10, 20, 30, 40, 50)
    println(s"\nArraySeq = $arr")
    println(s"arr(2)   = ${arr(2)}")           // 30  — O(1) access
    println(s"updated  = ${arr.updated(1, 99)}")  // ArraySeq(10, 99, 30, 40, 50)

    // =========================================================
    // Conversion between sequence types
    // =========================================================

    val myList   = List(1, 2, 3)
    val myVector = myList.toVector
    val myLazy   = myList.to(LazyList)
    val myArr    = myList.toArray           // becomes a mutable Array

    println(s"\nList -> Vector:    $myVector")
    println(s"List -> LazyList:  ${myLazy.toList}")
    println(s"List -> Array:     ${myArr.mkString("[", ", ", "]")}")

    // =========================================================
    // Summary table (as comments for quick reference)
    // =========================================================

    // | Type      | Prepend | Append  | Access  | Use case              |
    // |-----------|---------|---------|---------|---------------------- |
    // | List      | O(1)    | O(n)    | O(n)    | FP recursion          |
    // | Vector    | O(logn) | O(logn) | O(logn) | general purpose       |
    // | LazyList  | O(1)    | O(1)    | O(n)    | infinite / streaming  |
    // | ArraySeq  | O(n)    | O(n)    | O(1)    | fixed, read-heavy     |

    println("\nSee comments in source for performance comparison table")
