# Module 04 — Numeric Types & Math Expressions

Covers chapters 16–17 of *Learn Scala 3 The Fast Way*.

## Files

| File | Chapter | Topic |
|------|---------|-------|
| `NumericTypes.scala` | 16 | Byte, Short, Int, Long, Float, Double, BigInt, BigDecimal, overflow, conversions |
| `MathExpressions.scala` | 17 | Arithmetic operators, integer division, modulo, scala.math, operator precedence |

## Run

```bash
scala-cli run module04numbers/NumericTypes.scala
scala-cli run module04numbers/MathExpressions.scala

# All at once
scala-cli run module04numbers/
```

## Quick Reference

```scala
// Numeric literals
val i: Int        = 1_000_000          // underscore separator
val l: Long       = 1_000_000_000L     // L suffix
val f: Float      = 3.14f              // f suffix
val d: Double     = 3.14               // default decimal type
val bi            = BigInt("999999999999999999999")
val bd            = BigDecimal("123456.789012345678")

// Conversions
42.toDouble        // Int -> Double
3.99.toInt         // Double -> Int (truncates, NOT rounds!)
"42".toInt         // String -> Int (throws if invalid)
42.toString        // Int -> String

// Math operators
10 / 3             // 3     (Int division: truncates)
10.0 / 3           // 3.333 (Double division)
10 % 3             // 1     (modulo / remainder)
2 ** 10            // not in Scala! use math.pow(2, 10)

// scala.math
import scala.math.*
abs(-7)            // 7
pow(2, 10)         // 1024.0
sqrt(16)           // 4.0
ceil(3.2)          // 4.0
floor(3.9)         // 3.0
round(3.5)         // 4
min(3, 7)          // 3
max(3, 7)          // 7
```
