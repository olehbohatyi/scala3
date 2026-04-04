package module21safetymodel

// Summary: Explicit nulls make nullable references visible in types instead of hiding them implicitly.
// This encourages developers to handle null at boundaries and keep the rest of the program safer.
// The approach works especially well with Option-based normalization.
// The real value is not syntax alone, but the design habit of forcing nullable APIs into the open.
// This file demonstrates explicit nullable references and a boundary conversion helper.

case class LegacyUser(name: String | Null, email: String | Null)

def safeName(user: LegacyUser): String =
    Option(user.name).map(_.trim).filter(_.nonEmpty).getOrElse("unknown")

def safeEmail(user: LegacyUser): Option[String] =
    Option(user.email).map(_.trim.toLowerCase).filter(_.contains("@"))

@main def explicitNullsMode(): Unit =

    // =========================================================
    // Nullable references are visible in the type signature
    // =========================================================

    val legacy = LegacyUser("  Alice ", null)
    println(safeName(legacy))
    println(safeEmail(legacy))
