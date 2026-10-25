package module25modernscala3

// Summary: `into` (stable since Scala 3.9) marks where implicit conversions are welcome.
// A parameter of type into[T] accepts values convertible to T, with no language import.
// The `into` modifier on a trait or class makes it a conversion target everywhere.
// Conversions to other types still need `import scala.language.implicitConversions`.
// This keeps conversions opt-in at the API boundary instead of scattered across call sites.

import scala.Conversion.into

case class Meters(value: Double)

object Meters:
    given Conversion[Int, Meters] = n => Meters(n.toDouble)

// `into` modifier: every conversion to Html is allowed without the language import
into trait Html:
    def render: String

case class Text(value: String) extends Html:
    def render: String = value

given Conversion[String, Html] = Text(_)

// into[T] parameter: callers may pass anything convertible to Meters
def walk(distance: into[Meters]): String =
    // inside the body the parameter is already a plain Meters
    val meters: Meters = distance
    s"walked ${meters.value} m"

def page(parts: Html*): String =
    parts.map(_.render).mkString("<p>", "</p><p>", "</p>")

@main def intoConversions(): Unit =

    // =========================================================
    // into[T] parameter — conversion at the call site
    // =========================================================

    println(walk(Meters(2.5)))   // walked 2.5 m
    println(walk(5))             // walked 5.0 m

    // =========================================================
    // into modifier — the target type opts in to conversions
    // =========================================================

    println(page("intro", Text("body")))   // <p>intro</p><p>body</p>

    // without `into`, this call would need scala.language.implicitConversions
    // to avoid a feature warning, which future versions turn into an error
