package module14scala3syntax

// Summary: Scala 3 supports creator applications, letting constructors be called without new in many cases.
// This syntax works naturally for case classes and some other creator-style APIs.
// The goal is to make object creation read more like normal function application.
// It fits Scala's general preference for concise but readable construction syntax.
// This file demonstrates the style with case classes and companion apply methods.

case class Course(name: String, lessons: Int)

class Module(val id: Int, val title: String)
object Module:
    def apply(id: Int, title: String): Module = new Module(id, title)

@main def creatorApplications(): Unit =

    // =========================================================
    // Case classes already use function-like construction
    // =========================================================

    val course = Course("Scala 3", 24)
    println(course)

    // =========================================================
    // Companion apply provides a creator-style API
    // =========================================================

    val module = Module(14, "Scala 3 Syntax")
    println(s"module=${module.id}:${module.title}")
