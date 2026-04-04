package module12domainfp

// Summary: match is an expression in Scala — it returns a value.
// Pattern matching works with literals, types, tuples, lists, case classes, and more.
// Guards add extra conditions with if after a pattern.
// Exhaustive matching is important for safety, especially with enums and sealed traits.
// Pattern matching is one of Scala's strongest tools for readable domain logic.

@main def patternMatching(): Unit =

    // =========================================================
    // Basic literal matching
    // =========================================================

    def describe(n: Int): String = n match
        case 0 => "zero"
        case 1 => "one"
        case 2 => "two"
        case _ => "many"

    println(describe(0))
    println(describe(2))
    println(describe(9))

    // =========================================================
    // match as an expression
    // =========================================================

    val score = 87
    val grade = score match
        case n if n >= 90 => "A"
        case n if n >= 80 => "B"
        case n if n >= 70 => "C"
        case _            => "F"
    println(grade)

    // =========================================================
    // Tuple matching
    // =========================================================

    def quadrant(point: (Int, Int)): String = point match
        case (0, 0)             => "origin"
        case (x, 0)             => s"x-axis at $x"
        case (0, y)             => s"y-axis at $y"
        case (x, y) if x > 0 && y > 0 => "quadrant I"
        case (x, y) if x < 0 && y > 0 => "quadrant II"
        case (x, y) if x < 0 && y < 0 => "quadrant III"
        case _                  => "quadrant IV"

    println(quadrant((0, 0)))
    println(quadrant((5, 0)))
    println(quadrant((2, 7)))

    // =========================================================
    // List matching
    // =========================================================

    def classify(xs: List[Int]): String = xs match
        case Nil            => "empty"
        case head :: Nil    => s"one element: $head"
        case a :: b :: Nil  => s"two elements: $a and $b"
        case head :: tail   => s"many, head=$head, tailSize=${tail.size}"

    println(classify(Nil))
    println(classify(List(42)))
    println(classify(List(1, 2)))
    println(classify(List(1, 2, 3, 4)))

    // =========================================================
    // Case class matching
    // =========================================================

    case class User(name: String, age: Int)

    def userLabel(user: User): String = user match
        case User(name, age) if age >= 18 => s"adult:$name"
        case User(name, _)                => s"minor:$name"

    println(userLabel(User("Alice", 30)))
    println(userLabel(User("Bob", 14)))

    // =========================================================
    // Option matching
    // =========================================================

    def renderEmail(email: Option[String]): String = email match
        case Some(value) => s"email=$value"
        case None        => "email missing"

    println(renderEmail(Some("hello@example.com")))
    println(renderEmail(None))

    // =========================================================
    // Additional: type matching
    // =========================================================

    def kindOf(value: Any): String = value match
        case i: Int    => s"int:$i"
        case s: String => s"string:${s.toUpperCase}"
        case b: Boolean => s"bool:$b"
        case _         => "unknown"

    println(kindOf(42))
    println(kindOf("scala"))
    println(kindOf(true))
