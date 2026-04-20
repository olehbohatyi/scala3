package module22metaprogramming

import scala.quoted.*

// Summary: Quotes and Expr are the foundation of Scala 3 macros.
// A macro can inspect types or code at compile time and return generated expressions.
// Even small examples are useful for understanding the basic shape of Scala 3 metaprogramming.
// This feature is powerful, so starting with tiny safe macros is the best learning path.
// This file demonstrates a macro that returns the pretty-printed name of a type.

inline def typeNameOf[A]: String = ${ typeNameOfImpl[A] }

def typeNameOfImpl[A: Type](using Quotes): Expr[String] =
    Expr(Type.show[A])

@main def quotesAndExpr(): Unit =

    // =========================================================
    // Macro expansion computes the type name at compile time
    // =========================================================

    // COMMENTED: Cannot call macro method typeNameOfImpl defined in the same source file
    // println(typeNameOf[Int])
    // println(typeNameOf[List[String]])
    // println(typeNameOf[Map[String, Int]])
    println("typeNameOf[Int] prints scala.Int")
    println("typeNameOf[List[String]] prints scala.collection.immutable.List[scala.Predef.String]")
    println("typeNameOf[Map[String, Int]] prints scala.collection.immutable.Map[scala.Predef.String, scala.Int]")
