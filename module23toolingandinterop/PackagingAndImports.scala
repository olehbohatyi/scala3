package module23toolingandinterop

// Summary: Packaging and imports remain fundamental for navigating larger Scala codebases.
// Clear package structure and focused imports make repositories easier to scan and maintain.
// Scala 3 also encourages more intentional imports, including given imports where needed.
// Teams benefit when modules communicate purpose through package boundaries.
// This file prints a small packaging checklist for reference.

case class PackagingRule(topic: String, note: String)

@main def packagingAndImports(): Unit =

    // =========================================================
    // Practical packaging and import guidelines
    // =========================================================

    val rules = List(
        PackagingRule("Packages", "Group code by domain or module responsibility"),
        PackagingRule("Imports", "Prefer small explicit imports near usage"),
        PackagingRule("Given imports", "Import givens only where the behavior is needed"),
        PackagingRule("Top-level defs", "Use them for compact focused examples, not for chaos")
    )

    rules.foreach(rule => println(s"${rule.topic}: ${rule.note}"))
