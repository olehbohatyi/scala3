package module24capstone

// Summary: This final app ties together several Scala 3 features in one concise walkthrough.
// It uses extension methods, givens, using clauses, enums, and match-based rendering.
// The goal is to give the learner one fast file to revisit before interviews.
// Each example is intentionally small, but the file demonstrates how the features coexist naturally.
// This program serves as a final Scala 3 refresher inside the course.

extension (value: String)
    def asSlug: String = value.trim.toLowerCase.replace(' ', '-')

trait Show[A]:
    def show(value: A): String

object Show:
    given Show[Int] with
        def show(value: Int): String = s"int:$value"

    given Show[String] with
        def show(value: String): String = s"text:${value.asSlug}"

enum BuildMode:
    case Dev, Prod

def render[A](value: A)(using show: Show[A]): String = show.show(value)

def describeMode(mode: BuildMode): String = mode match
    case BuildMode.Dev  => "development"
    case BuildMode.Prod => "production"

@main def scala3FeatureTourApp(): Unit =

    // =========================================================
    // Compact Scala 3 refresher
    // =========================================================

    import Show.given

    println(render(42))
    println(render("Scala 3 Feature Tour"))
    println(describeMode(BuildMode.Dev))
    println(describeMode(BuildMode.Prod))
