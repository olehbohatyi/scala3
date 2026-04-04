package module23toolingandinterop

// Summary: Moving from Scala 2 to Scala 3 is easier when changes are grouped by theme.
// Syntax updates, contextual abstractions, and safer typing are the biggest migration checkpoints.
// Teams should migrate incrementally and keep examples small while learning the new style.
// A compact checklist is often more useful than a long essay during real migration work.
// This file prints a focused migration checklist for course review.

case class MigrationStep(area: String, action: String)

@main def migrationNotes(): Unit =

    // =========================================================
    // A compact migration-oriented checklist
    // =========================================================

    val steps = List(
        MigrationStep("Syntax", "Adopt indentation syntax and top-level definitions gradually"),
        MigrationStep("Context", "Replace older implicit patterns with given, using, and extension methods"),
        MigrationStep("Types", "Introduce union, opaque, and match types only where they add clarity"),
        MigrationStep("Safety", "Push null handling to boundaries and prefer smart constructors"),
        MigrationStep("Build", "Use Scala CLI for examples and sbt for larger multi-module projects")
    )

    steps.foreach(step => println(s"${step.area}: ${step.action}"))
