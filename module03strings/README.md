# Module 03 — Strings

Covers chapters 13–15 of *Learn Scala 3 The Fast Way*.

## Files

| File | Chapter | Topic |
|------|---------|-------|
| `StringMethods.scala` | 13 | Common methods: length, trim, split, replace, contains, startsWith... |
| `StringInterpolators.scala` | 14 | s"", f"" (formatted), raw"" (no escapes), custom interpolators |
| `MultilineStrings.scala` | 15 | Triple-quoted strings, stripMargin, stripLeading, multiline SQL/JSON |

## Run

```bash
scala-cli run module03strings/StringMethods.scala
scala-cli run module03strings/StringInterpolators.scala
scala-cli run module03strings/MultilineStrings.scala

# All at once
scala-cli run module03strings/
```

## Quick Reference

```scala
// Common methods
val s = "  Hello, Scala 3!  "
s.length          // 19
s.trim            // "Hello, Scala 3!"
s.toUpperCase     // "  HELLO, SCALA 3!  "
s.contains("Scala") // true
s.replace("Scala", "World")
s.split(", ")     // Array("  Hello", "Scala 3!  ")
s.startsWith("  H") // true
s.drop(2).take(5) // "Hello"

// Interpolators
val name = "Alice"
val age  = 30
s"name=$name, age=$age"             // basic embedding
f"salary: $salary%.2f"              // printf-style formatting
raw"no \n escape here"              // raw string, backslash is literal

// Multiline
val text = """
    |line 1
    |line 2
    """.stripMargin    // removes leading whitespace up to |
```
