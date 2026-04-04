package module15givensandusing

// Summary: A context bound is shorthand for saying "a given of this type must exist".
// It is especially common with Ordering, Numeric, Encoder, Decoder, and custom type classes.
// Context bounds keep generic method signatures compact while preserving type-safe constraints.
// summon can still be used inside the method body to access the resolved instance.
// This file demonstrates context bounds with Ordering and custom rendering helpers.

import scala.math.Ordering

def maxOf[A: Ordering](left: A, right: A): A =
    val ord = summon[Ordering[A]]
    if ord.gteq(left, right) then left else right

def sortAndRender[A: Ordering](values: List[A]): String =
    values.sorted.mkString(", ")

@main def contextBounds(): Unit =

    // =========================================================
    // Context bounds with built-in Ordering instances
    // =========================================================

    println(maxOf(10, 20))
    println(maxOf("scala", "java"))
    println(sortAndRender(List(5, 1, 4, 2, 3)))
    println(sortAndRender(List("pear", "apple", "banana")))
