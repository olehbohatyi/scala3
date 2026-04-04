package module21safetymodel

// Summary: Scala 3 encourages pushing nullable values to program boundaries.
// A value like String | Null makes the possibility of null explicit in the type.
// Converting nullable inputs to Option early leads to safer domain code.
// This pattern is useful when integrating with Java APIs, legacy code, or loose external inputs.
// This file demonstrates turning nullable data into a safer representation right away.

def normalizeEmail(value: String | Null): Option[String] =
    Option(value)
        .map(_.trim.toLowerCase)
        .filter(_.nonEmpty)


def displayEmail(value: String | Null): String =
    normalizeEmail(value).getOrElse("unknown@example.com")

@main def explicitNullBoundaries(): Unit =

    // =========================================================
    // Null is handled at the boundary and converted immediately
    // =========================================================

    val raw1: String | Null = " Alice@Example.COM "
    val raw2: String | Null = null

    println(normalizeEmail(raw1))
    println(normalizeEmail(raw2))
    println(displayEmail(raw2))
