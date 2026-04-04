package module09mapset

import scala.collection.mutable

// Summary: Set is an UNORDERED collection of UNIQUE values — no duplicates allowed.
// Membership test (.contains) is O(1) on HashSet — much faster than List.contains O(n).
// Set supports algebraic operations: union (|), intersection (&), difference (&~).
// The default Set is immutable; mutable.Set is available for in-place operations.
// Sorted iteration: use SortedSet which maintains natural order.

@main def setCollection(): Unit =

    // =========================================================
    // Creating an immutable Set
    // =========================================================

    val s1 = Set(1, 2, 3, 4, 5)
    println(s"s1 = $s1")

    // duplicates are silently dropped — Set keeps only unique values
    val withDups = Set(1, 2, 2, 3, 3, 3, 4)
    println(s"with dups: $withDups")   // Set(1, 2, 3, 4)

    // empty Set
    val empty: Set[Int] = Set.empty
    println(s"empty: $empty")

    // Set of Strings
    val fruits = Set("apple", "banana", "cherry", "apple")   // one "apple"
    println(s"fruits: $fruits")

    // =========================================================
    // Membership — the primary use case for Set
    // =========================================================

    println(s"\ncontains 3:  ${s1.contains(3)}")   // true
    println(s"contains 9:  ${s1.contains(9)}")   // false

    // in operator (same as contains)
    println(s"3 in s1:  ${s1(3)}")    // true  (apply = contains for Set)
    println(s"9 in s1:  ${s1(9)}")    // false

    // =========================================================
    // Adding and removing — returns a NEW Set
    // =========================================================

    val s2 = s1 + 6
    println(s"\ns1 + 6 = $s2")          // Set(1, 2, 3, 4, 5, 6)
    println(s"s1 unchanged: $s1")        // Set(1, 2, 3, 4, 5)

    val s3 = s1 - 3
    println(s"s1 - 3 = $s3")            // Set(1, 2, 4, 5)

    // add multiple
    val s4 = s1 ++ Set(6, 7, 8)
    println(s"s1 ++ {6,7,8} = $s4")

    // remove multiple
    val s5 = s1 -- Set(2, 4)
    println(s"s1 -- {2,4} = $s5")       // Set(1, 3, 5)

    // =========================================================
    // Set algebra — union, intersection, difference
    // =========================================================

    val a = Set(1, 2, 3, 4, 5)
    val b = Set(4, 5, 6, 7, 8)

    // union: all elements from both
    println(s"\na | b   = ${a | b}")          // Set(1,2,3,4,5,6,7,8)
    println(s"a union b = ${a.union(b)}")     // same

    // intersection: elements in BOTH
    println(s"a & b   = ${a & b}")            // Set(4, 5)
    println(s"a intersect b = ${a.intersect(b)}")

    // difference: elements in a but NOT in b
    println(s"a &~ b  = ${a &~ b}")           // Set(1, 2, 3)
    println(s"a diff b = ${a.diff(b)}")

    // symmetric difference: in one but not both
    val symDiff = (a | b) &~ (a & b)
    println(s"symDiff = $symDiff")            // Set(1, 2, 3, 6, 7, 8)

    // =========================================================
    // Subset / superset checks
    // =========================================================

    val small = Set(2, 3)
    println(s"\nsmall subsetOf a:    ${small.subsetOf(a)}")     // true
    println(s"a supersetOf small:  ${a.subsetOf(small)}")      // false — reversed!
    println(s"a.forall(small) via: ${small.forall(a.contains)}") // true = small ⊆ a

    // =========================================================
    // Iterating over a Set
    // =========================================================

    // order is NOT guaranteed for HashSet
    for elem <- fruits do
        print(s"$elem ")
    println()

    // if you need sorted order, use SortedSet
    import scala.collection.immutable.SortedSet
    val sorted = SortedSet(5, 1, 3, 2, 4)
    println(s"SortedSet: $sorted")   // TreeSet(1, 2, 3, 4, 5)

    // map and filter on Set
    val doubled = s1.map(_ * 2)
    println(s"doubled: $doubled")        // Set(2, 4, 6, 8, 10)

    val bigSet = s1.filter(_ > 3)
    println(s"filter>3: $bigSet")        // Set(4, 5)

    // =========================================================
    // Mutable Set
    // =========================================================

    val ms = mutable.Set(1, 2, 3)
    println(s"\nmutable initial: $ms")

    ms += 4
    ms += 5
    println(s"after +=4,5: $ms")

    ms -= 2
    println(s"after -=2:   $ms")

    ms ++= Set(10, 11, 12)
    println(s"after ++=:   $ms")

    ms --= Set(10, 11)
    println(s"after --=:   $ms")

    // =========================================================
    // Additional: deduplicate a List using Set
    // =========================================================

    val dupes = List(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)
    val unique = dupes.toSet.toList.sorted
    println(s"\ndupes:  $dupes")
    println(s"unique: $unique")   // List(1, 2, 3, 4)

    // =========================================================
    // Additional: Set vs List for membership test
    // =========================================================

    // List.contains is O(n) — scans every element
    // Set.contains  is O(1) — hash lookup

    val allowedList = List("admin", "moderator", "editor")
    val allowedSet  = Set("admin", "moderator", "editor")

    val user = "moderator"
    println(s"\nList check: ${allowedList.contains(user)}")  // true, but O(n)
    println(s"Set check:  ${allowedSet.contains(user)}")    // true, O(1)
