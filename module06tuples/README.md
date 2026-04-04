# Module 06 — Tuples

Covers chapter 20 of *Learn Scala 3 The Fast Way*.

## Files

| File | Chapter | Topic |
|------|---------|-------|
| `Tuples.scala` | 20 | Creating tuples, accessing elements, destructuring, in for loops, swap trick |

## Run

```bash
scala-cli run module06tuples/Tuples.scala
```

## Quick Reference

```scala
// Create
val t = (1, "hello", true)      // Tuple3[Int, String, Boolean]
val pair = ("Alice", 30)        // common: Tuple2 = a pair

// Access — index starts at 0 (unlike Scala 2 which started at _1, _2)
t(0)   // 1
t(1)   // "hello"
t(2)   // true

// Destructuring
val (name, age) = ("Alice", 30)

// In for loop
val people = List(("Alice", 30), ("Bob", 25))
for (name, age) <- people do println(s"$name is $age")

// Swap a pair
val swapped = pair.swap    // ("Alice", 30) -> (30, "Alice")
```
