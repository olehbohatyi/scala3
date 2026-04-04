package module05constructs

import scala.io.Source

// Summary: try/catch/finally handles JVM exceptions in an expression-oriented way.
// try/catch is an EXPRESSION — it returns the value of the successful branch or catch.
// catch uses pattern matching syntax with 'case' — multiple exception types supported.
// finally ALWAYS runs (cleanup code) and its value is discarded — it returns Unit.
// Part 1: basic exception handling. Part 2 (module10) covers functional alternatives.

@main def tryCatchFinally(): Unit =

    // =========================================================
    // Basic try/catch — Scala 3 syntax (no parentheses on catch)
    // =========================================================

    // toInt throws NumberFormatException on invalid input
    try
        val n = "hello".toInt
        println(s"parsed: $n")
    catch
        case e: NumberFormatException =>
            println(s"caught NumberFormatException: ${e.getMessage}")

    // =========================================================
    // try/catch is an EXPRESSION — returns a value (EOP)
    // =========================================================

    // book example: safe parseInt that returns -1 on failure
    def safeParseInt(s: String): Int =
        try s.toInt
        catch
            case _: NumberFormatException => -1   // _ means we ignore the exception object

    println(safeParseInt("42"))      // 42
    println(safeParseInt("abc"))     // -1
    println(safeParseInt("100"))     // 100

    // =========================================================
    // Catching multiple exception types
    // =========================================================

    def riskyDivide(a: String, b: String): String =
        try
            val result = a.toInt / b.toInt
            s"result = $result"
        catch
            case e: NumberFormatException => s"bad number: ${e.getMessage}"
            case e: ArithmeticException   => s"math error: ${e.getMessage}"

    println(riskyDivide("10", "2"))    // result = 5
    println(riskyDivide("10", "abc"))  // bad number: For input string: "abc"
    println(riskyDivide("10", "0"))    // math error: / by zero

    // =========================================================
    // Catching a broad exception type
    // =========================================================

    // Exception catches all checked exceptions
    // Throwable catches absolutely everything (including errors like OutOfMemoryError)
    def parseDouble(s: String): Double =
        try s.toDouble
        catch
            case e: Exception =>
                System.err.println(s"Error parsing '$s': ${e.getMessage}")
                Double.NaN   // sentinel value indicating "no valid result"

    println(parseDouble("3.14"))   // 3.14
    println(parseDouble("oops"))   // NaN  (error printed to STDERR)

    // =========================================================
    // finally — always executes, used for cleanup
    // =========================================================

    // finally runs whether the try succeeds or catch fires
    println("\n--- finally demo ---")
    def withCleanup(input: String): Unit =
        try
            val n = input.toInt
            println(s"  success: parsed $n")
        catch
            case e: NumberFormatException =>
                println(s"  failure: ${e.getMessage}")
        finally
            println("  finally: cleanup done")  // ALWAYS runs

    withCleanup("42")     // success + finally
    withCleanup("bad")    // failure + finally

    // =========================================================
    // finally value is DISCARDED — it returns Unit
    // =========================================================

    // the result comes from try or catch — NOT from finally
    val result =
        try 42
        catch case _: Exception => -1
        finally println("  finally runs but its value is dropped")

    println(s"result = $result")   // 42

    // =========================================================
    // Real pattern: reading a resource safely
    // =========================================================

    def readFirstLine(path: String): String =
        var source: Source = null
        try
            source = Source.fromFile(path)
            source.getLines().nextOption().getOrElse("empty file")
        catch
            case e: java.io.FileNotFoundException =>
                s"file not found: $path"
            case e: Exception =>
                s"unexpected error: ${e.getMessage}"
        finally
            // close the resource if it was opened — check for null
            if source != null then source.close()

    println(readFirstLine("/etc/hostname"))               // first line or error
    println(readFirstLine("/nonexistent/file.txt"))       // file not found

    // =========================================================
    // Additional: re-throwing an exception
    // =========================================================

    def strictParse(s: String): Int =
        try s.toInt
        catch
            case e: NumberFormatException =>
                println(s"logging error: $s is not a number")
                throw e   // re-throw after logging

    try
        println(strictParse("99"))    // 99
        println(strictParse("bad"))   // throws after logging
    catch
        case e: NumberFormatException =>
            println(s"outer catch: ${e.getMessage}")
