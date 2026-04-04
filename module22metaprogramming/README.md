# Module 22 — Metaprogramming

Covers practical Scala 3 metaprogramming features from inline to quotes and reflection.

## Files

| File | Topic |
|------|-------|
| `InlineBasics.scala` | `inline` methods and compile-time branching |
| `CompileTimeConstants.scala` | `scala.compiletime.constValue` with singleton types |
| `QuotesAndExpr.scala` | Quotes, `Expr`, and simple macros |
| `ReflectionBasics.scala` | Basic use of `quotes.reflect` |
| `SplicesAndQuotedPatterns.scala` | Splices and quoted code composition |
| `ReflectTreeInspection.scala` | Inspecting syntax trees with `quotes.reflect` |

## Run

```bash
scala-cli run module22metaprogramming/
```

## Quick Reference

```scala
inline def twice(inline value: Int): Int = value + value

inline def typeNameOf[A]: String = ${ typeNameOfImpl[A] }

def typeNameOfImpl[A: Type](using Quotes): Expr[String] = Expr(Type.show[A])
```
