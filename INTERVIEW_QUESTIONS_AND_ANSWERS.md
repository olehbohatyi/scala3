# Scala Interview Questions and Short Answers

Use these answers as compact rehearsal notes.

## Core language

### What does expression-oriented programming mean in Scala?

It means constructs like `if`, `match`, `try`, blocks, and `for/yield` return values.
This reduces mutation and lets you assign results directly to `val`.

### Does Scala have a ternary operator?

No.
`if/then/else` already works as an expression, so a separate ternary operator is not needed.

### What is the practical difference between `val` and `var`?

`val` is immutable binding and is the default.
`var` is mutable binding and should be used only when mutation is clearly justified.

## Collections

### When would you choose `List` over `Vector`?

Choose `List` for head/tail style processing and prepend-heavy workflows.
Choose `Vector` as the general immutable default when you need better random access and balanced updates.

### What is the difference between `map`, `flatMap`, and `filter`?

`map` transforms each element.
`filter` keeps elements that satisfy a predicate.
`flatMap` transforms each element into a collection-like value and then flattens one level.

### What is `foldLeft` used for?

`foldLeft` reduces a collection to one value while carrying an accumulator.
It is one of the most important methods for deriving totals, summaries, and transformed aggregates.

## FP

### What is a pure function?

A pure function always returns the same output for the same input and has no side effects.
That makes it easy to test, reason about, and compose.

### What is referential transparency?

If an expression is referentially transparent, you can replace it with its result without changing program behavior.
This is the practical test for purity.

### What is a higher-order function?

A higher-order function takes a function, returns a function, or both.
Examples include `map`, `filter`, custom pipelines, and callback-based orchestration.

### What is a closure?

A closure is a function that captures values from its outer scope.
This is useful, but capturing mutable `var` can create surprising behavior.

### What is the difference between currying and partial application?

Currying means writing a function with multiple parameter lists like `f(a)(b)`.
Partial application means fixing some arguments now and returning a function for the rest.

## Errors

### When should you use `Option`?

Use `Option` when absence is expected and there is no meaningful error detail to return.
Typical examples are optional fields and lookups that may not find a value.

### When should you use `Either`?

Use `Either` when you need explicit success or failure with domain error information.
It is a better fit than exceptions for business validation.

### When should you use `Try`?

Use `Try` at technical boundaries that may throw exceptions.
It is useful when wrapping legacy or exception-driven APIs into value-based flows.

### Why is `.get` on `Option` discouraged?

Because it throws at runtime on `None` and throws away the safety of the type.
Prefer `map`, `flatMap`, `fold`, `getOrElse`, or pattern matching.

## OOP and modeling

### When should you use a `case class` instead of a regular class?

Use a `case class` for immutable domain data.
Use a regular class when you need controlled mutable behavior, encapsulation, or service-style orchestration.

### Why use companion objects?

Companion objects are a natural place for factory methods, smart constructors, parsing, and utilities tied to a type.
They also make private constructors practical.

### Traits or inheritance?

Start with traits and composition for reusable behavior and service contracts.
Use inheritance only when the subtype relationship is stable and genuinely expresses an is-a relationship.

### Why are sealed traits useful?

They define a closed set of alternatives in one file.
That gives safer pattern matching and clearer workflow or state modeling.

### What does “make illegal states unrepresentable” mean?

It means designing types so invalid combinations cannot be constructed in the first place.
ADTs are one of the best tools for this in Scala.

## Architecture

### How would you combine OOP and FP in a real application?

Use immutable case classes and ADTs for the domain core.
Use classes, traits, and objects for services, policies, orchestration, and integration boundaries.

### What is a good compact architecture style for Scala?

A strong default is immutable core plus thin mutable shell.
Domain rules stay pure where possible, while stateful services coordinate workflows and I/O.

## Portfolio discussion

### Which files are best to show in an interview?

Start with `module13projects/ExpenseTrackerApp.scala` and `InterviewPipelineApp.scala`.
Then connect them to `module11domainoop` and `module12domainfp` to explain design choices.
