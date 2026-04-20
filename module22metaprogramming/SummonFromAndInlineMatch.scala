package module22metaprogramming

import scala.compiletime.summonFrom

// Summary: summonFrom performs compile-time selection based on which givens are available.
// This is useful when inline code should adapt to type-class availability.
// It adds another practical piece to the Scala 3 metaprogramming toolbox.
// The pattern is more advanced than ordinary givens, but still approachable in small examples.
// This file demonstrates compile-time detection of Ordering support.

inline def describeOrdering[A]: String =
    summonFrom {
        case given Ordering[A] => "ordering available"
        case _ => "ordering missing"
    }

@main def summonFromAndInlineMatch(): Unit =

    // =========================================================
    // The result depends on compile-time given resolution
    // =========================================================

    println(describeOrdering[Int])
    println(describeOrdering[String])
