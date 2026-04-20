# Module 18 — New Types, Part 1

Covers core Scala 3 type-system additions used in real code.

## Files

| File | Topic |
|------|-------|
| `UnionAndIntersectionTypes.scala` | Union types and intersection types |
| `OpaqueTypes.scala` | Opaque types for zero-overhead domain modeling |
| `StructuralTypes.scala` | Structural types and reflective selection |
| `TransparentTraitsAndClasses.scala` | Transparent traits and transparent inline methods |
| `OpaqueTypesWithValidation.scala` | Opaque types combined with validation |

## Run

```bash
scala-cli run module18newtypes1/
```

## Quick Reference

```scala
def render(value: Int | String): String = value match
    case i: Int => s"int:$i"
    case s: String => s"text:$s"

trait Camera:
    def shoot(): String

trait Phone:
    def call(number: String): String

def useBoth[A <: Camera & Phone](device: A): String =
    device.shoot() + " | " + device.call("123")
```
