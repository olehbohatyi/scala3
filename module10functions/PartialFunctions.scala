package module10functions

// Summary: A partial function is defined only for part of its input domain.
// It is useful when only some inputs should be handled and the rest are intentionally excluded.
// collect is one of the most common ways to use partial functions with collections.
// They are also common in actor-style and event-handling code.
// This file demonstrates isDefinedAt, apply, and collect.

@main def partialFunctions(): Unit =

    // =========================================================
    // A partial function only handles selected inputs
    // =========================================================

    val describeNumber: PartialFunction[Int, String] =
        case n if n > 0 => s"positive:$n"
        case 0 => "zero"

    println(describeNumber.isDefinedAt(5))
    println(describeNumber.isDefinedAt(-1))
    println(describeNumber(0))

    // =========================================================
    // collect applies a partial function only where it is defined
    // =========================================================

    val mixed = List(-2, -1, 0, 1, 2, 3)
    println(mixed.collect(describeNumber))
