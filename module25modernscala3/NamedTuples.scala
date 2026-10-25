package module25modernscala3

// Summary: Named tuples (stable since Scala 3.7) give tuple elements field-like names.
// They are lightweight records: no class definition, but readable access like person.name.
// Names exist only at compile time; at runtime a named tuple is a plain tuple.
// Named patterns can match a subset of fields in any order, also on case classes.
// This file demonstrates construction, access, matching, and NamedTuple.From.

case class City(zip: Int, name: String, population: Int)

type Person = (name: String, age: Int)

// returning several values with self-documenting names instead of (Int, Int)
def minMax(values: List[Int]): (min: Int, max: Int) =
    (min = values.min, max = values.max)

@main def namedTuples(): Unit =

    // =========================================================
    // Construction and field access
    // =========================================================

    val bob: Person = (name = "Bob", age = 33)
    println(s"${bob.name} is ${bob.age}")   // Bob is 33

    // a plain tuple with matching element types is accepted
    val laura: Person = ("Laura", 25)
    println(laura.name)   // Laura

    val range = minMax(List(3, 1, 4, 1, 5, 9))
    println(s"min=${range.min}, max=${range.max}")   // min=1, max=9

    // =========================================================
    // Pattern matching by name — any subset, any order
    // =========================================================

    bob match
        case (age = a, name = n) => println(s"$n/$a")   // Bob/33

    // named patterns also work on case classes
    val london = City(10001, "London", 8_900_000)
    london match
        case c @ City(name = "London") => println(c.population)   // 8900000
        case _                         => println("other city")

    // =========================================================
    // Converting to plain tuples and from case classes
    // =========================================================

    // names are erased: toTuple gives the plain (String, Int) tuple
    val plain: (String, Int) = bob.toTuple
    println(plain)   // (Bob,33)

    // inside type constructors the conversion must be explicit
    val people: List[Person] = List(bob, laura)
    val pairs: List[(String, Int)] = people.map(_.toTuple)
    println(pairs)   // List((Bob,33), (Laura,25))

    // NamedTuple.From derives a named tuple type from a case class
    type CityRow = NamedTuple.From[City]
    val row: CityRow = (zip = 75001, name = "Paris", population = 2_100_000)
    println(s"${row.name}: ${row.population}")   // Paris: 2100000
