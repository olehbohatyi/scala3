package module17contextfunctions

// Summary: A context function is a function that receives contextual parameters automatically.
// Its type uses the ?=> syntax, which says "given this context, produce that result".
// Context functions work well for request-scoped data, capabilities, and small DSLs.
// They let you write logic that reads dependencies from context without threading them manually.
// This file shows the basic syntax and invocation style for context functions.

case class RequestContext(user: String, traceId: String)

type WithRequest[A] = RequestContext ?=> A

def currentUser: WithRequest[String] = summon[RequestContext].user

def currentTraceId: WithRequest[String] = summon[RequestContext].traceId

def renderDashboard: WithRequest[String] =
    s"dashboard(user=${currentUser}, trace=${currentTraceId})"

@main def basicContextFunctions(): Unit =

    // =========================================================
    // Invoke a context function by supplying a contextual value
    // =========================================================

    val ctx = RequestContext("alice", "trace-101")
    println(renderDashboard(using ctx))

    // =========================================================
    // Different context, different result
    // =========================================================

    val another = RequestContext("bob", "trace-202")
    println(renderDashboard(using another))
