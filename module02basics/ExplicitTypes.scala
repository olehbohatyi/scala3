package module02basics

// Summary: Scala infers types automatically, but you can declare them explicitly.
// Syntax: val name: Type = value  (colon + type between name and equals sign)
// Explicit types improve readability, document intent, and catch mistakes early.
// Scala's unified type hierarchy: Any > AnyVal (Int, Double...) / AnyRef (String...)

@main def explicitTypes(): Unit =

    // --- Type inference: compiler deduces the type from the value ---
    val inferred = 42          // compiler sees Int
    val inferredS = "hello"    // compiler sees String
    println(s"inferred Int=$inferred, String=$inferredS")

    // --- Explicit type annotation: name: Type = value ---
    // book example: declaring the type explicitly
    val age: Int        = 30
    val name: String    = "Oleh"
    val salary: Double  = 95_000.0
    val isActive: Boolean = true
    val initial: Char   = 'A'

    println(s"age=$age, name=$name, salary=$salary")
    println(s"isActive=$isActive, initial=$initial")

    // --- Explicit type forces the compiler to check compatibility ---
    // val x: Int = "hello"   // compile error: type mismatch
    // val y: Boolean = 1     // compile error: 1 is Int, not Boolean

    // --- Scala primitive types (all start with uppercase unlike Java) ---
    // Byte   : 8-bit  integer, range -128 to 127
    // Short  : 16-bit integer, range -32768 to 32767
    // Int    : 32-bit integer — default integer type
    // Long   : 64-bit integer
    // Float  : 32-bit floating point
    // Double : 64-bit floating point — default decimal type
    // Boolean: true or false
    // Char   : single Unicode character

    val b: Byte    = 127
    val s: Short   = 32767
    val i: Int     = 2_147_483_647     // max Int
    val l: Long    = 9_223_372_036_854_775_807L  // max Long, suffix L
    val f: Float   = 3.14f             // suffix f for Float literal
    val d: Double  = 3.141592653589793
    val bool: Boolean = false
    val ch: Char   = 'Z'

    println(s"Byte=$b, Short=$s, Int=$i")
    println(s"Long=$l")
    println(s"Float=$f, Double=$d")
    println(s"Boolean=$bool, Char=$ch")

    // --- String is a reference type (AnyRef), not AnyVal ---
    // but it is still immutable in Scala (and Java)
    val str: String = "Scala 3"
    println(s"String length=${str.length}")

    // --- When to annotate explicitly (interview tip) ---
    // 1. Public API / method return types — documents the contract
    // 2. When the inferred type is too broad (e.g., Any)
    // 3. When declaring a variable separately from its initialisation
    // 4. To make the code self-documenting

    // --- Additional: explicit return type on a method ---
    // return type comes after the parameter list, before '='
    def double(n: Int): Int = n * 2
    def greet(s: String): String = s"Hello, $s!"

    println(double(21))      // 42
    println(greet("world"))  // Hello, world!

    // --- Additional: Nothing and Unit ---
    // Unit   : return type for methods that produce no value (like void)
    // Nothing: subtype of every type; used for methods that never return
    def printAndReturnUnit(msg: String): Unit = println(msg)
    printAndReturnUnit("Unit means no meaningful return value")
