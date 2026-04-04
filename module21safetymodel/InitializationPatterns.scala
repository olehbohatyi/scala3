package module21safetymodel

// Summary: Safer initialization comes from making required data explicit in constructors.
// Constructor parameters, final vals, and lazy vals are usually clearer than delayed mutation.
// This reduces the chance of partially initialized objects and surprising order-dependent behavior.
// Scala 3 highlights safe initialization as an important direction for robust code.
// This file demonstrates a small service built from explicit dependencies and lazy derived data.

case class DatabaseConfig(url: String, poolSize: Int)

class DatabaseService(config: DatabaseConfig):
    val url: String = config.url
    lazy val label: String = s"db(url=$url,pool=${config.poolSize})"

    def connect(): String = s"connected:$label"

@main def initializationPatterns(): Unit =

    // =========================================================
    // Required state is provided up front through the constructor
    // =========================================================

    val config = DatabaseConfig("jdbc:postgresql://localhost:5432/app", 8)
    val service = DatabaseService(config)

    println(service.url)
    println(service.connect())
