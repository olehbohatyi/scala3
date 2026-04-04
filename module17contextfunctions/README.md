# Module 17 — Context Functions

Covers context functions and their practical use in Scala 3.

## Files

| File | Topic |
|------|-------|
| `BasicContextFunctions.scala` | Defining and invoking context functions |
| `ContextFunctionValues.scala` | Context functions as first-class values |
| `ContextualWorkflows.scala` | Combining contextual dependencies in small workflows |
| `ContextFunctionsVsUsing.scala` | Comparing context functions with regular `using` clauses |

## Run

```bash
scala-cli run module17contextfunctions/
```

## Quick Reference

```scala
case class RequestContext(user: String, traceId: String)

type WithRequest[A] = RequestContext ?=> A

def currentUser: WithRequest[String] = summon[RequestContext].user

val render: WithRequest[String] =
    s"user=${summon[RequestContext].user}"
```
