package module09mapset

import scala.collection.mutable

// Summary: mutable.Map allows in-place add/update/remove without creating new Maps.
// Accessed via scala.collection.mutable.Map — otherwise the default is immutable.
// m("key") = value adds a new entry OR updates existing — one unified operation.
// getOrElseUpdate is the "cache pattern": return existing value or insert a default.
// Use mutable Map for local, performance-sensitive accumulation (e.g. building a cache).

@main def mutableMap(): Unit =

    // =========================================================
    // Creating a mutable Map
    // =========================================================

    // must import scala.collection.mutable — or qualify it
    val mm = mutable.Map("alice" -> 85, "bob" -> 92)
    println(s"initial: $mm")

    // empty mutable Map
    val empty = mutable.Map[String, Int]()
    println(s"empty:   $empty")

    // =========================================================
    // Adding and updating — all IN PLACE
    // =========================================================

    // add a new key-value pair with () = assignment
    mm("carol") = 78
    println(s"after add carol: $mm")

    // update an existing key — same syntax
    mm("alice") = 99
    println(s"after update alice: $mm")

    // += : add one entry
    mm += ("dave" -> 88)
    println(s"after += dave: $mm")

    // ++= : add multiple entries at once
    mm ++= Map("eve" -> 75, "frank" -> 91)
    println(s"after ++=: $mm")

    // put() — Java-style, returns Option[V] (previous value or None)
    val prev = mm.put("alice", 100)
    println(s"put alice=100, prev was: $prev")   // Some(99)

    // =========================================================
    // Removing entries — IN PLACE
    // =========================================================

    mm -= "frank"
    println(s"\nafter -= frank: $mm")

    // --= : remove multiple keys
    mm --= List("eve", "dave")
    println(s"after --= eve,dave: $mm")

    // remove() — returns Option[V] (removed value or None)
    val removed = mm.remove("bob")
    println(s"removed bob: $removed")   // Some(92)
    println(s"after remove: $mm")

    // =========================================================
    // getOrElseUpdate — the "cache / memoize" pattern
    // =========================================================

    // if key exists: return its value (no update)
    // if key missing: insert default and return it
    val cache = mutable.Map[String, Int]()

    println(s"\ncache miss:  ${cache.getOrElseUpdate("answer", 42)}")  // 42, inserts it
    println(s"cache hit:   ${cache.getOrElseUpdate("answer", 0)}")    // 42, uses existing
    println(s"cache state: $cache")   // Map(answer -> 42)

    // =========================================================
    // Iterating — same as immutable Map
    // =========================================================

    val inventory = mutable.Map("apples" -> 10, "bananas" -> 5, "cherries" -> 20)

    for (item, count) <- inventory do
        println(s"  $item: $count")

    // update values in place via a loop
    for key <- inventory.keys.toList do   // .toList to avoid ConcurrentModification
        inventory(key) = inventory(key) * 2

    println(s"doubled: $inventory")

    // =========================================================
    // Converting mutable <-> immutable
    // =========================================================

    val immutable   = Map("x" -> 1, "y" -> 2)
    val toMutable   = immutable.to(mutable.Map)

    val mutableMap  = mutable.Map("a" -> 10, "b" -> 20)
    val toImmutable = mutableMap.toMap

    println(s"\nimmutable -> mutable:   $toMutable")
    println(s"mutable   -> immutable: $toImmutable")

    // =========================================================
    // Additional: word frequency counter
    // =========================================================

    def wordFrequency(text: String): mutable.Map[String, Int] =
        val freq = mutable.Map[String, Int]()
        for word <- text.split(" ") do
            freq(word) = freq.getOrElse(word, 0) + 1
        freq

    val freq = wordFrequency("the cat sat on the mat the cat")
    println(s"\nword freq: $freq")
    // Map(the -> 3, cat -> 2, sat -> 1, on -> 1, mat -> 1)
