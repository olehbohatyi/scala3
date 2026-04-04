# Module 14 — Scala 3 Syntax

Covers core Scala 3 syntax changes and source-organization improvements.

## Files

| File | Topic |
|------|-------|
| `OptionalBraces.scala` | Significant indentation, optional braces, end markers, expression blocks |
| `TopLevelDefinitions.scala` | Top-level vals, defs, enums, and case classes |
| `MainMethods.scala` | Scala 3 `@main` entry points and command-line friendly methods |
| `WildcardTypes.scala` | `?` wildcard syntax in types and APIs |
| `CreatorApplications.scala` | Function-like construction without `new` through creators |

## Run

```bash
scala-cli run module14scala3syntax/
```

## Quick Reference

```scala
val result =
    val a = 10
    val b = 20
    a + b

def greet(name: String): String =
    s"Hello, $name"

enum Mode:
    case Dev, Prod

@main def hello(): Unit =
    println("Scala 3")

def sizeOf(values: List[?]): Int = values.size
```
