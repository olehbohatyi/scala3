package module15givensandusing

// Summary: summon retrieves the contextual value of a requested type from the current scope.
// It is useful when you need direct access to a given inside a generic or helper method.
// Together, using and summon form a core part of Scala 3 contextual abstractions.
// This pattern helps keep APIs concise while still making dependencies explicit in the type signature.
// This file shows configuration flowing contextually through helper methods.

trait AppConfig:
    def env: String
    def debug: Boolean

object AppConfig:
    given defaultConfig: AppConfig with
        def env: String = "dev"
        def debug: Boolean = true

def banner(using config: AppConfig): String =
    s"env=${config.env}, debug=${config.debug}"

def debugMessage(message: String)(using AppConfig): String =
    val config = summon[AppConfig]
    if config.debug then s"[${config.env}] $message" else message

@main def summonAndContextParameters(): Unit =

    // =========================================================
    // Import one given config for the whole block
    // =========================================================

    import AppConfig.given

    println(banner)
    println(debugMessage("loaded contextual configuration"))

    // =========================================================
    // summon fetches the actual contextual value
    // =========================================================

    val config = summon[AppConfig]
    println(s"currentEnv=${config.env}")
