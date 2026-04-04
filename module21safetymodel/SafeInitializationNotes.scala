package module21safetymodel

// Summary: Safe initialization is easier when required state is established before behavior runs.
// Constructor parameters, final vals, and small explicit dependencies reduce initialization hazards.
// Delayed mutation and partially configured objects make reasoning harder.
// Scala 3 pushes developers toward more explicit and safer initialization styles.
// This file demonstrates a small setup pattern that avoids partially initialized state.

case class AppConfig(host: String, port: Int)

class HttpClient(config: AppConfig):
    val baseUrl: String = s"http://${config.host}:${config.port}"

    def get(path: String): String = s"GET $baseUrl$path"

@main def safeInitializationNotes(): Unit =

    // =========================================================
    // The dependency is complete before behavior is used
    // =========================================================

    val client = HttpClient(AppConfig("localhost", 8080))
    println(client.baseUrl)
    println(client.get("/health"))
