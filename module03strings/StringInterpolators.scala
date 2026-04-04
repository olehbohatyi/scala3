package module03strings

// Summary: Scala provides three built-in string interpolators.
// s"..."   — simple embedding: $varName or ${expression}
// f"..."   — printf-style formatting: $var%format (type-checked at compile time)
// raw"..." — no escape processing: \n is literally backslash-n
// Custom interpolators can also be defined (advanced — shown briefly).

@main def stringInterpolators(): Unit =

    // =========================================================
    // s"..." — the most common interpolator
    // =========================================================

    val name = "Alvin"
    val age  = 42

    // embed a variable directly with $name
    println(s"Hello, $name!")           // Hello, Alvin!

    // embed any expression with ${...}
    println(s"In 10 years: ${age + 10}")   // In 10 years: 52
    println(s"Name length: ${name.length}") // Name length: 5
    println(s"Uppercase:   ${name.toUpperCase}")  // Uppercase: ALVIN

    // boolean expression inside interpolation
    val score = 87
    println(s"Passed: ${score >= 60}")  // Passed: true

    // nested string inside interpolation
    val items = List("a", "b", "c")
    println(s"Items: ${items.mkString(", ")}")   // Items: a, b, c

    // =========================================================
    // f"..." — formatted interpolation (compile-time type check)
    // =========================================================

    val salary  = 95_000.5
    val pi      = 3.141592653589793
    val product = "Scala Course"

    // %.2f  — floating point, 2 decimal places
    println(f"Salary:  $salary%.2f")       // Salary:  95000.50

    // %10.2f — right-aligned in a field of width 10
    println(f"Pi:      $pi%10.4f")         // Pi:          3.1416

    // %d — integer formatting
    println(f"Age:     $age%05d")          // Age:     00042  (zero-padded, width 5)

    // %s — string formatting, %-20s = left-aligned in width 20
    println(f"Product: $product%-20s | end")  // Product: Scala Course         | end

    // %e — scientific notation
    println(f"Pi sci:  $pi%e")             // Pi sci:  3.141593e+00

    // practical: a simple table with aligned columns
    println("\n--- Price List ---")
    val prices = List(("Apple", 1.5), ("Banana", 0.75), ("Cherry", 3.0))
    for (item, price) <- prices do
        println(f"  $item%-10s $$${price}%.2f")

    // =========================================================
    // raw"..." — no escape sequences processed
    // =========================================================

    // in a normal string, \n is a newline
    println("normal: line1\nline2")

    // in raw"...", \n is two characters: backslash and n
    println(raw"raw:    line1\nline2")     // prints literally: line1\nline2

    // useful for regex patterns — no need to double-escape backslashes
    val regexNormal = "\\d+"    // need double backslash in normal string
    val regexRaw    = raw"\d+"  // clean, readable
    println(s"regex normal = $regexNormal")
    println(s"regex raw    = $regexRaw")
    // both represent the same regex \d+

    // =========================================================
    // Additional: s"" with multi-expression and method calls
    // =========================================================

    case class Person(first: String, last: String, age: Int)
    val p = Person("Jane", "Doe", 35)

    // access case class fields directly inside interpolation
    println(s"${p.first} ${p.last} is ${p.age} years old")

    // conditional inside interpolation
    val label = s"${p.first}: ${if p.age >= 18 then "adult" else "minor"}"
    println(label)   // Jane: adult

    // =========================================================
    // Additional: escape $ and " inside interpolated strings
    // =========================================================

    // to print a literal $ sign inside s"..."
    println(s"Price: $$${salary}")        // Price: $95000.5
    // to include a quote
    println(s"She said: \"hello\"")       // She said: "hello"
