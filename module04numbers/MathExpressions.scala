package module04numbers

import scala.math.*

// Summary: Scala arithmetic operators work as expected with one key trap:
// Int / Int is INTEGER division — the decimal part is discarded silently.
// The scala.math package mirrors Java's Math and is imported with scala.math.*.
// Operator precedence follows standard math rules (PEMDAS/BODMAS).
// Compound assignment operators +=, -=, *=, /= work on var only.

@main def mathExpressions(): Unit =

    // =========================================================
    // Basic arithmetic operators
    // =========================================================

    val a = 10
    val b = 3

    // standard four operations
    println(s"$a + $b = ${a + b}")   // 13
    println(s"$a - $b = ${a - b}")   // 7
    println(s"$a * $b = ${a * b}")   // 30

    // =========================================================
    // Integer division vs floating-point division — KNOW THIS!
    // =========================================================

    // Int / Int = Int: the fractional part is DROPPED without rounding
    println(s"\n10 / 3  (Int)    = ${10 / 3}")         // 3  (not 3.33!)
    println(s"10 / 4  (Int)    = ${10 / 4}")           // 2  (not 2.5!)
    println(s"-10 / 3 (Int)    = ${-10 / 3}")          // -3 (truncates toward zero)

    // promote to Double for real division
    println(s"10.0 / 3 (Double) = ${10.0 / 3}")        // 3.3333333333333335
    println(s"10 / 3.0 (Double) = ${10 / 3.0}")        // 3.3333333333333335

    // explicit conversion before dividing
    println(s"a.toDouble / b   = ${a.toDouble / b}")   // 3.3333333333333335

    // =========================================================
    // Modulo (remainder) operator %
    // =========================================================

    // % returns the remainder after integer division
    println(s"\n10 % 3  = ${10 % 3}")    // 1   (10 = 3*3 + 1)
    println(s"10 % 5  = ${10 % 5}")    // 0   (divides evenly)
    println(s"10 % 7  = ${10 % 7}")    // 3
    println(s"-10 % 3 = ${-10 % 3}")   // -1  (sign follows dividend in Scala/JVM)

    // classic use: even/odd check
    val n = 42
    println(s"$n is ${if n % 2 == 0 then "even" else "odd"}")  // even

    // classic use: wrap-around index (e.g., circular buffer)
    val size = 5
    for i <- 0 to 8 do
        print(s"${i % size} ")   // 0 1 2 3 4 0 1 2 3
    println()

    // =========================================================
    // Operator precedence (standard math rules apply)
    // =========================================================

    // * and / before + and -
    println(s"\n2 + 3 * 4     = ${2 + 3 * 4}")         // 14  (not 20)
    println(s"(2 + 3) * 4   = ${(2 + 3) * 4}")        // 20
    println(s"10 - 2 + 3    = ${10 - 2 + 3}")          // 11  (left to right)
    println(s"10 - (2 + 3)  = ${10 - (2 + 3)}")        // 5

    // =========================================================
    // Compound assignment operators (var only!)
    // =========================================================

    var x = 100
    x += 10;  println(s"after +=10 : x=$x")   // 110
    x -= 5;   println(s"after -=5  : x=$x")   // 105
    x *= 2;   println(s"after *=2  : x=$x")   // 210
    x /= 3;   println(s"after /=3  : x=$x")   // 70  (Int division!)
    x %= 11;  println(s"after %=11 : x=$x")   // 4

    // =========================================================
    // scala.math — imported at the top of this file
    // =========================================================

    // power: no ** operator in Scala, use math.pow
    println(s"\npow(2, 10)   = ${pow(2, 10)}")    // 1024.0  (returns Double)
    println(s"pow(3, 3)    = ${pow(3, 3)}")    // 27.0

    // square root
    println(s"sqrt(144)    = ${sqrt(144)}")    // 12.0
    println(s"sqrt(2)      = ${sqrt(2)}")      // 1.4142135623730951

    // absolute value
    println(s"abs(-42)     = ${abs(-42)}")     // 42
    println(s"abs(-3.14)   = ${abs(-3.14)}")   // 3.14

    // rounding
    println(s"ceil(3.2)    = ${ceil(3.2)}")    // 4.0  (round up)
    println(s"ceil(-3.2)   = ${ceil(-3.2)}")   // -3.0 (round up toward zero)
    println(s"floor(3.9)   = ${floor(3.9)}")   // 3.0  (round down)
    println(s"floor(-3.9)  = ${floor(-3.9)}")  // -4.0 (round down away from zero)
    println(s"round(3.5)   = ${round(3.5)}")   // 4    (returns Long)
    println(s"round(3.4)   = ${round(3.4)}")   // 3

    // min and max
    println(s"min(3, 7)    = ${min(3, 7)}")    // 3
    println(s"max(3, 7)    = ${max(3, 7)}")    // 7

    // logarithms and trigonometry
    println(s"log(E)       = ${log(E)}")       // 1.0  (natural log of e)
    println(s"log10(1000)  = ${log10(1000)}")  // 3.0
    println(s"sin(Pi/2)    = ${sin(Pi / 2)}")  // 1.0
    println(s"cos(Pi)      = ${cos(Pi)}")      // -1.0

    // useful constants
    println(s"\nPi = $Pi")                    // 3.141592653589793
    println(s"E  = $E")                       // 2.718281828459045

    // =========================================================
    // Additional: practical examples
    // =========================================================

    // circle area using Pi constant
    def circleArea(r: Double): Double = Pi * pow(r, 2)
    println(s"\ncircle area r=5  = ${circleArea(5)}")    // 78.53...

    // hypotenuse using Pythagorean theorem
    def hypotenuse(a: Double, b: Double): Double = sqrt(pow(a, 2) + pow(b, 2))
    println(s"hypotenuse(3,4) = ${hypotenuse(3, 4)}")   // 5.0

    // percentage calculation
    def percent(part: Double, whole: Double): Double =
        (part / whole * 100).round.toDouble
    println(s"75 of 200 = ${percent(75, 200)}%%")        // 38.0%
