# Module 19 — New Types, Part 2

Covers more advanced Scala 3 type-system features.

## Files

| File | Topic |
|------|-------|
| `DependentFunctionTypes.scala` | Dependent function types and path-dependent results |
| `PolymorphicFunctionTypes.scala` | First-class polymorphic function values |
| `TypeLambdas.scala` | Type lambdas for higher-kinded abstractions |
| `MatchTypes.scala` | Match types and type-level branching |
| `GenericsAndTypeParameters.scala` | Generic containers and reusable type parameters |
| `VarianceBasics.scala` | Covariance, contravariance, and API intent |
| `TypeBoundsAndConstraints.scala` | Upper and lower bounds in generic APIs |

## Run

```bash
scala-cli run module19newtypes2/
```

## Quick Reference

```scala
trait Entry:
    type Value
    def value: Value

val extractor: (e: Entry) => e.Value = (e: Entry) => e.value

val identityPoly: [A] => A => A = [A] => (a: A) => a

type ErrorOr = [A] =>> Either[String, A]
```
