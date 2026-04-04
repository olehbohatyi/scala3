package module02basics

// Summary: println prints a value followed by a newline; print does not add one.
// Both write to STDOUT (standard output). System.err.println writes to STDERR.
// Strings can be joined with + but s-interpolation is the preferred approach.
// Knowing STDOUT vs STDERR matters when redirecting output in shell scripts.

@main def printingOutput(): Unit =

    // --- println: prints value + newline ---
    // the most common output function in Scala
    println("Hello, world")
    println(42)
    println(3.14)
    println(true)

    // --- print: prints value WITHOUT a trailing newline ---
    // useful when building a line piece by piece
    print("one ")
    print("two ")
    print("three")
    println()   // add the newline manually at the end

    // --- String concatenation with + (works but not idiomatic) ---
    // book example: two strings joined with +
    val firstName = "Oleh"
    val lastName  = "Bohatyi"
    println("Hello, " + firstName + " " + lastName)   // works but verbose

    // --- s-interpolation: preferred approach ---
    // cleaner, safer, no + operators needed
    println(s"Hello, $firstName $lastName")            // same output

    // --- Arbitrary expressions inside interpolation ---
    // wrap expressions in ${...}
    val a = 10
    val b = 3
    println(s"$a + $b = ${a + b}")         // 10 + 3 = 13
    println(s"$a * $b = ${a * b}")         // 10 * 3 = 30
    println(s"is even: ${a % 2 == 0}")     // is even: true

    // --- STDOUT vs STDERR ---
    // println goes to STDOUT — normal program output
    println("This goes to STDOUT")

    // System.err.println goes to STDERR — errors and diagnostics
    // in the shell: ./prog > out.txt  =>  STDERR still shows in terminal
    System.err.println("This goes to STDERR (may appear in a different color)")

    // --- Additional: printing different types directly ---
    // println calls .toString on any value automatically
    val numbers = List(1, 2, 3)
    val pair    = (42, "hello")
    println(numbers)   // List(1, 2, 3)
    println(pair)      // (42,hello)

    // --- Additional: multiline output with \n ---
    println("line 1\nline 2\nline 3")
