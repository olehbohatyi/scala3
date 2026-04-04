package module22metaprogramming

import scala.compiletime.constValue

// Summary: constValue materializes singleton-typed constants at compile time.
// This is useful when type information should become a concrete value in code.
// It is one of the simplest entry points into Scala 3 compile-time programming.
// Singleton types and literal-based APIs often rely on this mechanism.
// This file demonstrates compile-time extraction of string and integer singleton values.

def intConstant[N <: Int & Singleton]: Int = constValue[N]

def textConstant[S <: String & Singleton]: String = constValue[S]

@main def compileTimeConstants(): Unit =

    // =========================================================
    // Type-level literals can become runtime values
    // =========================================================

    println(intConstant[42])
    println(textConstant["scala3"])
