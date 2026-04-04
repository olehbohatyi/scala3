# Module 13 — Mini Projects: OOP + FP Integration

A compact portfolio-oriented module that combines object-oriented design and functional domain modeling.

## Files

| File | Focus |
|------|-------|
| `ExpenseTrackerApp.scala` | Stateful service + immutable domain records + functional reporting |
| `InterviewPipelineApp.scala` | Hiring workflow, scoring policy, sealed decisions, validation |
| `LibraryRentalApp.scala` | Inventory + loan workflow, dates, fines, service modeling |
| `SupportTicketApp.scala` | Ticket board, assignment strategy, workflow transitions, metrics |

## Why this module matters

These projects are intentionally small but interview-friendly:

- They model realistic business domains.
- They combine classes, traits, objects, enums, case classes, `Either`, and collection pipelines.
- They show the difference between domain data (immutable) and application services (stateful or orchestrating).
- They are suitable as reference code for interviews, mentoring, and portfolio examples.

## Run

```bash
scala-cli run module13projects/ExpenseTrackerApp.scala
scala-cli run module13projects/InterviewPipelineApp.scala
scala-cli run module13projects/LibraryRentalApp.scala
scala-cli run module13projects/SupportTicketApp.scala

# All at once
scala-cli run module13projects/
```
