package module14scala3syntax

// Summary: Scala 3 uses the @main annotation to define runnable entry points without boilerplate.
// This replaces the old pattern of always writing an object with a main(args) method.
// @main methods can accept parameters directly, which makes them convenient for scripts and CLI tools.
// Default parameter values also work, making examples easier to run.
// This file demonstrates a small command-line style program written in idiomatic Scala 3.

@main def scala3MainMethods(name: String = "Scala 3", repeat: Int = 2, excited: Boolean = true): Unit =

    // =========================================================
    // Direct command-line friendly parameters
    // =========================================================

    val punctuation = if excited then "!" else "."
    println(s"Preparing greeting for $name")

    // =========================================================
    // A small loop driven by method parameters
    // =========================================================

    for i <- 1 to repeat do
        println(s"$i. Hello, $name$punctuation")

    // =========================================================
    // The same method can be run with explicit arguments
    // =========================================================

    val exampleInvocation = "scala-cli run module14scala3syntax/MainMethods.scala -- Alice 3 false"
    println(exampleInvocation)
