package module08arraybuffer

import scala.collection.mutable.ArrayBuffer

// Summary: ArrayBuffer mutation operators: += (append), ++= (append many),
// prepend, insert, -= (remove by value), remove (by index), clear.
// All these operations mutate the buffer IN PLACE and return Unit (or the buffer).
// ArrayBuffer also supports all functional methods: map, filter, sorted, etc.
// — but those return a NEW ArrayBuffer, not mutating the original.

@main def arrayBufferOperations(): Unit =

    // =========================================================
    // Adding elements
    // =========================================================

    val buf = ArrayBuffer(1, 2, 3)
    println(s"initial: $buf")

    // += : append one element to the end
    buf += 4
    println(s"after +=4:       $buf")   // ArrayBuffer(1, 2, 3, 4)

    // += chained
    buf += 5 += 6
    println(s"after +=5+=6:    $buf")   // ArrayBuffer(1, 2, 3, 4, 5, 6)

    // ++= : append all elements from another collection
    buf ++= List(7, 8, 9)
    println(s"after ++=List:   $buf")   // ArrayBuffer(1..9)

    buf ++= Vector(10, 11)
    println(s"after ++=Vector: $buf")   // ArrayBuffer(1..11)

    // prepend(x) : add ONE element at the FRONT
    buf.prepend(0)
    println(s"after prepend 0: $buf")   // ArrayBuffer(0, 1..11)

    // prependAll : add many at the front
    buf.prependAll(List(-2, -1))
    println(s"after prependAll:$buf")   // ArrayBuffer(-2,-1,0,1..11)

    // insert(index, value) : insert at a specific position
    val small = ArrayBuffer(1, 2, 4, 5)
    small.insert(2, 3)    // insert 3 at index 2
    println(s"\nafter insert(2,3): $small")   // ArrayBuffer(1, 2, 3, 4, 5)

    // insertAll(index, collection) : insert multiple
    val gaps = ArrayBuffer(1, 5, 9)
    gaps.insertAll(1, List(2, 3, 4))
    println(s"insertAll:         $gaps")    // ArrayBuffer(1, 2, 3, 4, 5, 9)

    // =========================================================
    // Removing elements
    // =========================================================

    val data = ArrayBuffer(10, 20, 30, 20, 40, 20, 50)
    println(s"\ninitial: $data")

    // -= : remove the FIRST occurrence of a value
    data -= 20
    println(s"after -=20:         $data")   // ArrayBuffer(10, 30, 20, 40, 20, 50)

    // --= : remove all occurrences of values in a collection
    data --= List(20, 50)
    println(s"after --=List(20,50): $data") // ArrayBuffer(10, 30, 40)

    // remove(index) : remove element AT a specific index, returns the removed value
    val nums = ArrayBuffer(100, 200, 300, 400, 500)
    val removed = nums.remove(2)    // removes index 2 (value 300)
    println(s"removed: $removed")   // 300
    println(s"after remove(2): $nums") // ArrayBuffer(100, 200, 400, 500)

    // remove(index, count) : remove 'count' elements starting at index
    val bulk = ArrayBuffer(1, 2, 3, 4, 5, 6, 7)
    bulk.remove(2, 3)    // remove 3 elements starting at index 2
    println(s"remove(2,3): $bulk")  // ArrayBuffer(1, 2, 6, 7)

    // clear() : remove ALL elements
    val temp = ArrayBuffer(1, 2, 3)
    temp.clear()
    println(s"after clear: $temp")  // ArrayBuffer()

    // =========================================================
    // Other useful ArrayBuffer methods
    // =========================================================

    val arr = ArrayBuffer(3, 1, 4, 1, 5, 9, 2, 6, 5)

    // sortInPlace() — sorts the buffer IN PLACE (mutation!)
    arr.sortInPlace()
    println(s"\nsortInPlace: $arr")   // ArrayBuffer(1, 1, 2, 3, 4, 5, 5, 6, 9)

    // sortInPlaceBy — sort in place by a key function
    val words = ArrayBuffer("banana", "apple", "cherry", "date")
    words.sortInPlaceBy(_.length)
    println(s"sortInPlaceBy len: $words")  // ArrayBuffer(date, apple, banana, cherry)

    // reverseInPlace — reverse in place
    val r = ArrayBuffer(1, 2, 3, 4, 5)
    r.reverseInPlace()
    println(s"reverseInPlace: $r")   // ArrayBuffer(5, 4, 3, 2, 1)

    // distinct — returns a NEW ArrayBuffer with duplicates removed
    val dups = ArrayBuffer(1, 2, 2, 3, 3, 3, 4)
    println(s"distinct: ${dups.distinct}")   // ArrayBuffer(1, 2, 3, 4)

    // =========================================================
    // Functional methods — return a NEW ArrayBuffer
    // =========================================================

    val source = ArrayBuffer(1, 2, 3, 4, 5)

    // map returns a NEW ArrayBuffer — source is unchanged
    val mapped = source.map(_ * 10)
    println(s"\nmap *10:  $mapped")    // ArrayBuffer(10, 20, 30, 40, 50)
    println(s"original: $source")      // ArrayBuffer(1, 2, 3, 4, 5) — untouched

    // filter returns a NEW ArrayBuffer
    val filtered = source.filter(_ % 2 == 0)
    println(s"filter even: $filtered") // ArrayBuffer(2, 4)

    // =========================================================
    // Additional: building an ArrayBuffer incrementally
    // =========================================================

    // typical builder pattern: start empty, add conditionally
    val result = ArrayBuffer[String]()
    val input  = List("alice", "bob", "", "carol", "", "dave")

    for name <- input do
        if name.nonEmpty then result += name.capitalize

    println(s"\nbuilt: $result")   // ArrayBuffer(Alice, Bob, Carol, Dave)
