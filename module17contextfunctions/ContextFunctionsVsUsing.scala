package module17contextfunctions

// Summary: Context functions and using clauses solve related problems, but at different levels.
// A using clause describes a method that needs contextual parameters.
// A context function is a value whose own type says that context will be required later.
// Understanding the difference helps you design APIs that are both explicit and composable.
// This file shows both approaches side by side with the same domain example.

trait Prefixer:
    def prefix: String

case class Prefix(value: String) extends Prefixer:
    def prefix: String = value

def withUsing(name: String)(using p: Prefixer): String =
    s"${p.prefix} $name"

type WithPrefix[A] = Prefixer ?=> A

def withContextFunction(name: String): WithPrefix[String] =
    s"${summon[Prefixer].prefix} $name"

@main def contextFunctionsVsUsing(): Unit =

    // =========================================================
    // using clause method
    // =========================================================

    given Prefixer = Prefix("Hello")
    println(withUsing("Scala"))

    // =========================================================
    // context function value
    // =========================================================

    println(withContextFunction("Scala"))
