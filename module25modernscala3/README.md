# Module 25 — Modern Scala 3 (3.6 → 3.10)

Language features added after the Scala 3.3 LTS line, up to Scala 3.10.

## Files

| File | Since | Topic |
|------|-------|-------|
| `NewGivenSyntax.scala` | 3.6 | `given T:` bodies, conditional givens, `as` and `{A, B}` context bounds, deferred givens |
| `NamedTuples.scala` | 3.7 | Named tuples, named patterns, `NamedTuple.From` |
| `BetterForsAndRuntimeChecked.scala` | 3.8 | Aliases at the start of `for`, `.runtimeChecked` instead of `: @unchecked` |
| `IntoConversions.scala` | 3.9 | `into[T]` parameters and the `into` modifier for import-free conversions |
| `Scala310Highlights.scala` | 3.10 | Pattern matching under `strictEquality`, `\s` escape, `with` type is an error |

## Run

```bash
scala-cli run module25modernscala3/NamedTuples.scala
scala-cli run . --main-class module25modernscala3.newGivenSyntax
```

## Not covered here (preview or experimental in 3.10)

- `-preview`: `@unroll` default arguments (SIP-61), relaxed lambda syntax `xs.map: x => x + 1` (SIP-75), referenceable package objects (SIP-68)
- experimental: capture checking, inline and specialized traits, spec strings

## Quick Reference

```scala
given Show[Int]:                              // was: given Show[Int] with
    def show(n: Int) = n.toString

given [A: Show] => Show[List[A]]:             // conditional given
    def show(xs: List[A]) = xs.map(summon[Show[A]].show).mkString(",")

def render[A: {Ordering as ord, Show as s}](a: A, b: A) = s.show(ord.max(a, b))

val bob = (name = "Bob", age = 33)            // named tuple
val Some(v) = map.get("k").runtimeChecked     // was: (map.get("k"): @unchecked)
def walk(d: into[Meters]) = ???               // conversions allowed without import
```
