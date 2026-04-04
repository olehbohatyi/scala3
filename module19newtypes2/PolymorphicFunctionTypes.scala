package module19newtypes2

// Summary: Polymorphic function types let function values themselves be generic.
// Instead of only methods being generic, a val can also carry type parameters.
// This is useful for reusable generic callbacks and higher-kinded programming patterns.
// The syntax starts with [A] =>, followed by the function body for each chosen type.
// This file demonstrates identity and duplication as first-class polymorphic functions.

val identityPoly: [A] => A => A =
    [A] => (value: A) => value

val duplicatePoly: [A] => A => (A, A) =
    [A] => (value: A) => (value, value)

@main def polymorphicFunctionTypes(): Unit =

    // =========================================================
    // Generic function values can be invoked with explicit type arguments
    // =========================================================

    println(identityPoly[Int](42))
    println(identityPoly[String]("Scala 3"))
    println(duplicatePoly[Boolean](true))
