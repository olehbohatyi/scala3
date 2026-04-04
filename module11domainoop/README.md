# Module 11 — Domain Modeling with OOP

Covers the practical OOP foundations needed before algebraic domain modeling.

## Files

| File | Topic |
|------|-------|
| `ClassesAndConstructors.scala` | Classes, constructor parameters, methods, validation, custom toString |
| `ObjectsAndCompanions.scala` | Singleton objects, companion objects, factory methods, apply/unapply |
| `TraitsAndMixins.scala` | Traits, mixin composition, stackable behavior, dependency-style contracts |
| `InheritanceAndOverrides.scala` | Inheritance, abstract classes, override, polymorphism, final/sealed |
| `EnumsBasics.scala` | Scala 3 enums, parameterized cases, methods on enums, parsing and rendering |

## Run

```bash
scala-cli run module11domainoop/
```

## Quick Reference

```scala
class User(val name: String, var age: Int):
    def isAdult: Boolean = age >= 18

object User:
    def apply(name: String, age: Int): User = new User(name, age)

trait Logger:
    def log(message: String): Unit

class ConsoleLogger extends Logger:
    def log(message: String): Unit = println(message)

abstract class Shape:
    def area: Double

class Circle(val radius: Double) extends Shape:
    override def area: Double = math.Pi * radius * radius

enum Role:
    case Admin, Editor, Viewer
```
