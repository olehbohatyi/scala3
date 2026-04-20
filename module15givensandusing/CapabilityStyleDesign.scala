package module15givensandusing

// Summary: Capability-style design passes required powers through contextual parameters.
// This makes dependencies visible in types while keeping call sites concise.
// The style is useful for logging, clocks, random sources, and controlled side effects.
// It encourages separating business logic from how capabilities are provided.
// This file demonstrates a tiny program that requires time and logging capabilities.

trait Clock:
    def now(): String

trait Logger:
    def log(message: String): Unit

case class SystemClock(timestamp: String) extends Clock:
    def now(): String = timestamp

case class ConsoleLogger(prefix: String) extends Logger:
    def log(message: String): Unit = println(s"[$prefix] $message")

type Program[A] = (Clock, Logger) ?=> A

def createAuditMessage(action: String): Program[String] =
    val time = summon[Clock].now()
    val logger = summon[Logger]
    logger.log(s"recording action=$action at $time")
    s"audit(action=$action,time=$time)"

@main def capabilityStyleDesign(): Unit =

    // =========================================================
    // Capabilities are supplied once and used throughout the program
    // =========================================================

    given Clock = SystemClock("2026-04-04T17:00:00Z")
    given Logger = ConsoleLogger("capability")

    println(createAuditMessage("publish-course"))
