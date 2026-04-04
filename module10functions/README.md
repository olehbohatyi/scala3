# Module 10 — Functions Deep Dive

Covers chapters 53–61 of *Learn Scala 3 The Fast Way*.

## Files

| File | Chapters | Topic |
|------|----------|-------|
| `PureFunctions.scala` | 53–55 | Writing functions, pure functions, referential transparency |
| `HigherOrderFunctions.scala` | 56–58 | HOF: accepting & returning functions, function types |
| `Closures.scala` | 59 | Closures: capturing variables from outer scope |
| `PartiallyApplied.scala` | 60 | Partially applied functions, eta-expansion |
| `Currying.scala` | 61 | Currying, multiple parameter lists, practical uses |

## Run

```bash
scala-cli run module10functions/
```

## Quick Reference

```scala
// Pure function — no side effects, same input = same output
def add(a: Int, b: Int): Int = a + b

// Function type
val double: Int => Int = _ * 2
val add: (Int, Int) => Int = _ + _

// HOF — takes a function as parameter
def applyTwice(f: Int => Int, x: Int): Int = f(f(x))
applyTwice(_ * 2, 3)   // 12

// HOF — returns a function
def multiplier(n: Int): Int => Int = x => x * n
val triple = multiplier(3)
triple(5)   // 15

// Closure — captures outer variable
def makeAdder(n: Int): Int => Int = x => x + n
val add5 = makeAdder(5)
add5(10)   // 15

// Partially applied function
def add(a: Int, b: Int): Int = a + b
val add10 = add(10, _)   // fix first arg
add10(5)   // 15

// Currying
def add(a: Int)(b: Int): Int = a + b
val add10 = add(10)      // returns Int => Int
add10(5)   // 15
```
