package module10functions

// Summary: Generic methods work with many input types while preserving type safety.
// They are a practical bridge between basic methods and more advanced type-system features.
// Type parameters let one method express reusable structure without giving up precise result types.
// Many standard library methods are generic, so understanding the pattern is essential.
// This file demonstrates small generic helpers that remain easy to read.

@main def genericMethods(): Unit =

    // =========================================================
    // A generic method can preserve the input type
    // =========================================================

    def firstOf[A](values: List[A]): Option[A] = values.headOption
    println(firstOf(List(1, 2, 3)))
    println(firstOf(List("scala", "java")))

    // =========================================================
    // Generic methods can work with pairs and containers
    // =========================================================

    def swap[A, B](pair: (A, B)): (B, A) = (pair._2, pair._1)
    println(swap(("level", 5)))

    def repeat[A](value: A, times: Int): List[A] = List.fill(times)(value)
    println(repeat(true, 3))
