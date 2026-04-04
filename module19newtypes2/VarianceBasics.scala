package module19newtypes2

// Summary: Variance describes how subtyping relationships behave for parameterized types.
// Covariance is common for immutable read-only containers, while contravariance fits consumers.
// Invariance is the default and is often the safest starting point.
// Scala's variance annotations make API intent visible in the type definition.
// This file demonstrates covariance and contravariance with small examples.

class Animal:
    override def toString: String = "animal"

class Dog extends Animal:
    override def toString: String = "dog"

case class ReadBox[+A](value: A)

trait Printer[-A]:
    def print(value: A): String

val animalPrinter: Printer[Animal] =
    (value: Animal) => s"printed:$value"

@main def varianceBasics(): Unit =

    // =========================================================
    // Covariance lets a box of Dog be used where a box of Animal is expected
    // =========================================================

    val dogBox: ReadBox[Dog] = ReadBox(Dog())
    val animalBox: ReadBox[Animal] = dogBox
    println(animalBox)

    // =========================================================
    // Contravariance lets an Animal printer work for Dog values too
    // =========================================================

    val dogPrinter: Printer[Dog] = animalPrinter
    println(dogPrinter.print(Dog()))
