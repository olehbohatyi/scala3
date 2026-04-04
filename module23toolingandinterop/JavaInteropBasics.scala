package module23toolingandinterop

// Summary: Scala 3 runs on the JVM and interoperates naturally with Java libraries.
// Converters in scala.jdk.CollectionConverters help bridge Java and Scala collection APIs.
// Interop code is easier to maintain when Java-specific concerns stay near the boundary.
// This is one of the most practical skills for real Scala work.
// This file demonstrates converting Java collections and consuming a nullable Java-style result.

import scala.jdk.CollectionConverters.*

object JavaStyleApi:
    def findName(id: Int): String | Null =
        if id == 1 then "Scala" else null

@main def javaInteropBasics(): Unit =

    // =========================================================
    // Convert Java collections into Scala collections
    // =========================================================

    val javaNames = java.util.List.of("Scala", "Java", "Kotlin")
    val scalaNames = javaNames.asScala.toList
    println(scalaNames.map(_.toUpperCase))

    // =========================================================
    // Handle Java-style nullable values at the boundary
    // =========================================================

    val maybeName = Option(JavaStyleApi.findName(1)).getOrElse("unknown")
    val missingName = Option(JavaStyleApi.findName(2)).getOrElse("unknown")
    println(maybeName)
    println(missingName)
