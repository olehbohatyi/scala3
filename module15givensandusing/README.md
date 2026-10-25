# Module 15 — Givens and Using

Covers the core contextual abstraction model in Scala 3.

## Files

| File | Topic |
|------|-------|
| `GivenInstances.scala` | Defining and consuming `given` instances |
| `UsingClauses.scala` | Passing contextual parameters with `using` |
| `SummonAndContextParameters.scala` | Accessing contextual values with `summon` |
| `ContextBounds.scala` | Context bounds and generic constraints |
| `GivenImports.scala` | Importing givens intentionally and locally |
| `NamedGivensAndLocalOverrides.scala` | Named givens and block-scoped overrides |
| `CapabilityStyleDesign.scala` | Capability-style contextual programming |

## Run

```bash
scala-cli run module15givensandusing/
```

## Quick Reference

```scala
trait Show[A]:
    def show(value: A): String

// Scala 3.0 style used in this module; since 3.6 prefer `given Show[Int]:`
// (see module25modernscala3/NewGivenSyntax.scala)
given Show[Int] with
    def show(value: Int): String = s"int:$value"

def show[A](value: A)(using show: Show[A]): String =
    show.show(value)

def maxOf[A: Ordering](a: A, b: A): A =
    val ord = summon[Ordering[A]]
    if ord.gteq(a, b) then a else b
```
