# Module 01 — Setup & Scala 3 Fundamentals

Covers chapters 2–7 of *Learn Scala 3 The Fast Way*.

## Files

| File | Chapter | Topic |
|------|---------|-------|
| `WhyScala3.scala` | 2–3 | FP+OOP fusion, expressiveness, consistency, collections preview |
| `IndentationSyntax.scala` | 5 | Significant indentation — the biggest syntax change from Scala 2 |
| `CommentsAndCodeBlocks.scala` | 6–7 | Comment style, scoped code blocks, block as expression |

## Run

```bash
# From the repo root
scala-cli run module01setup/WhyScala3.scala
scala-cli run module01setup/IndentationSyntax.scala
scala-cli run module01setup/CommentsAndCodeBlocks.scala

# All at once
scala-cli run module01setup/
```

## Key Rules to Remember

- Always prefer `val` over `var` — mutation is a last resort
- Scala 3 uses **indentation** instead of `{}` for blocks (like Python/Haskell)
- A code block `{ ... }` is an expression — its value is the **last line**
- Use `//` for all comments — one per line, no `/* */`

## Quick Reference

```scala
// if/else — Scala 3 style, no parentheses, no braces
if x > 0 then
    println("positive")
else
    println("non-positive")

// for loop — 'do' replaces the opening brace
for i <- 1 to 5 do
    println(i)

// def — body indented, no braces
def square(n: Int): Int =
    n * n

// code block — value is the last expression
val result = {
    val x = 10
    val y = 20
    x + y   // result == 30
}
```
