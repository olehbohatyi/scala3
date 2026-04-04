# Module 09 — Map and Set

Covers chapters 44–50 of *Learn Scala 3 The Fast Way*.

## Files

| File | Chapters | Topic |
|------|----------|-------|
| `MapCollection.scala` | 44–49 | Immutable Map: create, access, CRUD, looping, common methods |
| `MutableMap.scala` | 44–49 | Mutable Map: in-place add/update/remove |
| `SetCollection.scala` | 50 | Immutable/Mutable Set, set algebra operations |

## Run

```bash
scala-cli run module09mapset/
```

## Quick Reference

```scala
// Immutable Map
val m = Map("a" -> 1, "b" -> 2)
m("a")                     // 1 — throws if missing
m.get("a")                 // Some(1)
m.getOrElse("z", 0)        // 0
m + ("c" -> 3)             // new Map with extra entry
m - "a"                    // new Map without key "a"
m.updated("a", 99)         // new Map with updated value
for (k, v) <- m do ...     // iterate

// Mutable Map
import scala.collection.mutable
val mm = mutable.Map("a" -> 1)
mm("b") = 2                // add or update
mm += ("c" -> 3)           // add
mm -= "a"                  // remove
mm.getOrElseUpdate("x", 0) // get or insert default

// Immutable Set
val s = Set(1, 2, 3)
s + 4                      // new Set with 4
s - 2                      // new Set without 2
s.contains(3)              // true
s1 | s2                    // union
s1 & s2                    // intersection
s1 &~ s2                   // difference
```
