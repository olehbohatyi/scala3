# Module 07 — Immutable Collections

Covers chapters 21–23, 30–35, 51–52 of *Learn Scala 3 The Fast Way*.

## Files

| File | Chapters | Topic |
|------|----------|-------|
| `ListClass.scala` | 21–22 | List creation, head/tail, accessing, updating (immutable ops) |
| `OtherSequences.scala` | 23 | Vector vs List, LazyList, ArraySeq — when to use which |
| `ForeachAndLambdas.scala` | 30 | foreach, anonymous functions, short-hand `_` syntax |
| `MapAndFilter.scala` | 31–33 | map, filter, chaining, collect |
| `SequenceMethods.scala` | 34–35 | take/drop, head/tail, exists/find, flatten/flatMap, zip, fold, reduce |
| `Ranges.scala` | 51–52 | Range creation, `to`/`until`/`by`, converting to collections |

## Run

```bash
scala-cli run module07collections/ListClass.scala
scala-cli run module07collections/OtherSequences.scala
scala-cli run module07collections/ForeachAndLambdas.scala
scala-cli run module07collections/MapAndFilter.scala
scala-cli run module07collections/SequenceMethods.scala
scala-cli run module07collections/Ranges.scala

# All at once
scala-cli run module07collections/
```

## Quick Reference

```scala
// List
val xs = List(1, 2, 3)
xs.head           // 1
xs.tail           // List(2, 3)
xs(1)             // 2  (by index)
xs :+ 4           // List(1, 2, 3, 4)   append
0 +: xs           // List(0, 1, 2, 3)   prepend
xs.updated(0, 99) // List(99, 2, 3)

// Lambdas
xs.foreach(x => println(x))
xs.foreach(println)     // method reference
xs.map(_ * 2)           // List(2, 4, 6)
xs.filter(_ % 2 != 0)  // List(1, 3)

// Key methods
xs.take(2)              // List(1, 2)
xs.drop(2)              // List(3)
xs.exists(_ > 2)        // true
xs.find(_ > 1)          // Some(2)
xs.flatten              // on List[List[_]]
xs.flatMap(x => List(x, x * 10))
xs.foldLeft(0)(_ + _)   // 6  (sum)
xs.zip(List("a","b","c")) // List((1,a),(2,b),(3,c))
```
