package module06tuples

// Summary: A Tuple groups a fixed number of values of DIFFERENT types into one value.
// Tuples are immutable and their size is fixed at compile time; Scala 3 dropped the 22-element limit.
// Elements are accessed by zero-based index with t(0), t(1), etc. (Scala 3 syntax).
// Tuples shine for returning multiple values from a function without defining a class.
// Key difference from List: List is homogeneous (all same type), Tuple is heterogeneous.

@main def tuples(): Unit =

    // =========================================================
    // Creating tuples — parentheses with comma-separated values
    // =========================================================

    // Tuple2: the most common — used everywhere for key-value pairs
    val pair = ("Alice", 30)
    println(s"pair = $pair")   // (Alice,30)

    // Tuple3
    val triple = (1, "hello", true)
    println(s"triple = $triple")   // (1,hello,true)

    // Tuple4 — any types, any combination
    val quad = ("Scala", 3, 3.14, false)
    println(s"quad = $quad")

    // explicit type annotation (rarely needed — inference works)
    val typed: (String, Int) = ("Bob", 25)
    println(s"typed = $typed")

    // =========================================================
    // Accessing elements — zero-based index (Scala 3)
    // =========================================================

    val person = ("Charlie", 42, "Berlin")

    // Scala 3: use apply syntax t(n) — zero-based
    println(s"name   = ${person(0)}")   // Charlie
    println(s"age    = ${person(1)}")   // 42
    println(s"city   = ${person(2)}")   // Berlin

    // Scala 2 legacy syntax still works: _1, _2, _3 — one-based!
    // person._1  ->  "Charlie"
    // person._2  ->  42
    // Prefer the (0) syntax in Scala 3 code

    // =========================================================
    // Destructuring — unpack a tuple into named vals
    // =========================================================

    // assign each element to its own val in one line
    val (name, age, city) = person
    println(s"$name, $age, $city")   // Charlie, 42, Berlin

    // ignore elements you don't need with _
    val (firstName, _, hometown) = person
    println(s"$firstName from $hometown")   // Charlie from Berlin

    // =========================================================
    // Tuples as function return values — return multiple results
    // =========================================================

    // no need to define a class just to return two values
    def minMax(list: List[Int]): (Int, Int) =
        (list.min, list.max)

    val numbers = List(3, 1, 4, 1, 5, 9, 2, 6)
    val (lo, hi) = minMax(numbers)
    println(s"min=$lo, max=$hi")   // min=1, max=9

    // statistics: return mean and standard deviation
    def stats(xs: List[Double]): (Double, Double) =
        val mean = xs.sum / xs.length
        val variance = xs.map(x => math.pow(x - mean, 2)).sum / xs.length
        (mean, math.sqrt(variance))

    val (mean, stdDev) = stats(List(2.0, 4.0, 4.0, 4.0, 5.0, 5.0, 7.0, 9.0))
    println(f"mean=$mean%.2f, stdDev=$stdDev%.2f")   // mean=5.00, stdDev=2.00

    // =========================================================
    // Tuples in collections — List of tuples
    // =========================================================

    val people = List(("Alice", 30), ("Bob", 25), ("Carol", 35))

    // iterate and destructure in the for generator
    for (n, a) <- people do
        println(s"  $n is $a years old")

    // sort a List of tuples — sorts by first element, then second
    val sorted = people.sortBy(_._1)   // sort by name (first element)
    println(s"sorted: $sorted")

    // sort by age (second element)
    val byAge = people.sortBy(_._2)
    println(s"by age: $byAge")

    // =========================================================
    // Tuple2 special methods
    // =========================================================

    val coord = (3, 7)

    // swap: flips the two elements
    println(s"coord       = $coord")         // (3,7)
    println(s"coord.swap  = ${coord.swap}")  // (7,3)

    // =========================================================
    // zip — creates a List of Tuple2 from two Lists
    // =========================================================

    val names  = List("Alice", "Bob", "Carol")
    val scores = List(95, 87, 91)

    // zip pairs elements at the same index
    val combined = names.zip(scores)
    println(s"zipped: $combined")   // List((Alice,95),(Bob,87),(Carol,91))

    // unzip splits a List of pairs back into two Lists
    val (ns, ss) = combined.unzip
    println(s"names:  $ns")   // List(Alice, Bob, Carol)
    println(s"scores: $ss")   // List(95, 87, 91)

    // zipWithIndex — pairs each element with its position
    for (item, idx) <- names.zipWithIndex do
        println(s"  [$idx] $item")

    // =========================================================
    // Additional: tuple as a Map entry
    // =========================================================

    // -> is syntactic sugar for creating a Tuple2
    val entry1: (String, Int) = "age" -> 30
    val entry2: (String, Int) = ("score", 100)   // identical to above
    println(s"entry1 = $entry1")   // (age,30)
    println(s"entry2 = $entry2")   // (score,100)

    // building a Map from a List of tuples
    val mapFromTuples = people.toMap
    println(s"map: $mapFromTuples")   // Map(Alice -> 30, Bob -> 25, Carol -> 35)

    // since Scala 3.7 elements can be named: (name = "Alice", age = 30)
    // see module25modernscala3/NamedTuples.scala
