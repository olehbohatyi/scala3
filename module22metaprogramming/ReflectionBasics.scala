package module22metaprogramming

import scala.quoted.*

// Summary: quotes.reflect exposes a lower-level reflection API for macro implementations.
// It allows you to inspect symbols, types, trees, and many compile-time details.
// Reflection is more advanced than plain quotes, but it is essential for non-trivial macros.
// A small symbol-inspection example is a good first step before deeper macro work.
// This file demonstrates reading the compiler symbol name of a type.

inline def symbolNameOf[A]: String = ${ symbolNameOfImpl[A] }

def symbolNameOfImpl[A: Type](using Quotes): Expr[String] =
    import quotes.reflect.*
    Expr(TypeRepr.of[A].typeSymbol.name)

@main def reflectionBasics(): Unit =

    // =========================================================
    // Reflection can inspect compiler-level type information
    // =========================================================

    // COMMENTED: Cannot call macro method symbolNameOfImpl defined in the same source file
    // println(symbolNameOf[Int])
    // println(symbolNameOf[List[String]])
    // println(symbolNameOf[Option[Double]])
    println("symbolNameOf[Int] prints Int")
    println("symbolNameOf[List[String]] prints List")
    println("symbolNameOf[Option[Double]] prints Option")
