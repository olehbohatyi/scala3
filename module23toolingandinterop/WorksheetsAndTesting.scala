package module23toolingandinterop

// Summary: Scala learning is faster when experiments and tests are easy to run.
// Worksheets, REPL sessions, and small tests complement normal application code.
// Even when a project uses sbt, lightweight examples still matter for discovery and debugging.
// Interview preparation also benefits from tiny runnable files and targeted assertions.
// This file prints a compact workflow checklist for experimentation and testing.

case class PracticeTool(name: String, purpose: String)

@main def worksheetsAndTesting(): Unit =

    // =========================================================
    // Practical tools for feedback and learning loops
    // =========================================================

    val tools = List(
        PracticeTool("Scala CLI", "Run small files and scripts quickly"),
        PracticeTool("REPL", "Try expressions and inspect types interactively"),
        PracticeTool("Worksheet", "Explore ideas with immediate feedback"),
        PracticeTool("Tests", "Lock in behavior and refactor safely")
    )

    tools.foreach(tool => println(s"${tool.name}: ${tool.purpose}"))
