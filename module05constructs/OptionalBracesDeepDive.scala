package module05constructs

// Summary: Scala 3 optional braces work together with indentation-based control structures.
// This file revisits if, match, and nested blocks with a stronger focus on readable Scala 3 layout.
// Optional braces reduce visual noise, but good indentation becomes even more important.
// end markers can still help when nesting grows deeper.
// This example strengthens the bridge between classic control structures and Scala 3 syntax style.

@main def optionalBracesDeepDive(): Unit =

    // =========================================================
    // Nested indentation with if and match
    // =========================================================

    def classify(score: Int): String =
        val grade =
            if score >= 90 then "excellent"
            else if score >= 75 then "good"
            else if score >= 60 then "pass"
            else "fail"

        grade match
            case "excellent" => "Top result"
            case "good"      => "Solid result"
            case "pass"      => "Enough to continue"
            case _            => "Needs more work"
    end classify

    println(classify(95))
    println(classify(78))
    println(classify(61))
    println(classify(40))

    // =========================================================
    // A block is still an expression returning the last value
    // =========================================================

    val summary =
        val numbers = List(1, 2, 3, 4)
        val total = numbers.sum
        s"count=${numbers.size}, total=$total"

    println(summary)
