package module17contextfunctions

// Summary: Context functions are first-class values and can be stored in vals, returned, and composed.
// This makes them a useful abstraction for configurable behaviors that depend on contextual data.
// They are especially expressive when combined with case classes that represent small environments.
// Because the environment type is explicit, the required capability stays visible in the signature.
// This file demonstrates reusable greeting functions built as context-function values.

case class Person(name: String, role: String)

type Greeter = Person ?=> String

val formalGreeter: Greeter =
    summon[Person] match
        case Person(name, role) => s"Good day, $name ($role)."

val casualGreeter: Greeter =
    s"Hey, ${summon[Person].name}!"

def greetWith(greeter: Greeter)(using Person): String = greeter

@main def contextFunctionValues(): Unit =

    // =========================================================
    // Context functions stored as vals
    // =========================================================

    val mentor = Person("Elena", "mentor")
    println(greetWith(formalGreeter)(using mentor))
    println(greetWith(casualGreeter)(using mentor))

    // =========================================================
    // Another context changes the same functions' output
    // =========================================================

    val candidate = Person("Maks", "candidate")
    println(greetWith(formalGreeter)(using candidate))
