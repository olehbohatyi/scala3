# Module 02 — Basics: REPL, Printing, Variables & Types

Covers chapters 8–12 of *Learn Scala 3 The Fast Way*.

## Files

| File | Chapter | Topic |
|------|---------|-------|
| `ScalaRepl.scala` | 8 | REPL concepts: expressions, tab completion, res variables |
| `PrintingOutput.scala` | 9 | println, print, STDOUT vs STDERR, string concatenation |
| `ValFields.scala` | 10 | val — immutable variables, algebraic variable model |
| `VarFields.scala` | 11 | var — mutable variables, when and why to use them |
| `ExplicitTypes.scala` | 12 | Explicit type annotations, type inference, common types |

## Run

```bash
scala-cli run module02basics/ScalaRepl.scala
scala-cli run module02basics/PrintingOutput.scala
scala-cli run module02basics/ValFields.scala
scala-cli run module02basics/VarFields.scala
scala-cli run module02basics/ExplicitTypes.scala

# All at once
scala-cli run module02basics/
```

## REPL Quick Start

```bash
# Start interactive REPL
scala-cli repl

# Inside the REPL
scala> val x = 42          // x: Int = 42
scala> x * 2               // res0: Int = 84
scala> res0 + 1            // res1: Int = 85
scala> "hello".Tab         // shows all String methods
scala> :reset              // clears all definitions
scala> :quit               // exits the REPL
```

## Key Rules to Remember

- **Prefer `val`** — declare everything as `val` first; switch to `var` only if mutation is required
- **Type inference** — the compiler infers types automatically; annotate explicitly when it aids clarity
- **`val` ≈ algebra** — once assigned, the value never changes (like math variables)
- **`var` ≈ imperative** — use sparingly; mutable state makes code harder to reason about
