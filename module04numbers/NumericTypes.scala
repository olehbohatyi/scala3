package module04numbers

// Summary: Scala has eight numeric types arranged by size and precision.
// Int and Double are the defaults — use them unless you have a specific reason.
// BigInt and BigDecimal handle arbitrarily large numbers without overflow.
// Scala inherits Java's numeric primitives but treats them as objects with methods.
// Key interview point: numeric conversions can lose precision — know when!

@main def numericTypes(): Unit =

    // =========================================================
    // The eight numeric types — size, range, literal syntax
    // =========================================================

    // Byte: 8-bit signed integer, range -128 to 127
    val b: Byte = 127
    println(s"Byte max  = $b")

    // Short: 16-bit signed integer, range -32768 to 32767
    val s: Short = 32767
    println(s"Short max = $s")

    // Int: 32-bit signed integer — DEFAULT integer type
    // underscore separator makes large numbers readable (Scala 2.13+)
    val i: Int = 2_147_483_647
    println(s"Int max   = $i")

    // Long: 64-bit signed integer, suffix L is required for literals
    val l: Long = 9_223_372_036_854_775_807L
    println(s"Long max  = $l")

    // Float: 32-bit floating point, suffix f required
    val f: Float = 3.14f
    println(s"Float     = $f")

    // Double: 64-bit floating point — DEFAULT decimal type
    val d: Double = 3.141592653589793
    println(s"Double    = $d")

    // =========================================================
    // Overflow: what happens when you exceed the type range
    // =========================================================

    // Int overflow wraps around silently — no exception!
    val maxInt = Int.MaxValue
    println(s"\nInt.MaxValue      = $maxInt")        // 2147483647
    println(s"MaxValue + 1      = ${maxInt + 1}")    // -2147483648 (wraps!)

    // Long ranges are much larger — overflow is rarer
    println(s"Int.MinValue      = ${Int.MinValue}")
    println(s"Long.MaxValue     = ${Long.MaxValue}")

    // =========================================================
    // BigInt: arbitrary precision integers, no overflow
    // =========================================================

    // use string constructor for very large numbers
    val big1 = BigInt("123456789012345678901234567890")
    val big2 = BigInt("987654321098765432109876543210")
    println(s"\nBigInt sum = ${big1 + big2}")   // exact result

    // factorial of 50 — impossible with Long, trivial with BigInt
    def factorial(n: BigInt): BigInt =
        if n <= 1 then BigInt(1) else n * factorial(n - 1)
    println(s"50! = ${factorial(50)}")

    // =========================================================
    // BigDecimal: arbitrary precision decimals — use for money!
    // =========================================================

    // Double has floating point rounding errors — never use for money
    val doubleSum = 0.1 + 0.2
    println(s"\n0.1 + 0.2 (Double)     = $doubleSum")   // 0.30000000000000004 !!!

    // BigDecimal is exact
    val bdSum = BigDecimal("0.1") + BigDecimal("0.2")
    println(s"0.1 + 0.2 (BigDecimal) = $bdSum")         // 0.3

    // financial calculation example
    val price    = BigDecimal("1999.99")
    val vatRate  = BigDecimal("0.19")
    val vat      = (price * vatRate).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    val total    = price + vat
    println(s"Price=$price  VAT=$vat  Total=$total")

    // =========================================================
    // Type conversions — explicit casting
    // =========================================================

    // widening: smaller -> larger type (safe, no data loss)
    val intVal  = 42
    val toLong   = intVal.toLong
    val toDouble = intVal.toDouble
    println(s"\n42.toLong   = $toLong")    // 42
    println(s"42.toDouble = $toDouble")   // 42.0

    // narrowing: larger -> smaller type (TRUNCATES, not rounds!)
    val pi = 3.99
    println(s"3.99.toInt  = ${pi.toInt}")  // 3  (truncated, NOT 4!)
    println(s"3.99.toFloat= ${pi.toFloat}") // 3.99 (approx)

    // String -> numeric (throws NumberFormatException if invalid)
    val parsed = "123".toInt
    println(s"\"123\".toInt = $parsed")   // 123

    // safe parsing via Option — no exception
    val safe = "abc".toIntOption
    println(s"\"abc\".toIntOption = $safe")   // None

    val good = "42".toIntOption
    println(s"\"42\".toIntOption  = $good")   // Some(42)

    // =========================================================
    // Useful constants on numeric types
    // =========================================================

    println(s"\nInt.MaxValue    = ${Int.MaxValue}")
    println(s"Int.MinValue    = ${Int.MinValue}")
    println(s"Double.MaxValue = ${Double.MaxValue}")
    println(s"Double.MinValue = ${Double.MinValue}")
    println(s"Double.NaN      = ${Double.NaN}")         // Not a Number
    println(s"isNaN           = ${Double.NaN.isNaN}")   // true
