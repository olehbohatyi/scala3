package module09mapset

// Summary: Map is a collection of key-value pairs with UNIQUE keys.
// The default Map (no import) is IMMUTABLE — "updates" return a new Map.
// Key lookup with m("key") throws NoSuchElementException if missing — prefer .get.
// .get returns Option[V]: Some(value) if found, None if missing — the safe API.
// Maps are unordered by default (HashMap internally); use ListMap / TreeMap for ordering.

@main def mapCollection(): Unit =

    // =========================================================
    // Creating an immutable Map
    // =========================================================

    // using -> sugar (Tuple2 under the hood)
    val capitals = Map(
        "Germany" -> "Berlin",
        "France"  -> "Paris",
        "Spain"   -> "Madrid",
        "Italy"   -> "Rome"
    )
    println(s"capitals: $capitals")

    // explicit Tuple2 syntax (identical to ->)
    val scores = Map(("Alice", 95), ("Bob", 87), ("Carol", 91))
    println(s"scores:   $scores")

    // empty map
    val empty: Map[String, Int] = Map.empty
    println(s"empty:    $empty")

    // =========================================================
    // Accessing values
    // =========================================================

    // by key — throws NoSuchElementException if key doesn't exist
    println(s"\ncapitals(\"Germany\") = ${capitals("Germany")}")  // Berlin

    // .get — returns Option[V], safe
    println(s".get(\"France\")  = ${capitals.get("France")}")    // Some(Paris)
    println(s".get(\"Japan\")   = ${capitals.get("Japan")}")     // None

    // .getOrElse — provides a default if key is missing
    println(s"getOrElse(\"Japan\",\"Unknown\") = ${capitals.getOrElse("Japan", "Unknown")}")

    // .contains — check if key exists
    println(s"contains(\"Spain\") = ${capitals.contains("Spain")}")   // true
    println(s"contains(\"USA\")   = ${capitals.contains("USA")}")     // false

    // =========================================================
    // Iterating over a Map
    // =========================================================

    // destructure key/value in for generator
    println("\nAll capitals:")
    for (country, capital) <- capitals do
        println(s"  $country -> $capital")

    // iterate over keys only
    println(s"keys:   ${capitals.keys.toList.sorted}")
    println(s"values: ${capitals.values.toList.sorted}")

    // map over entries
    val upper = capitals.map((country, cap) => country -> cap.toUpperCase)
    println(s"upper: $upper")

    // =========================================================
    // Adding entries — returns a NEW Map (immutable!)
    // =========================================================

    // add one entry
    val c2 = capitals + ("Japan" -> "Tokyo")
    println(s"\nwith Japan: $c2")
    println(s"original:   $capitals")   // unchanged

    // add multiple entries
    val c3 = capitals ++ Map("Japan" -> "Tokyo", "Brazil" -> "Brasilia")
    println(s"with two:   $c3")

    // =========================================================
    // Removing entries — returns a NEW Map
    // =========================================================

    val c4 = capitals - "Spain"
    println(s"\nwithout Spain: $c4")

    // remove multiple keys
    val c5 = capitals -- List("Spain", "Italy")
    println(s"without two:   $c5")

    // =========================================================
    // Updating a value — returns a NEW Map
    // =========================================================

    // + with existing key replaces the value
    val updated = capitals + ("Germany" -> "Hamburg")  // replaces Berlin
    println(s"\nupdated Germany: $updated")

    // .updated method is more explicit
    val updated2 = capitals.updated("France", "Lyon")
    println(s"updated France:  $updated2")

    // =========================================================
    // Common Map methods
    // =========================================================

    println(s"\nsize     = ${capitals.size}")         // 4
    println(s"isEmpty  = ${capitals.isEmpty}")        // false
    println(s"keySet   = ${capitals.keySet}")

    // filter entries
    val euroZone = Map("Germany" -> 83, "France" -> 67, "Spain" -> 47, "UK" -> 67)
    val large = euroZone.filter((_, pop) => pop > 60)
    println(s"large (pop>60): $large")

    // transform values with mapValues (lazy — call .toMap to force)
    val doubled = euroZone.map((k, v) => k -> v * 2)
    println(s"doubled pop:    $doubled")

    // find an entry
    val found = euroZone.find((_, pop) => pop == 47)
    println(s"pop==47:  $found")   // Some((Spain,47))

    // =========================================================
    // Merging Maps — later values win on key collision
    // =========================================================

    val m1 = Map("a" -> 1, "b" -> 2)
    val m2 = Map("b" -> 99, "c" -> 3)   // "b" conflicts

    // ++ : values from m2 win on collision
    val merged = m1 ++ m2
    println(s"\nmerged (m2 wins): $merged")   // Map(a->1, b->99, c->3)

    // custom merge: keep higher value on collision
    val custom = (m1.toSeq ++ m2.toSeq)
        .groupBy(_._1)
        .map((k, vs) => k -> vs.map(_._2).max)
    println(s"max merge:        $custom")

    // =========================================================
    // Additional: building a frequency map
    // =========================================================

    val text  = "hello scala"
    val freqs = text.filter(_ != ' ').groupBy(identity).map((ch, cs) => ch -> cs.length)
    println(s"\nchar frequencies: $freqs")
