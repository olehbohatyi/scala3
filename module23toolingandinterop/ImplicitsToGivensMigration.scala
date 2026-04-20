package module23toolingandinterop

// Summary: One of the biggest Scala 2 to Scala 3 migration themes is replacing old implicit patterns.
// In Scala 3, given instances, using clauses, and extension methods usually express the same ideas more clearly.
// A focused migration checklist helps teams update code incrementally without confusion.
// The goal is not to rewrite everything at once, but to modernize the style in the most valuable places first.
// This file prints a compact migration map from older idioms to Scala 3 approaches.

case class MigrationMapping(oldStyle: String, scala3Style: String)

@main def implicitsToGivensMigration(): Unit =

    // =========================================================
    // A small migration guide for contextual patterns
    // =========================================================

    val mappings = List(
        MigrationMapping("implicit val / implicit object", "given instance"),
        MigrationMapping("implicit parameter", "using clause"),
        MigrationMapping("implicit class", "extension method"),
        MigrationMapping("implicit resolution helper", "summon or summonFrom")
    )

    mappings.foreach(item => println(s"${item.oldStyle} -> ${item.scala3Style}"))
