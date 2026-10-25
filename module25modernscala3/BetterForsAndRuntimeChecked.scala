package module25modernscala3

// Summary: Scala 3.8 made "better fors" standard: a for-comprehension may start with aliases.
// The desugaring also drops redundant trailing map calls, so for-yield code is cheaper.
// runtimeChecked marks a pattern you know always matches; it replaces `: @unchecked`.
// Unlike @unchecked, runtimeChecked keeps other warnings and still fails loudly at runtime.
// This file demonstrates both features on small Option and List examples.

enum Weekday:
    case Mon, Tue, Wed, Thu, Fri, Sat, Sun

@main def betterForsAndRuntimeChecked(): Unit =

    // =========================================================
    // Better fors — aliases before the first generator
    // =========================================================

    val sums =
        for
            as = List(1, 2, 3)
            bs = List(10, 20)
            a <- as
            b <- bs
        yield a + b
    println(sums)   // List(11, 21, 12, 22, 13, 23)

    // `for a <- xs yield a` now desugars to just `xs`, without `.map(a => a)`
    val same = for a <- List(1, 2, 3) yield a
    println(same)   // List(1, 2, 3)

    // =========================================================
    // runtimeChecked — refutable patterns you know are safe
    // =========================================================

    val config = Map("appVersion" -> "3.10.0")

    // without .runtimeChecked this pattern definition is flagged as refutable
    val Some(appVersion) = config.get("appVersion").runtimeChecked
    println(s"appVersion=$appVersion")   // appVersion=3.10.0

    // without .runtimeChecked this match triggers an exhaustivity warning
    def describe(day: Weekday): String =
        day.runtimeChecked match
            case Weekday.Mon | Weekday.Tue | Weekday.Wed | Weekday.Thu | Weekday.Fri => "workday"

    println(describe(Weekday.Wed))   // workday

    // the check still happens at runtime: an unexpected value throws MatchError
    val weekend = scala.util.Try(describe(Weekday.Sun))
    println(weekend.isFailure)   // true
