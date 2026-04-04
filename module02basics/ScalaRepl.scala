package module02basics

// Summary: The Scala REPL (Read-Evaluate-Print Loop) is an interactive
// playground for experimenting with code without writing a full program.
// Start it with: scala-cli repl
// This file reproduces the same experiments as runnable code so you can
// compare REPL output with compiled output — results are identical.

@main def scalaRepl(): Unit =

    // --- Expressions are evaluated and the result is shown ---
    // in the REPL: scala> 1 + 2  =>  val res0: Int = 3
    // the REPL auto-names unnamed results as res0, res1, res2 ...
    val res0 = 1 + 2
    println(s"1 + 2 = $res0")   // 3

    // --- Naming values with val ---
    // in the REPL: scala> val x = 1  =>  val x: Int = 1
    val x = 1
    val y = 2
    println(s"x=$x, y=$y, x+y=${x + y}")   // x=1, y=2, x+y=3

    // --- The result of using a previous res variable ---
    // in the REPL: scala> res0 + 1  =>  val res1: Int = 4
    val res1 = res0 + 1
    println(s"res0 + 1 = $res1")   // 4

    // --- Everything in Scala is an expression with a type ---
    // Int, Double, String, Boolean — the compiler infers all of these
    val intVal    = 42          // Int
    val doubleVal = 3.14        // Double
    val strVal    = "hello"     // String
    val boolVal   = true        // Boolean

    println(s"Int:     $intVal")
    println(s"Double:  $doubleVal")
    println(s"String:  $strVal")
    println(s"Boolean: $boolVal")

    // --- Tab completion hint (REPL only, shown as comment) ---
    // In the REPL, type: 42.  then press Tab
    // You'll see methods like: abs, compareTo, doubleValue, max, min, ...
    // Try: "hello".  Tab  =>  shows all String methods

    // --- Methods available on Int (same ones tab-completion reveals) ---
    val n = -7
    println(s"abs of $n  = ${n.abs}")          // 7
    println(s"max(n, 0)  = ${n.max(0)}")       // 0
    println(s"min(n, 0)  = ${n.min(0)}")       // -7
    println(s"to Double  = ${n.toDouble}")     // -7.0
    println(s"to String  = ${n.toString}")     // "-7"

    // --- Methods available on String ---
    val s = "Scala"
    println(s"length     = ${s.length}")        // 5
    println(s"uppercase  = ${s.toUpperCase}")   // SCALA
    println(s"reversed   = ${s.reverse}")       // alacS
    println(s"contains S = ${s.contains("S")}") // true
