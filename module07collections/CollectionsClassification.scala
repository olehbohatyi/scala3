package module07collections

// Summary: Collection methods often fall into a few broad categories.
// Thinking in categories makes large collection APIs easier to navigate during interviews.
// Common groups include transformational, filtering, informational, and aggregation methods.
// This mental model helps you choose a method faster instead of reaching for manual loops.
// The file demonstrates each group on one small data set.

@main def collectionsClassification(): Unit =

    // =========================================================
    // One data set, several method categories
    // =========================================================

    val values = List(1, 2, 3, 4, 5, 6)

    val transformed = values.map(_ * 10)
    val filtered = values.filter(_ % 2 == 0)
    val informational = values.exists(_ > 5)
    val aggregated = values.foldLeft(0)(_ + _)

    println(transformed)
    println(filtered)
    println(informational)
    println(aggregated)
