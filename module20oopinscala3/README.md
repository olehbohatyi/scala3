# Module 20 — OOP in Scala 3

Covers Scala 3 additions that improve object-oriented design and API structure.

## Files

| File | Topic |
|------|-------|
| `TraitParameters.scala` | Trait parameters for reusable configured behavior |
| `OpenClasses.scala` | `open` classes and explicit inheritance design |
| `ExportClauses.scala` | `export` clauses for clean delegation APIs |
| `EnumsWithBehavior.scala` | Rich enums with fields and methods |

## Run

```bash
scala-cli run module20oopinscala3/
```

## Quick Reference

```scala
trait PrefixLogger(prefix: String):
    def log(message: String): String = s"[$prefix] $message"

open class Notification(val channel: String):
    def deliver(message: String): String = s"[$channel] $message"

class UserService(repo: UserRepository):
    export repo.find
```
