package module19newtypes2

// Summary: Type parameters let classes and methods describe reusable typed abstractions.
// They are one of the core tools behind collections, options, eithers, and custom containers.
// Understanding generics is essential before advanced Scala 3 type features feel natural.
// This file keeps the examples small and concrete to reinforce the mental model.
// The goal is to show that type parameters add flexibility without losing safety.

case class Box[A](value: A):
    def map[B](f: A => B): Box[B] = Box(f(value))

@main def genericsAndTypeParameters(): Unit =

    // =========================================================
    // Generic containers preserve the element type
    // =========================================================

    val intBox = Box(42)
    val textBox = Box("Scala 3")

    println(intBox)
    println(textBox)

    // =========================================================
    // Generic transformations can change the contained type
    // =========================================================

    println(intBox.map(_ * 2))
    println(textBox.map(_.length))
