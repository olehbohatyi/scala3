# Scala Mock Interview Pack

A compact mock interview pack built on top of the Scala 3 course repository.

## How to Use

- Answer each question out loud in 60–90 seconds.
- Add one trade-off after your first answer.
- Add one anti-example or common mistake.
- For coding tasks, aim for 10–15 minutes per task.
- After solving, explain your design as if you were speaking to an interviewer.

---

## Section 1: Core Language

### 1. What does expression-oriented programming mean in Scala?

**Expected answer:**
- In Scala, many constructs return values.
- `if`, `match`, `try`, code blocks, and `for/yield` are expressions.
- This reduces the need for temporary mutable variables and supports a more compositional style.

**Follow-ups:**
- How is `for` different from `for/yield`?
- Why is expression-oriented code useful in FP?

---

### 2. Why does Scala not need a ternary operator?

**Expected answer:**
- Because `if/then/else` already returns a value.
- A separate ternary operator would duplicate existing functionality.

**Follow-ups:**
- Does this improve readability?
- Would you still use `if` for very large branches?

---

### 3. What is the practical difference between `val` and `var`?

**Expected answer:**
- `val` is an immutable binding and should be the default.
- `var` is mutable and should be used only when mutation is justified.
- Preferring `val` reduces state complexity.

**Follow-ups:**
- When is `var` still reasonable?
- Where is mutation acceptable in a real application?

---

## Section 2: Collections

### 4. When would you choose `List` over `Vector`?

**Expected answer:**
- `List` is good for head/tail recursion and prepend-heavy logic.
- `Vector` is a better general immutable default when you need more balanced performance, especially for indexed access.

**Follow-ups:**
- Why is indexed access on `List` expensive?
- What would be your default immutable sequence in application code?

---

### 5. What is the difference between `Array` and `ArrayBuffer`?

**Expected answer:**
- `Array` has fixed size and is mutable.
- `ArrayBuffer` is mutable and growable.
- `ArrayBuffer` is often easier when elements are added or removed dynamically.

**Follow-ups:**
- When would you avoid mutable collections completely?
- Where are mutable collections still acceptable?

---

### 6. Explain `map`, `filter`, and `flatMap`.

**Expected answer:**
- `map` transforms each element.
- `filter` keeps elements matching a predicate.
- `flatMap` transforms and flattens one level.

**Follow-ups:**
- Why is `flatMap` especially important with `Option` and `Either`?
- How is `flatMap` related to `map` + `flatten`?

---

### 7. What is `foldLeft` used for?

**Expected answer:**
- `foldLeft` reduces a collection into one value using an accumulator.
- It is useful for totals, summaries, maps, grouped results, and custom aggregation.
- It often replaces manual loops.

**Follow-ups:**
- How does `foldLeft` differ from `reduce`?
- Why is `foldLeft` safer on empty collections?

---

### 8. When is a tuple appropriate, and when should you use a case class instead?

**Expected answer:**
- Tuple is good for local, temporary grouping of values.
- Case class is better for domain meaning and long-term readability.
- Named fields are better when data represents a real concept.

**Follow-ups:**
- When do tuples become too cryptic?
- Would you expose tuples in a public API?

---

## Section 3: Functional Programming

### 9. What is a pure function?

**Expected answer:**
- It always returns the same output for the same input.
- It has no side effects.
- Pure functions are easier to test and reason about.

**Follow-ups:**
- Is reading current time pure?
- Is printing to console pure?

---

### 10. What is referential transparency?

**Expected answer:**
- An expression is referentially transparent if it can be replaced by its value without changing program behavior.
- This is a practical way to think about purity.

**Follow-ups:**
- Why does mutation break referential transparency?
- Why is this useful in code review?

---

### 11. What is a higher-order function?

**Expected answer:**
- A higher-order function takes a function as input, returns a function, or both.
- Examples include `map`, `filter`, `foldLeft`, and custom pipelines.

**Follow-ups:**
- Why are HOFs so common in Scala?
- What is the practical benefit in business code?

---

### 12. What is a closure?

**Expected answer:**
- A closure is a function that captures values from an outer scope.
- This is useful for custom behavior and function factories.

**Follow-ups:**
- Why can capturing mutable `var` be risky?
- What bugs can closures introduce?

---

### 13. What is the difference between currying and partial application?

**Expected answer:**
- Currying means writing a function with multiple parameter lists, for example `f(a)(b)`.
- Partial application means fixing some arguments and getting a new function back.
- They are related, but they are not the same thing.

**Follow-ups:**
- Why is currying useful in Scala APIs?
- Give one example where partial application improves readability.

---

## Section 4: Error Handling

### 14. When should you use `Option`?

**Expected answer:**
- When absence is expected and no detailed error is needed.
- `Option` is a safer replacement for `null`.

**Follow-ups:**
- Why is `.get` discouraged?
- When does `Option` become too weak?

---

### 15. When should you use `Either`?

**Expected answer:**
- When you need success/failure with explicit domain error information.
- `Either` is often better than exceptions for business validation.

**Follow-ups:**
- Why is `Either` better than throwing exceptions for expected failures?
- What would you put on the `Left` side in a real codebase?

---

### 16. When should you use `Try`?

**Expected answer:**
- When wrapping exception-based APIs.
- `Try` is useful at technical boundaries where exceptions may occur.

**Follow-ups:**
- Why is `Try` usually not the best domain model?
- How would you convert `Try` to `Either`?

---

### 17. Explain `Option` vs `Either` vs `Try` in one short answer.

**Expected answer:**
- `Option`: value may be absent.
- `Either`: success or meaningful business error.
- `Try`: success or captured exception.

**Follow-ups:**
- Which one for parsing user input?
- Which one for network or file boundary?

---

## Section 5: OOP and Modeling

### 18. When would you use a regular class instead of a case class?

**Expected answer:**
- Use a case class for immutable data.
- Use a regular class for encapsulated mutable state, service behavior, or controlled construction.
- Case classes are ideal for domain records and value objects.

**Follow-ups:**
- Why are case classes so common in Scala domain modeling?
- When is a private constructor useful?

---

### 19. Why use a companion object?

**Expected answer:**
- For factory methods, smart constructors, parsing, and utilities tied to a type.
- Companion objects work well with private constructors.

**Follow-ups:**
- What is a smart constructor?
- Why might `apply` improve usability?

---

### 20. Traits vs inheritance vs composition?

**Expected answer:**
- Traits are great for contracts and reusable behavior.
- Composition is usually safer and more flexible than deep inheritance.
- Inheritance should be used only for stable, real is-a relationships.

**Follow-ups:**
- What problems appear in deep inheritance trees?
- Why is composition often easier to test?

---

### 21. What do Scala 3 enums give you?

**Expected answer:**
- A clean way to model finite sets of states, roles, or commands.
- They are useful for workflow and domain state modeling.

**Follow-ups:**
- When is enum enough?
- When would you choose a sealed trait instead?

---

### 22. Why are sealed traits useful?

**Expected answer:**
- They define a closed family of variants.
- This helps exhaustive pattern matching.
- They are excellent for workflow and state modeling.

**Follow-ups:**
- What does “closed set of cases” mean?
- Why is exhaustiveness valuable?

---

### 23. What is an ADT?

**Expected answer:**
- Algebraic Data Type combines product types and sum types.
- Product type: case class with fields.
- Sum type: enum or sealed trait with alternatives.
- ADTs model domains more clearly and safely.

**Follow-ups:**
- Show a workflow as an ADT.
- Why are ADTs useful for preventing invalid states?

---

### 24. What does “make illegal states unrepresentable” mean?

**Expected answer:**
- Design the type model so invalid combinations cannot be created in the first place.
- It is better to prevent impossible states than to validate them later.

**Follow-ups:**
- Give an example of a bad model and a better ADT-based model.
- Why does this reduce bugs?

---

### 25. How would you combine OOP and FP in a real Scala application?

**Expected answer:**
- Use immutable case classes and ADTs for the domain core.
- Use classes, objects, and traits for services, policies, orchestration, and boundaries.
- A strong default is immutable core plus thin mutable shell.

**Follow-ups:**
- Which part should stay pure?
- Which part may be stateful?

---

## Section 6: Applied Design Questions

### 26. How would you model a hiring pipeline?

**Expected answer:**
- Candidate and feedback as immutable records.
- Stage and decision as enum or sealed trait.
- Decision policy as a trait or service.
- Validation and outcomes as `Either`.

**Follow-ups:**
- Where would mutable state live?
- How would you test the decision policy?

---

### 27. How would you model an expense tracker?

**Expected answer:**
- Transaction as immutable data.
- Budget status as an ADT.
- Tracker service for orchestration, validation, and reporting.
- Aggregation through collection pipelines.

**Follow-ups:**
- Which parts should be pure?
- How would you extend this with category limits?

---

### 28. How would you model a support ticket workflow?

**Expected answer:**
- Ticket as immutable record.
- Priority and status as enums.
- Assignment strategy as a trait.
- Board/service responsible for transitions and metrics.

**Follow-ups:**
- Where would you enforce valid transitions?
- How would you handle audit history?

---

## Section 7: Live Coding Tasks

### Task 1: Safe Parsing

Write:

```scala
def parsePositiveInt(value: String): Either[String, Int]
```

**Requirements:**
- parse from string
- must be greater than zero
- return meaningful errors

**What interviewer checks:**
- `toIntOption`
- `Either`
- no exception-driven flow for normal validation

---

### Task 2: Collection Pipeline

Given:

```scala
case class User(name: String, age: Int, active: Boolean)
```

Return names of active users older than 18 in uppercase, sorted alphabetically.

**What interviewer checks:**
- `filter`
- `map`
- clean pipeline
- no unnecessary mutation

---

### Task 3: ADT Modeling

Model:

```scala
sealed trait PaymentMethod
```

with:
- `Card`
- `Wire`
- `PayPal`

and implement:

```scala
def label(method: PaymentMethod): String
```

**What interviewer checks:**
- ADT thinking
- pattern matching
- illegal states unrepresentable

---

### Task 4: Small Service Design

Build a tiny `LibraryService`:
- add books
- checkout
- return
- return error if already loaned

**What interviewer checks:**
- domain/service separation
- `Either`
- state transitions
- readable design

---

### Task 5: Fold-Based Aggregation

Given:

```scala
case class Tx(category: String, amount: BigDecimal)
```

Return:

```scala
Map[String, BigDecimal]
```

with sum per category.

**What interviewer checks:**
- `foldLeft`
- immutable accumulation
- avoiding immediate fallback to mutable maps

---

## Section 8: Senior-Level Follow-Ups

Use these after answering the main question:

- Why did you choose `Vector` over `List`?
- Why `Either` and not `Try`?
- Which parts of this code are pure?
- Which parts are orchestration only?
- Where are the invariants?
- Where is composition better than inheritance?
- How would this scale if the model grew 10x?
- Which parts would you split into separate modules?
- Which errors are domain errors and which are technical?
- What in this solution demonstrates engineering maturity?

---

## Section 9: Self-Run Mock Interview Format

### 45-minute version

- 10 minutes: core language and collections
- 10 minutes: FP and errors
- 10 minutes: OOP and domain modeling
- 15 minutes: one live coding task

### 90-minute version

- 15 minutes: core language
- 15 minutes: collections
- 20 minutes: FP and error handling
- 20 minutes: OOP and domain modeling
- 20 minutes: live coding + code explanation

---

## Final Reminder

Do not answer with syntax only.

For strong answers:
- explain the construct
- explain the trade-off
- explain when to use it
- explain what bug or design problem it prevents

---
