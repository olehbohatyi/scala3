package module07collections

// Summary: foreach applies a function to each element FOR SIDE EFFECTS — returns Unit.
// Anonymous functions (lambdas) are inline functions without a name.
// Scala provides progressively shorter lambda syntaxes — know all three forms.
// The underscore shorthand `_` is the most concise but only works for simple expressions.
// Higher-Order Functions (HOFs) like foreach, map, filter take functions as arguments.

@main def foreachAndLambdas(): Unit =

    val nums   = List(1, 2, 3, 4, 5)
    val fruits = List("apple", "banana", "cherry")

    // =========================================================
    // foreach — apply a function to every element, returns Unit
    // =========================================================

    // full lambda syntax: parameter => body
    nums.foreach(n => println(s"  n = $n"))

    // with explicit type annotation on parameter
    nums.foreach((n: Int) => println(n * 2))

    // =========================================================
    // Three lambda syntaxes — know all three for interviews
    // =========================================================

    // 1. Full form: explicit parameter name and body
    val doubled1 = nums.map(n => n * 2)
    println(s"full:      $doubled1")   // List(2, 4, 6, 8, 10)

    // 2. Block form: useful when the body has multiple lines
    val doubled2 = nums.map(n =>
        val result = n * 2
        result
    )
    println(s"block:     $doubled2")

    // 3. Underscore shorthand: _ represents the parameter
    // use only when each parameter appears exactly once
    val doubled3 = nums.map(_ * 2)
    println(s"shorthand: $doubled3")

    // =========================================================
    // Passing a method reference instead of a lambda
    // =========================================================

    // println is a method — pass it directly as a function reference
    nums.foreach(println)                    // prints each element
    fruits.foreach(s => println(s.length))  // lambda wrapping a method call

    // method reference with partially applied function
    fruits.foreach(println)   // same as: fruits.foreach(s => println(s))

    // =========================================================
    // Anonymous functions stored in vals
    // =========================================================

    // a function value — stored in a val, called like a regular function
    val double: Int => Int    = n => n * 2
    val isEven: Int => Boolean = _ % 2 == 0
    val shout:  String => String = _.toUpperCase + "!"

    println(double(7))      // 14
    println(isEven(4))      // true
    println(shout("hello")) // HELLO!

    // passing stored function values to HOFs
    println(nums.map(double))         // List(2, 4, 6, 8, 10)
    println(nums.filter(isEven))      // List(2, 4)
    println(fruits.map(shout))        // List(APPLE!, BANANA!, CHERRY!)

    // =========================================================
    // Multi-parameter lambda — use (a, b) syntax
    // =========================================================

    // reduce takes a 2-parameter lambda: (accumulator, element)
    val sum     = nums.reduce((a, b) => a + b)
    val product = nums.reduce(_ * _)   // shorthand: both _ appear once
    println(s"sum     = $sum")         // 15
    println(s"product = $product")     // 120

    // sortWith: sort by custom comparator lambda
    val words = List("banana", "fig", "apple", "cherry", "date")
    val sorted = words.sortWith((a, b) => a.length < b.length)
    println(s"by length = $sorted")    // fig, date, apple, banana, cherry
    val sortedShort = words.sortWith(_.length < _.length)   // same, shorthand
    println(s"same:      $sortedShort")

    // =========================================================
    // foreach vs map — CRITICAL difference
    // =========================================================

    // foreach:  returns Unit — USE for side effects (print, write, update)
    // map:      returns a new collection — USE for transformation

    // side effect only — foreach is correct
    nums.foreach(n => print(s"$n "))
    println()

    // transformation — map is correct (foreach would return Unit, not a list)
    val transformed = nums.map(n => n * n)
    println(s"transformed = $transformed")   // List(1, 4, 9, 16, 25)

    // =========================================================
    // Additional: lambda with tuple destructuring
    // =========================================================

    val pairs = List((1, "one"), (2, "two"), (3, "three"))

    // destructure the tuple inside the lambda
    pairs.foreach((num, word) => println(s"  $num -> $word"))

    // map over pairs
    val lengths = pairs.map((_, word) => word.length)
    println(s"lengths = $lengths")   // List(3, 3, 5)
