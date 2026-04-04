package module02basics

// Summary: val declares an immutable value — once assigned it cannot change.
// This mirrors algebraic variables: in math, once x = 5, x stays 5.
// val is the default choice for everything in Scala; prefer it always.
// Equivalent to 'final' in Java or 'const' in JavaScript.

@main def valFields(): Unit =

    // --- Basic val declaration: keyword + name + value ---
    // type is inferred by the compiler (Int here)
    val x = 1
    println(s"x = $x")

    // --- Multiple vals building on each other ---
    // book example: composing names
    val firstName = "Oleh"
    val lastName  = "Bohatyi"
    val fullName  = firstName + " " + lastName
    println(s"fullName = $fullName")   // Oleh Bohatyi

    // --- Immutability: reassignment causes a compile error ---
    // uncommenting the line below will NOT compile:
    // x = 2   // error: Reassignment to val x

    // --- val works for all types ---
    val age       = 30             // Int
    val salary    = 95_000.50      // Double  (underscores for readability)
    val isActive  = true           // Boolean
    val initial   = 'A'            // Char
    val greeting  = "Hello"        // String

    println(s"age=$age, salary=$salary, isActive=$isActive")
    println(s"initial=$initial, greeting=$greeting")

    // --- val with a computed expression ---
    // the right-hand side is evaluated once and the result is stored
    val circle = 2 * 3.14159 * 5   // circumference of radius 5
    println(s"circumference = $circle")

    // --- val inside a function ---
    // each function call creates a fresh, immutable binding
    def greet(name: String): String =
        val message = s"Welcome, $name!"
        message   // returns the val — it never changes inside the function

    println(greet("Scala Dev"))
    println(greet("Interview Candidate"))

    // --- Why immutability matters (interview question) ---
    // 1. Thread-safe by default — no race conditions
    // 2. Easier to reason about — value never changes unexpectedly
    // 3. Enables referential transparency — core FP principle
    // 4. Compiler can optimise more aggressively
    println("\nval benefits: thread-safe, predictable, FP-friendly")

    // --- Additional: val with a block expression ---
    // the block is evaluated once, its last expression becomes the val
    val result = {
        val base = 100
        val tax  = base * 0.19
        base + tax   // 119.0
    }
    println(s"price with tax = $result")
