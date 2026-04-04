package module14scala3syntax

// Summary: Scala 3 uses ? as the wildcard syntax in types instead of the older underscore style.
// This makes type wildcards easier to distinguish from other underscore-based syntax.
// Wildcards are useful when you care about the outer container but not the exact inner type.
// You will often see this in APIs that accept "some list", "some map", or "some box".
// This file shows practical wildcard type examples that remain fully type-safe.

trait Box[+A]:
    def value: A

case class IntBox(value: Int) extends Box[Int]
case class TextBox(value: String) extends Box[String]

@main def wildcardTypes(): Unit =

    // =========================================================
    // List[?] means a list of some unknown element type
    // =========================================================

    def describeList(values: List[?]): String =
        s"size=${values.size}, head=${values.headOption.getOrElse("<empty>")}"

    println(describeList(List(1, 2, 3)))
    println(describeList(List("a", "bb", "ccc")))

    // =========================================================
    // Map[String, ?] means known key type, unknown value type
    // =========================================================

    def mapKeys(values: Map[String, ?]): String =
        values.keys.toList.sorted.mkString(", ")

    println(mapKeys(Map("language" -> "Scala", "version" -> 3)))
    println(mapKeys(Map("passed" -> true, "attempts" -> 2)))

    // =========================================================
    // Wildcards also work with custom generic types
    // =========================================================

    def showBox(box: Box[?]): String = s"box=${box.value}"

    println(showBox(IntBox(42)))
    println(showBox(TextBox("Scala 3")))
