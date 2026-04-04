# Module 21 — Safety Model

Covers Scala 3-oriented safety ideas for null handling, invariants, exhaustive modeling, and initialization.

## Files

| File | Topic |
|------|-------|
| `ExplicitNullBoundaries.scala` | Safer boundaries with `String | Null` and `Option` |
| `SmartConstructors.scala` | Encoding invariants with validated constructors |
| `ExhaustiveMatches.scala` | Safer state handling through closed models |
| `InitializationPatterns.scala` | Prefer constructor parameters and lazy values over unsafe setup |
| `ExplicitNullsMode.scala` | Visibility of nullable references and boundary normalization |
| `SafeInitializationNotes.scala` | Additional safe initialization practices |

## Run

```bash
scala-cli run module21safetymodel/
```

## Quick Reference

```scala
def normalize(value: String | Null): Option[String] =
    Option(value).map(_.trim).filter(_.nonEmpty)

case class Email private (value: String)

sealed trait JobState
```
