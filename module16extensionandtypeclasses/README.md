# Module 16 — Extension Methods and Type Classes

Covers some of the most practical Scala 3 features for expressive APIs.

## Files

| File | Topic |
|------|-------|
| `ExtensionMethods.scala` | Adding methods without wrappers or inheritance |
| `TypeClasses.scala` | Type-class style programming with givens |
| `MultiversalEquality.scala` | Safer equality with `CanEqual` and strict equality |
| `ImplicitConversions.scala` | Controlled implicit conversions in Scala 3 |

## Run

```bash
scala-cli run module16extensionandtypeclasses/
```

## Quick Reference

```scala
extension (value: String)
    def asSlug: String = value.trim.toLowerCase.replace(' ', '-')

trait JsonEncoder[A]:
    def encode(value: A): String

import scala.language.strictEquality
case class UserId(value: Int) derives CanEqual
```
