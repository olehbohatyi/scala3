package module10functions

// Summary: Eta-expansion converts a method into a function value.
// Scala 3 can often do this automatically, but it is still important to understand what happens.
// Many higher-order APIs expect function values rather than methods.
// Knowing the distinction helps when debugging type errors around methods and functions.
// This file shows a method being passed into map and assigned to a function value.

@main def etaExpansion(): Unit =

    // =========================================================
    // A regular method
    // =========================================================

    def double(value: Int): Int = value * 2

    val asFunction: Int => Int = double
    println(asFunction(10))

    // =========================================================
    // The method is adapted when a function is expected
    // =========================================================

    val doubled = List(1, 2, 3, 4).map(double)
    println(doubled)
