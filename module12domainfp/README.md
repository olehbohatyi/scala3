# Module 12 — Functional Domain Modeling

Builds the FP-oriented modeling toolkit used in idiomatic Scala codebases.

## Files

| File | Topic |
|------|-------|
| `CaseClasses.scala` | Case classes, copy, equals, deconstruction, domain records |
| `PatternMatching.scala` | match expressions, guards, tuple/list matching, exhaustiveness |
| `SealedTraitsAndAdts.scala` | Sealed traits, ADTs, modeling workflows and result states |
| `OptionBasics.scala` | Option, Some, None, map/flatMap/getOrElse, avoiding null |
| `EitherBasics.scala` | Either for domain errors, right-bias, map/flatMap/fold |
| `TryBasics.scala` | Try, Success, Failure, exception capture, recovery |

## Run

```bash
scala-cli run module12domainfp/
```

## Quick Reference

```scala
case class User(id: Long, name: String)

sealed trait PaymentStatus
object PaymentStatus:
    case object Pending extends PaymentStatus
    case object Paid extends PaymentStatus
    case object Failed extends PaymentStatus

val maybeName: Option[String] = Some("Scala")
val name = maybeName.getOrElse("Anonymous")

val parsed: Either[String, Int] = Right(42)
val safe = parsed.map(_ * 2)

import scala.util.Try
val n = Try("123".toInt)
```
