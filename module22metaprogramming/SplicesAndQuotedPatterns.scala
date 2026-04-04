package module22metaprogramming

import scala.quoted.*

// Summary: Splices insert generated expressions into quoted code in Scala 3 macros.
// Quotes build code, while splices place computed pieces inside that code.
// Understanding this pair gives a more complete picture of macro structure than quotes alone.
// Small examples are enough to build intuition before deeper macro work.
// This file demonstrates generating a simple expression through quotes and splices.

inline def addOneInline(inline value: Int): Int = ${ addOneInlineImpl('value) }

def addOneInlineImpl(value: Expr[Int])(using Quotes): Expr[Int] =
    '{ $value + 1 }

@main def splicesAndQuotedPatterns(): Unit =

    // =========================================================
    // A splice inserts the incoming expression into generated code
    // =========================================================

    println(addOneInline(41))
