package module19newtypes2

// Summary: Type bounds express subtype constraints directly in generic APIs.
// Upper bounds are useful when a method needs capabilities from a parent type.
// Lower bounds can appear in APIs that widen a result to accept additional values safely.
// These tools complement generics and variance when designing typed abstractions.
// This file demonstrates both upper and lower bounds with simple animal examples.

class Animal(val name: String):
    override def toString: String = s"animal:$name"

class Dog(name: String) extends Animal(name):
    override def toString: String = s"dog:$name"

class Puppy(name: String) extends Dog(name)

def describe[A <: Animal](value: A): String = s"name=${value.name}"

def prependDog[A >: Dog](values: List[A], dog: Dog): List[A] = dog :: values

@main def typeBoundsAndConstraints(): Unit =

    // =========================================================
    // Upper bound example
    // =========================================================

    val puppy = Puppy("Rex")
    println(describe(puppy))

    // =========================================================
    // Lower bound example
    // =========================================================

    val animals: List[Animal] = List(Animal("cat"))
    println(prependDog(animals, Dog("Buddy")))
