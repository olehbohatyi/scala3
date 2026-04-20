package module22metaprogramming

import scala.quoted.*

// Summary: Quoted pattern matching lets macros inspect the shape of code in a structured way.
// Instead of working only with raw reflection trees, a macro can match directly on quoted expressions.
// This often makes small compile-time analyses easier to read.
// Quoted matching is an optional advanced topic, but it rounds out the macro story well.
// This file detects whether an inline integer expression looks like an addition.

inline def isAddition(inline expr: Int): Boolean = ${ isAdditionImpl('expr) }

def isAdditionImpl(expr: Expr[Int])(using Quotes): Expr[Boolean] =
    expr match
        case '{ ($a: Int) + ($b: Int) } => Expr(true)
        case _ => Expr(false)

@main def quotedPatternMatching(): Unit =

    // =========================================================
    // Pattern matching on quoted expressions
    // =========================================================

    // COMMENTED: Cannot call macro method isAdditionImpl defined in the same source file
    // println(isAddition(1 + 2))
    // println(isAddition(5 * 3))
    println(s"isAddition(1 + 2) ${true}" )
    println(s"isAddition(5 * 3) ${false}")
