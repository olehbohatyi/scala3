# Module 23 — Tooling and Interoperability

Covers practical Scala 3 workflows around Scala CLI, sbt, Java interop, and migration-oriented patterns.

## Files

| File | Topic |
|------|-------|
| `ScalaCliDirectives.scala` | Scala CLI directives, dependencies, and small scripts |
| `SbtAndProjectLayout.scala` | sbt layout, source directories, and test structure |
| `JavaInteropBasics.scala` | Working with Java collections and Java-style APIs |
| `MigrationNotes.scala` | Practical Scala 2 to Scala 3 migration checkpoints |
| `PackagingAndImports.scala` | Packaging structure and import guidance |
| `WorksheetsAndTesting.scala` | Worksheets, REPL, and testing workflow |
| `ImplicitsToGivensMigration.scala` | Migration map from Scala 2 implicits to Scala 3 style |

## Run

```bash
scala-cli run module23toolingandinterop/
```

## Quick Reference

```scala
//> using scala 3.3.4
//> using dep com.lihaoyi::os-lib:0.10.2

import scala.jdk.CollectionConverters.*

val names = java.util.List.of("Scala", "Java")
val scalaNames = names.asScala.toList
```
