//> using scala 3.10.0
//> using dep com.lihaoyi::os-lib:0.11.8

// Using directives must come before any Scala code, including the package clause;
// Scala CLI ignores directives placed after it.

package module23toolingandinterop

// Summary: Scala CLI is a practical tool for running small Scala 3 programs quickly.
// File-level using directives can define Scala versions and dependencies close to the code.
// This style is convenient for experiments, scripts, interview snippets, and compact course examples.
// Scala CLI helps keep the barrier to running Scala 3 code low.
// This file demonstrates the directive style while remaining runnable inside the course structure.

import os.*

@main def scalaCliDirectives(): Unit =

    // =========================================================
    // Simple Scala CLI style script behavior
    // =========================================================

    // relative to the directory scala-cli was started from (the repo root)
    val workingDir = pwd
    val demoPath = workingDir / "module23toolingandinterop"

    println(s"workingDir=$workingDir")
    println(s"demoPath=$demoPath")
    println(exists(demoPath))
