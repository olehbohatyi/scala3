# Module 08 — Mutable Collections: ArrayBuffer & Array

Covers chapters 37–43 of *Learn Scala 3 The Fast Way*.

## Files

| File | Chapters | Topic |
|------|----------|-------|
| `ArrayBufferBasics.scala` | 37–39 | Creating, accessing, populating ArrayBuffer |
| `ArrayBufferOperations.scala` | 40–42 | Add, remove, other methods (sort, map, filter...) |
| `ArrayVsArrayBuffer.scala` | 43 | Array: fixed-size, when to use Array vs ArrayBuffer vs List |

## Run

```bash
scala-cli run module08arraybuffer/
```

## Quick Reference

```scala
import scala.collection.mutable.ArrayBuffer

// Create
val buf = ArrayBuffer[Int]()
val buf = ArrayBuffer(1, 2, 3)

// Access / Update
buf(0)           // get by index
buf(0) = 99      // update in place (mutation!)

// Add
buf += 4              // append one
buf ++= List(5, 6)    // append many
buf.prepend(0)        // prepend one
buf.insert(2, 99)     // insert at index

// Remove
buf -= 3              // remove first occurrence of value
buf.remove(0)         // remove at index
buf.clear()           // remove all

// Convert
buf.toList
buf.toVector
List(1,2,3).to(ArrayBuffer)
```
