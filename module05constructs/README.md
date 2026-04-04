# Module 05 — Control Flow Constructs

Covers chapters 18–19, 24–29 of *Learn Scala 3 The Fast Way*.

## Files

| File | Chapters | Topic |
|------|----------|-------|
| `IfElseAndEOP.scala` | 18–19 | if/then/else as expression, EOP — everything returns a value |
| `ForLoops.scala` | 24 | for loops with ranges, collections, nested loops |
| `ForExpressions.scala` | 25–27 | for/yield (comprehensions), multiple generators, if guards |
| `WhileLoop.scala` | 29 | while loop, do/while equivalent, when to use |
| `TryCatchFinally.scala` | 28 | try/catch/finally, exception types, catching multiple exceptions |

## Run

```bash
scala-cli run module05constructs/IfElseAndEOP.scala
scala-cli run module05constructs/ForLoops.scala
scala-cli run module05constructs/ForExpressions.scala
scala-cli run module05constructs/WhileLoop.scala
scala-cli run module05constructs/TryCatchFinally.scala

# All at once
scala-cli run module05constructs/
```

## Quick Reference

```scala
// if/else — is an EXPRESSION, always returns a value
val label = if x > 0 then "positive" else "non-positive"

// for loop — side effects only
for i <- 1 to 5 do println(i)
for i <- list do println(i)

// for expression — returns a NEW collection (yield)
val doubled = for n <- list yield n * 2

// for with multiple generators + guard
val pairs = for
    x <- 1 to 3
    y <- 1 to 3
    if x != y
yield (x, y)

// while
var i = 0
while i < 5 do
    println(i)
    i += 1

// try/catch/finally
try
    val n = "abc".toInt
catch
    case e: NumberFormatException => println(s"bad input: $e")
finally
    println("always runs")
```
