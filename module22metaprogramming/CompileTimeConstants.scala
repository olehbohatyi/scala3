package module22metaprogramming

import scala.compiletime.{constValue, constValueOpt}

// Summary: constValue materializes singleton-typed constants at compile time.
// This is useful when type information should become a concrete value in code.
// It is one of the simplest entry points into Scala 3 compile-time programming.
// Singleton types and literal-based APIs often rely on this mechanism.
// This file demonstrates compile-time extraction of string and integer singleton values.

@main def compileTimeConstants(): Unit =

    // =========================================================
    // Type-level literals can become runtime values
    // =========================================================

    println(constValue[42])
    println(constValue["scala3"])
