package module10functions

// Summary: A Higher-Order Function (HOF) either takes a function as a parameter,
// returns a function, or both. HOFs are the backbone of functional collections API.
// Function types are written as: InputType => OutputType, e.g. Int => Boolean.
// Multiple inputs: (Int, Int) => Int.  No input: () => Int (thunk).
// Understanding HOFs is essential for Scala interviews and for using ZIO, Cats, Akka.

@main def higherOrderFunctions(): Unit =

    // =========================================================
    // Function types — first-class values
    // =========================================================

    // a function stored in a val — it's a value like Int or String
    val double: Int => Int          = n => n * 2
    val isEven: Int => Boolean      = n => n % 2 == 0
    val add:    (Int, Int) => Int   = (a, b) => a + b
    val shout:  String => String    = s => s.toUpperCase + "!"
    val thunk:  () => Int           = () => 42   // no input

    println(double(5))       // 10
    println(isEven(4))       // true
    println(add(3, 7))       // 10
    println(shout("hello"))  // HELLO!
    println(thunk())         // 42

    // =========================================================
    // HOF: taking a function as a parameter
    // =========================================================

    // applyTwice applies f to x, then applies f to the result
    def applyTwice(f: Int => Int, x: Int): Int = f(f(x))

    println(applyTwice(double, 3))         // 12  (3→6→12)
    println(applyTwice(_ + 10, 5))         // 25  (5→15→25)
    println(applyTwice(n => n * n, 2))     // 16  (2→4→16)

    // transform: apply a function to a List, return a new List
    def transform(xs: List[Int], f: Int => Int): List[Int] = xs.map(f)

    val nums = List(1, 2, 3, 4, 5)
    println(transform(nums, double))        // List(2, 4, 6, 8, 10)
    println(transform(nums, n => n * n))    // List(1, 4, 9, 16, 25)

    // =========================================================
    // HOF: returning a function
    // =========================================================

    // multiplier returns a new function Int => Int
    def multiplier(factor: Int): Int => Int = n => n * factor

    val triple = multiplier(3)
    val times5 = multiplier(5)

    println(triple(7))    // 21
    println(times5(7))    // 35
    println(multiplier(10)(4))  // 40 — call immediately

    // adder returns a function that adds n to its argument
    def adder(n: Int): Int => Int = x => x + n

    val add5  = adder(5)
    val add10 = adder(10)
    println(add5(3))    // 8
    println(add10(3))   // 13

    // =========================================================
    // HOF: both takes AND returns a function — function composition
    // =========================================================

    // compose: returns a new function that applies g then f
    def compose[A, B, C](f: B => C, g: A => B): A => C = x => f(g(x))

    val doubleAndAddOne = compose(adder(1), double)
    println(doubleAndAddOne(4))   // (4*2)+1 = 9
    println(doubleAndAddOne(10))  // (10*2)+1 = 21

    // Scala's built-in: andThen (left to right) and compose (right to left)
    val pipeline1 = double.andThen(adder(1))   // double first, then addOne
    val pipeline2 = adder(1).compose(double)   // same: double first, then addOne
    println(pipeline1(5))   // 11
    println(pipeline2(5))   // 11

    // =========================================================
    // Using function values with collection HOFs
    // =========================================================

    val words = List("banana", "apple", "cherry", "date", "fig")

    // store predicate in a val, reuse it
    val isShort: String => Boolean  = _.length <= 4
    val isLong:  String => Boolean  = _.length >  5
    val upperCase: String => String = _.toUpperCase

    println(words.filter(isShort))             // List(date, fig)
    println(words.filter(isLong))              // List(banana, cherry)
    println(words.filter(isShort).map(upperCase)) // List(DATE, FIG)

    // =========================================================
    // Callback pattern — pass a function to be called later
    // =========================================================

    // process: applies a transform, then calls the callback with the result
    def process(data: List[Int], transform: Int => Int, callback: List[Int] => Unit): Unit =
        val result = data.map(transform)
        callback(result)

    process(nums, _ * 3, result => println(s"callback received: $result"))

    // =========================================================
    // Function type aliases — cleaner signatures
    // =========================================================

    type Predicate[A] = A => Boolean
    type Transform[A] = A => A

    def filterAndTransform[A](xs: List[A], p: Predicate[A], t: Transform[A]): List[A] =
        xs.filter(p).map(t)

    val result = filterAndTransform(
        List(1, 2, 3, 4, 5, 6),
        n => n % 2 == 0,    // keep evens
        n => n * 100         // multiply by 100
    )
    println(s"filterAndTransform: $result")   // List(200, 400, 600)

    // =========================================================
    // Additional: building a mini pipeline DSL
    // =========================================================

    case class Pipeline[A](value: A):
        def pipe[B](f: A => B): Pipeline[B] = Pipeline(f(value))
        def result: A = value

    val answer = Pipeline(10)
        .pipe(_ * 2)       // 20
        .pipe(_ + 5)       // 25
        .pipe(n => s"Result: $n")
        .result

    println(answer)   // Result: 25
