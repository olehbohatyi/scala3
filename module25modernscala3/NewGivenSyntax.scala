package module25modernscala3

// Summary: Scala 3.6 introduced a cleaner given syntax that replaces `given X with`.
// A given with a body ends in `:`; conditional givens use `[A: Show] => Show[List[A]]`.
// Context bounds can be named with `as` and grouped with `{Ord, Show}`.
// Deferred givens let a trait demand a given that concrete classes must provide.
// The old `given X with` form still compiles, but new code should prefer this style.

import scala.compiletime.deferred

trait Show[A]:
    def show(value: A): String

object Show:
    // anonymous given with a body: `given Type:` instead of `given Type with`
    given Show[Int]:
        def show(value: Int): String = s"int:$value"

    // named given with a body
    given stringShow: Show[String]:
        def show(value: String): String = s"'$value'"

    // conditional given: a Show[List[A]] exists whenever a Show[A] exists
    given [A: Show] => Show[List[A]]:
        def show(values: List[A]): String =
            values.map(summon[Show[A]].show).mkString("[", ", ", "]")

// named context bound: `as show` names the witness, so no summon is needed
def render[A: Show as show](value: A): String =
    show.show(value)

// aggregate context bounds: several type classes on one type parameter
def showMax[A: {Ordering as ord, Show as show}](left: A, right: A): String =
    show.show(ord.max(left, right))

// =========================================================
// Deferred givens — a trait asks its implementations for a given
// =========================================================

trait SortedBag:
    // a context bound on a type member expands to `given Ordering[Element] = deferred`
    type Element: Ordering
    def items: List[Element]
    def sortedItems: List[Element] = items.sorted

class IntBag(val items: List[Int]) extends SortedBag:
    // the compiler fills in the deferred Ordering[Int] from the givens in scope
    type Element = Int

trait Greeter:
    given Show[String] = deferred
    def greet(name: String): String = s"hello ${summon[Show[String]].show(name)}"

object LoudGreeter extends Greeter:
    // provide the deferred given explicitly
    override given Show[String]:
        def show(value: String): String = value.toUpperCase

@main def newGivenSyntax(): Unit =

    // =========================================================
    // New given forms
    // =========================================================

    println(render(42))                // int:42
    println(render("scala"))           // 'scala'
    println(render(List(1, 2, 3)))     // [int:1, int:2, int:3]

    // =========================================================
    // Named and aggregate context bounds
    // =========================================================

    println(showMax(3, 7))             // int:7
    println(showMax("pear", "apple"))  // 'pear'

    // =========================================================
    // Deferred givens
    // =========================================================

    println(IntBag(List(5, 2, 8)).sortedItems)   // List(2, 5, 8)
    println(LoudGreeter.greet("ada"))            // hello ADA
