package module25modernscala3

// Summary: Scala 3.10 makes several small but interview-relevant language changes.
// Under strictEquality, matching on enum cases and case objects no longer needs CanEqual.
// The `\s` escape inserts a space, handy for trailing spaces that editors strip.
// `A with B` as a type is now an error (use `A & B`), and `this` is forbidden at top level.
// Java records can be deconstructed in pattern matches like case classes.

import scala.language.strictEquality

// no `derives CanEqual` here on purpose
enum Signal:
    case Red, Amber, Green

sealed trait Command
case object Start extends Command
case object Stop extends Command

trait Named:
    def name: String

trait Aged:
    def age: Int

// before 3.10 these matches failed under strictEquality without CanEqual instances
def next(signal: Signal): Signal =
    signal match
        case Signal.Red   => Signal.Green
        case Signal.Green => Signal.Amber
        case Signal.Amber => Signal.Red

def run(command: Command): String =
    command match
        case Start => "starting"
        case Stop  => "stopping"

// intersection types use `&`; `Named with Aged` as a type no longer compiles
def label(value: Named & Aged): String = s"${value.name} (${value.age})"

@main def scala310Highlights(): Unit =

    // =========================================================
    // Pattern matching under strictEquality (SIP-67)
    // =========================================================

    println(next(Signal.Red))   // Green
    println(run(Stop))          // stopping

    // == between values still requires CanEqual, so this stays rejected:
    // println(Signal.Red == Signal.Green)

    // =========================================================
    // The \s escape
    // =========================================================

    val padded = "name:\s\s|"
    println(padded)          // name:  |
    println(padded.length)   // 8

    // =========================================================
    // Intersection types instead of `with`
    // =========================================================

    val person = new Named with Aged:
        def name = "Ada"
        def age = 36
    println(label(person))   // Ada (36)

    // =========================================================
    // Other 3.10 notes
    // =========================================================

    // Java records match like case classes:  case Point(x, y) => ...
    // `->` is now an inline method, so `a -> b` builds the pair with no wrapper call
    // Rich* wrappers on primitives became direct extension methods (same call syntax)
    // -language:experimental.strictEqualityPatternMatching is now deprecated and a no-op
