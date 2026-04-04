package module12domainfp

// Summary: In Scala, functions are values and can be stored, passed, and returned.
// This is one of the practical foundations of FP-style design.
// It lets you build reusable behaviors without reaching for inheritance too early.
// Many validation, transformation, and decision pipelines are just functions composed together.
// This file demonstrates storing and applying functions like ordinary values.

@main def functionsAsValues(): Unit =

    // =========================================================
    // Functions stored in vals and collections
    // =========================================================

    val normalize: String => String = _.trim.toLowerCase
    val exclaim: String => String = _ + "!"

    val pipeline = List(normalize, exclaim)
    val result = pipeline.foldLeft("  Scala  ")((value, f) => f(value))

    println(result)
