package module03strings

// Summary: Triple-quoted strings """...""" span multiple lines without escaping.
// Backslashes and quotes inside are treated as literal characters.
// stripMargin removes leading whitespace up to a | marker on each line.
// stripLeading / trim clean up the first and last newlines.
// Multiline strings are ideal for SQL, JSON, HTML, regex, and test fixtures.

@main def multilineStrings(): Unit =

    // =========================================================
    // Basic triple-quoted string
    // =========================================================

    // everything between """ is taken literally including newlines
    val poem = """Roses are red,
Violets are blue,
Scala is great,
And so are you."""

    println(poem)
    println(s"Lines: ${poem.linesIterator.size}")   // 4

    // =========================================================
    // stripMargin: the idiomatic way to indent multiline strings
    // =========================================================

    // | marks the true left edge of each line
    // stripMargin removes everything to the left of | including |
    val sql = """
        |SELECT id, name, salary
        |FROM   employees
        |WHERE  salary > 50000
        |ORDER  BY salary DESC
        """.stripMargin

    println("--- SQL ---")
    println(sql)

    // =========================================================
    // Combining triple-quoted string with s interpolation
    // =========================================================

    val table  = "products"
    val column = "price"
    val limit  = 100

    val dynamicSql =
        s"""
        |SELECT *
        |FROM   $table
        |WHERE  $column > $limit
        |LIMIT  10
        """.stripMargin

    println("--- Dynamic SQL ---")
    println(dynamicSql)

    // =========================================================
    // JSON / config templates (common in tests and scripts)
    // =========================================================

    val host    = "localhost"
    val port    = 5432
    val dbName  = "myapp"

    val config =
        s"""
        |{
        |  "host": "$host",
        |  "port": $port,
        |  "database": "$dbName"
        |}
        """.stripMargin

    println("--- Config JSON ---")
    println(config)

    // =========================================================
    // Regex patterns: raw + triple-quotes = no escaping at all
    // =========================================================

    // in a normal string: "\\d{3}-\\d{4}" — lots of backslashes
    // triple-quoted raw: clean and readable
    val phoneRegex = raw"\d{3}-\d{4}"
    println(s"Phone regex: $phoneRegex")

    val dateRegex = """\d{4}-\d{2}-\d{2}"""   // ISO date pattern
    println(s"Date regex:  $dateRegex")

    // =========================================================
    // Controlling leading/trailing whitespace
    // =========================================================

    // .trim removes leading and trailing whitespace including newlines
    val noisy = """
        hello
        world
        """.stripMargin.trim

    println(s"trimmed: '$noisy'")

    // .stripMargin uses | by default; you can pass a custom char
    val customMargin = """
        !line one
        !line two
        !line three
        """.stripMargin('!')

    println("--- Custom margin char --- ")
    println(customMargin)

    // =========================================================
    // Additional: multiline string in a method
    // =========================================================

    def htmlTemplate(title: String, body: String): String =
        s"""
        |<!DOCTYPE html>
        |<html>
        |  <head><title>$title</title></head>
        |  <body>
        |    <p>$body</p>
        |  </body>
        |</html>
        """.stripMargin

    println("--- HTML Template ---")
    println(htmlTemplate("Scala 3", "Hello from multiline strings!"))
