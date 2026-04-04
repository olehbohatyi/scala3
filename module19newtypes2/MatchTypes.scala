package module19newtypes2

// Summary: Match types perform type-level branching using pattern-match-like syntax.
// They let result types vary depending on input types in a structured way.
// Match types are part of Scala 3's richer type-level programming model.
// Even simple examples help build intuition for how type computation can mirror value-level matching.
// This file demonstrates several small match-type results with explicit values.

type OutputOf[T] = T match
    case Int        => String
    case Boolean    => String
    case List[t]    => t
    case Array[t]   => t
    case _          => T

@main def matchTypes(): Unit =

    // =========================================================
    // Explicit values show the computed result types
    // =========================================================

    val fromInt: OutputOf[Int] = "number"
    val fromBoolean: OutputOf[Boolean] = "flag"
    val fromList: OutputOf[List[String]] = "scala"
    val fromArray: OutputOf[Array[Double]] = 2.5
    val unchanged: OutputOf[Long] = 99L

    println(fromInt)
    println(fromBoolean)
    println(fromList.toUpperCase)
    println(fromArray + 1.0)
    println(unchanged + 1)
