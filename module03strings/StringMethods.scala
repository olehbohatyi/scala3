package module03strings

// Summary: String is an immutable sequence of characters backed by java.lang.String.
// All String methods return a NEW string — the original is never modified.
// Scala inherits the full Java String API plus adds extension methods.
// These are the methods you will see most often in real code and interviews.

@main def stringMethods(): Unit =

    val s = "Hello, Scala 3!"

    // --- Length and character access ---
    println(s"length  = ${s.length}")          // 15
    println(s"charAt  = ${s.charAt(7)}")       // S
    println(s"apply[] = ${s(7)}")              // S  (Scala shorthand for charAt)
    println(s"head    = ${s.head}")            // H  (first character)
    println(s"last    = ${s.last}")            // !  (last character)

    // --- Case conversion ---
    println(s.toUpperCase)     // HELLO, SCALA 3!
    println(s.toLowerCase)     // hello, scala 3!
    println("hello".capitalize) // Hello   (first char uppercased)

    // --- Searching and testing ---
    println(s"contains  = ${s.contains("Scala")}")       // true
    println(s"startsWith= ${s.startsWith("Hello")}")     // true
    println(s"endsWith  = ${s.endsWith("3!")}")          // true
    println(s"indexOf   = ${s.indexOf("Scala")}")        // 7
    println(s"isEmpty   = ${"".isEmpty}")                // true
    println(s"nonEmpty  = ${s.nonEmpty}")                // true

    // --- Trimming and padding ---
    val padded = "   hello   "
    println(s"'${padded.trim}'")           // 'hello'
    println(s"'${padded.strip}'")          // 'hello'  (Unicode-aware, prefer this)
    println(s"'${padded.stripLeading}'")   // 'hello   '
    println(s"'${padded.stripTrailing}'")  // '   hello'

    // --- Extracting substrings ---
    // take: first n chars, drop: skip first n chars
    println(s.take(5))          // Hello
    println(s.drop(7))          // Scala 3!
    println(s.takeRight(2))     // 3!
    println(s.dropRight(2))     // Hello, Scala

    // substring: start index (inclusive), end index (exclusive)
    println(s.substring(7, 12)) // Scala

    // --- Replacing content ---
    // replace is literal text substitution
    println(s.replace("Scala", "Rust"))             // Hello, Rust 3!
    // replaceAll uses regex pattern
    println("a1b2c3".replaceAll("[0-9]", "_"))      // a_b_c_

    // --- Splitting ---
    // split returns an Array[String]
    val csv = "alice,bob,carol"
    val parts = csv.split(",")
    println(parts.mkString(" | "))   // alice | bob | carol
    println(s"parts count = ${parts.length}")   // 3

    // --- Joining / mkString ---
    // mkString on a collection: separator, or prefix+separator+suffix
    val words = List("Scala", "is", "awesome")
    println(words.mkString(" "))            // Scala is awesome
    println(words.mkString("[", ", ", "]")) // [Scala, is, awesome]

    // --- Type conversion ---
    println("42".toInt)        // 42      (throws if not a valid Int)
    println("3.14".toDouble)   // 3.14
    println(99.toString)       // "99"
    println(true.toString)     // "true"

    // --- Additional: repeat and lines (Scala / Java 11+) ---
    println("ab" * 4)               // abababab  (Scala extension)
    println("-" * 20)               // --------------------

    // --- Additional: useful chain example ---
    val raw = "  SCALA IS GREAT  "
    val cleaned = raw.strip.toLowerCase.replace(" ", "-")
    println(cleaned)   // scala-is-great
