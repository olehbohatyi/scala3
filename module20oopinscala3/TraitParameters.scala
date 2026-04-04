package module20oopinscala3

// Summary: Scala 3 lets traits accept constructor parameters.
// This makes reusable behavior easier to configure without extra abstract members.
// Trait parameters work well for small policies, labels, and formatting concerns.
// They are a clean upgrade over older patterns that relied on separate vals or initialization order.
// This file demonstrates configured logging behavior shared through a trait.

trait PrefixLogger(prefix: String):
    def log(message: String): String = s"[$prefix] $message"

class AuditLogger extends PrefixLogger("audit"):
    def record(action: String): String = log(s"action=$action")

class SecurityLogger extends PrefixLogger("security"):
    def warn(event: String): String = log(s"warning=$event")

@main def traitParameters(): Unit =

    // =========================================================
    // Trait parameters configure reusable behavior
    // =========================================================

    val audit = AuditLogger()
    val security = SecurityLogger()

    println(audit.record("user-created"))
    println(security.warn("failed-login"))
