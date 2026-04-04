package module18newtypes1

// Summary: Scala 3 adds transparent features that help preserve precise result types.
// A transparent inline method lets the compiler keep a more specific return type after inlining.
// This can improve type precision in helper methods that branch on inline conditions.
// The feature is more advanced than day-to-day syntax, but it is an important Scala 3 capability.
// This file demonstrates a small transparent inline example that returns precise types.

transparent inline def choose(inline flag: Boolean): Int | String =
    inline if flag then 42 else "scala"

@main def transparentTraitsAndClasses(): Unit =

    // =========================================================
    // transparent inline can keep a more precise result type
    // =========================================================

    val number: Int = choose(true)
    val text: String = choose(false)

    println(number + 1)
    println(text.toUpperCase)
