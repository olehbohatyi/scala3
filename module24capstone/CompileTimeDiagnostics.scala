package module24capstone

import scala.compiletime.constValue
import scala.quoted.*

// Summary: This capstone combines inline and macro-style ideas in one focused example.
// It shows how Scala 3 can surface compile-time information through small helper APIs.
// The goal is not deep macro complexity, but practical intuition for compile-time utilities.
// Singleton types, inline, and quoted expressions work together naturally here.
// This file acts as a bridge between language learning and advanced Scala 3 experimentation.

inline def labelOf[S <: String & Singleton]: String = constValue[S]

inline def debugTypeOf[A]: String = ${ debugTypeOfImpl[A] }

def debugTypeOfImpl[A: Type](using Quotes): Expr[String] =
    Expr(s"type=${Type.show[A]}")

@main def compileTimeDiagnostics(): Unit =

    // =========================================================
    // Compile-time labels and type descriptions
    // =========================================================

    println(labelOf["job-service"])
    println(debugTypeOf[Int])
    println(debugTypeOf[Either[String, List[Int]]])
