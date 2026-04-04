package module14scala3syntax

import scala.util.Try

// Summary: Scala 3 allows top-level definitions, so not everything must live inside an object.
// This makes small programs, scripts, and focused examples easier to write and read.
// Top-level vals, defs, enums, and case classes are common in Scala 3 teaching material.
// The feature reduces boilerplate compared with the old "put everything in an object" style.
// This file shows several top-level definitions working together in one program.

val courseName: String = "Scala 3 Complete Track"
val defaultPort: Int = 8080

def normalizeName(value: String): String =
    value.trim.toLowerCase.replace(' ', '-')

def parsePort(value: String): Option[Int] =
    Try(value.toInt).toOption.filter(_ > 0)

enum BuildMode:
    case Dev, Test, Prod

case class ModuleInfo(name: String, lessons: Int):
    def slug: String = normalizeName(name)

@main def topLevelDefinitions(): Unit =

    // =========================================================
    // Top-level vals and defs
    // =========================================================

    println(courseName)
    println(defaultPort)
    println(normalizeName("Scala 3 Syntax"))
    println(parsePort("9000"))
    println(parsePort("oops"))

    // =========================================================
    // Top-level enum
    // =========================================================

    val mode = BuildMode.Dev
    println(mode)
    println(BuildMode.values.toList)

    // =========================================================
    // Top-level case class
    // =========================================================

    val module = ModuleInfo("Contextual Abstractions", 5)
    println(module)
    println(module.slug)
