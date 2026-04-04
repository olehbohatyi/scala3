package module22metaprogramming

// Summary: inline lets the compiler expand method bodies at compile time.
// This is useful for tiny performance-sensitive helpers and compile-time branching.
// Inline code is also the starting point for many Scala 3 metaprogramming techniques.
// The feature becomes especially powerful when combined with quoted macros.
// This file demonstrates simple inline methods and inline if behavior.

inline def twice(inline value: Int): Int = value + value

inline def modeLabel(inline debug: Boolean): String =
    inline if debug then "debug" else "prod"

@main def inlineBasics(): Unit =

    // =========================================================
    // Small inline helpers still look like normal functions
    // =========================================================

    println(twice(21))
    println(modeLabel(true))
    println(modeLabel(false))
