package module22metaprogramming

import scala.quoted.*

// Summary: quotes.reflect can inspect trees as well as symbols and types.
// Tree inspection is useful when a macro must understand the structure of an expression.
// Even a tiny example helps connect the reflection API with actual syntax trees.
// This is an optional advanced layer on top of basic quotes and Expr usage.
// The file demonstrates reporting a simple tree representation as a compile-time-generated string.

inline def treeShapeOf(inline expr: Any): String = ${ treeShapeOfImpl('expr) }

def treeShapeOfImpl(expr: Expr[Any])(using Quotes): Expr[String] =
    import quotes.reflect.*
    Expr(expr.asTerm.show)

@main def reflectTreeInspection(): Unit =

    // =========================================================
    // The tree shape is computed from the expression structure
    // =========================================================

    println(treeShapeOf(1 + 2))
    println(treeShapeOf(List(1, 2, 3).map(_ * 2)))
