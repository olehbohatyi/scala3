package module17contextfunctions

// Summary: Context functions become more interesting when several contextual services are involved.
// They can express small workflows where environment, logging, and policies are available implicitly.
// This keeps the business code compact while preserving explicit dependency types.
// The pattern is useful for lightweight service composition without building large frameworks.
// This file demonstrates a workflow that requires both configuration and logging from context.

trait Config:
    def env: String
    def featureX: Boolean

trait Logger:
    def log(message: String): Unit

case class ConsoleLogger(prefix: String) extends Logger:
    def log(message: String): Unit = println(s"[$prefix] $message")

case class DefaultConfig(env: String, featureX: Boolean) extends Config

type Program[A] = (Config, Logger) ?=> A

def computePlan(user: String): Program[String] =
    val config = summon[Config]
    val logger = summon[Logger]
    logger.log(s"planning for $user in env=${config.env}")
    val flag = if config.featureX then "featureX=on" else "featureX=off"
    s"plan(user=$user, $flag)"

@main def contextualWorkflows(): Unit =

    // =========================================================
    // Provide both contextual services to run the workflow
    // =========================================================

    given Config = DefaultConfig("dev", featureX = true)
    given Logger = ConsoleLogger("workflow")

    println(computePlan("scala-student"))
