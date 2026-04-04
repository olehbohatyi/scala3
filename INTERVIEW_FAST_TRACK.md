# Scala 3 Interview Fast-Track

A focused review layer on top of the full course.

## Goal

Use this document when you do not want to reread the whole repository before an interview.
Focus on the smallest set of files that gives the highest return.

## The 15 Must-Know Topics

1. Expression-oriented programming: `if`, `match`, `try`, `for/yield` return values.
2. Immutable vs mutable collections.
3. `map`, `filter`, `flatMap`, `foldLeft`, `reduce`.
4. `List` vs `Vector` vs `ArrayBuffer` vs `Array`.
5. Tuples, destructuring, `zip`, `unzip`.
6. `Option`, `Either`, `Try` and when to choose each.
7. Pure functions and referential transparency.
8. Higher-order functions and function types.
9. Closures, currying, partially applied functions.
10. Classes vs case classes.
11. Traits vs inheritance vs composition.
12. Companion objects and smart constructors.
13. Enums and sealed traits.
14. Algebraic Data Types for workflow/domain states.
15. Small realistic service design: immutable domain + stateful orchestration.

## 60-Minute Review

### Part 1 — Core language

- `module05constructs/IfElseAndEOP.scala`
- `module05constructs/ForExpressions.scala`
- `module05constructs/TryCatchFinally.scala`

### Part 2 — Collections

- `module07collections/MapAndFilter.scala`
- `module07collections/SequenceMethods.scala`
- `module09mapset/MapCollection.scala`

### Part 3 — FP

- `module10functions/PureFunctions.scala`
- `module10functions/HigherOrderFunctions.scala`
- `module10functions/Currying.scala`

### Part 4 — Domain modeling

- `module12domainfp/CaseClasses.scala`
- `module12domainfp/SealedTraitsAndAdts.scala`
- `module12domainfp/EitherBasics.scala`

### Part 5 — Realistic examples

- `module13projects/ExpenseTrackerApp.scala`
- `module13projects/InterviewPipelineApp.scala`

## 1-Day Review

### Block 1 — Syntax and expressions

- `module01setup`
- `module05constructs`

### Block 2 — Collections and transformations

- `module06tuples`
- `module07collections`
- `module08arraybuffer`
- `module09mapset`

### Block 3 — Functions and FP thinking

- `module10functions`
- `module12domainfp/OptionBasics.scala`
- `module12domainfp/EitherBasics.scala`
- `module12domainfp/TryBasics.scala`

### Block 4 — OOP and domain modeling

- `module11domainoop`
- `module12domainfp/SealedTraitsAndAdts.scala`
- `module13projects`

## 3-Day Review

### Day 1

- `module01setup` to `module05constructs`
- Goal: syntax fluency and expression-oriented thinking.

### Day 2

- `module06tuples` to `module10functions`
- Goal: strong collection and functional transformation fluency.

### Day 3

- `module11domainoop` to `module13projects`
- Goal: show system design maturity and domain modeling skill.

## Fast-Track by Topic

## OOP

- `module11domainoop/ClassesAndConstructors.scala`
- `module11domainoop/ObjectsAndCompanions.scala`
- `module11domainoop/TraitsAndMixins.scala`
- `module11domainoop/InheritanceAndOverrides.scala`

## FP

- `module05constructs/IfElseAndEOP.scala`
- `module10functions/PureFunctions.scala`
- `module10functions/HigherOrderFunctions.scala`
- `module10functions/Closures.scala`
- `module10functions/Currying.scala`
- `module12domainfp/SealedTraitsAndAdts.scala`

## Collections

- `module07collections/ListClass.scala`
- `module07collections/OtherSequences.scala`
- `module07collections/MapAndFilter.scala`
- `module07collections/SequenceMethods.scala`
- `module08arraybuffer/ArrayVsArrayBuffer.scala`
- `module09mapset/MapCollection.scala`
- `module09mapset/SetCollection.scala`

## Errors

- `module05constructs/TryCatchFinally.scala`
- `module12domainfp/OptionBasics.scala`
- `module12domainfp/EitherBasics.scala`
- `module12domainfp/TryBasics.scala`

## Domain Modeling

- `module11domainoop/EnumsBasics.scala`
- `module12domainfp/CaseClasses.scala`
- `module12domainfp/PatternMatching.scala`
- `module12domainfp/SealedTraitsAndAdts.scala`
- `module13projects/*.scala`

## What to Say in Interviews

### If they ask about OOP in Scala

Say that Scala supports standard OOP with classes, traits, inheritance, and companion objects,
but in application design you usually combine that with immutable data and FP-style transformations.

### If they ask about FP in Scala

Say that FP in Scala starts with pure functions, immutable collections, expression-oriented code,
and explicit domain modeling with `case class`, `enum`, `sealed trait`, `Option`, and `Either`.

### If they ask about domain modeling

Say that you try to model finite workflows with enums or sealed traits,
keep records immutable with case classes,
and push invalid states out of the model rather than validating chaos later.

## Common Mistakes to Avoid

- Using `var` where a value-returning expression is enough.
- Using `List` when random access is required.
- Using `.get` on `Option`.
- Throwing exceptions for normal business validation.
- Using inheritance where a trait or composition is clearer.
- Using mutable collections in the domain layer instead of in the orchestration layer.
- Explaining syntax only, without explaining modeling trade-offs.

## Best Demo Files for Portfolio Discussion

1. `module13projects/ExpenseTrackerApp.scala`
2. `module13projects/InterviewPipelineApp.scala`
3. `module13projects/LibraryRentalApp.scala`
4. `module12domainfp/SealedTraitsAndAdts.scala`
5. `module11domainoop/TraitsAndMixins.scala`

## Final Advice

Before an interview, prioritise explaining trade-offs over listing features.
This repository is strongest when you use it to answer why a specific Scala construct improves correctness, clarity, or maintainability.
