package module23toolingandinterop

// Summary: Even when Scala CLI is great for learning, sbt remains important for larger projects.
// Understanding standard layout makes navigation easier across production Scala repositories.
// The usual structure separates main sources, tests, and build configuration.
// Knowing this layout also helps during interviews and onboarding.
// This file prints a compact sbt-style project map for reference.

case class ProjectPath(path: String, purpose: String)

@main def sbtAndProjectLayout(): Unit =

    // =========================================================
    // Common sbt directories and what they are for
    // =========================================================

    val layout = List(
        ProjectPath("build.sbt", "build definition"),
        ProjectPath("project/", "build plugins and settings"),
        ProjectPath("src/main/scala/", "application source code"),
        ProjectPath("src/test/scala/", "unit and integration tests"),
        ProjectPath("src/main/resources/", "config and static resources")
    )

    layout.foreach(item => println(s"${item.path} -> ${item.purpose}"))
