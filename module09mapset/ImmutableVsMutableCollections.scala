package module09mapset

import scala.collection.mutable

// Summary: Scala offers both immutable and mutable collection styles.
// Immutable collections are the safer default because updates return new values instead of changing shared state.
// Mutable collections can still be useful in small local algorithms or performance-sensitive internals.
// Choosing between them is an engineering trade-off, not a purity contest.
// This file compares the same task with immutable and mutable maps.

@main def immutableVsMutableCollections(): Unit =

    // =========================================================
    // Immutable update returns a new map value
    // =========================================================

    val scores = Map("scala" -> 10, "java" -> 8)
    val updatedScores = scores.updated("scala", 11)
    println(scores)
    println(updatedScores)

    // =========================================================
    // Mutable update changes the existing collection in place
    // =========================================================

    val mutableScores = mutable.Map("scala" -> 10, "java" -> 8)
    mutableScores.update("scala", 11)
    println(mutableScores)
