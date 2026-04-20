package module19newtypes2

// Summary: Variance describes how subtyping relationships behave for parameterized types.
// Covariance is common for immutable read-only containers, while contravariance fits consumers.
// Invariance is the default and is often the safest starting point.
// Scala's variance annotations make API intent visible in the type definition.
// This file demonstrates covariance and contravariance with small examples.

class Animal1:
    override def toString: String = "animal"

class Cat extends Animal1:
    override def toString: String = "cat"

case class ReadBox[+A](value: A)

trait Printer[-A]:
    def print(value: A): String

val animalPrinter: Printer[Animal1] =
    (value: Animal1) => s"printed:$value"

@main def varianceBasics(): Unit =

    // =========================================================
    // Covariance lets a box of cat be used where a box of Animal is expected
    // =========================================================

    val catBox: ReadBox[Cat] = ReadBox(Cat())
    val animalBox: ReadBox[Animal1] = catBox
    println(animalBox)

    // =========================================================
    // Contravariance lets an Animal printer work for cat values too
    // =========================================================

    val catPrinter: Printer[Cat] = animalPrinter
    println(catPrinter.print(Cat()))
