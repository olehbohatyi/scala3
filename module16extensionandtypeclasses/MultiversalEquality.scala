package module16extensionandtypeclasses

import scala.language.strictEquality

// Summary: strict equality in Scala 3 helps prevent accidental comparisons between unrelated types.
// The CanEqual type class describes which comparisons are allowed.
// This can catch mistakes at compile time instead of letting weak comparisons slip into production code.
// Scala 3 makes this feature more explicit and safer than unconstrained universal equality.
// This file demonstrates safe equality with domain-specific identifier types.

case class UserId(value: Int) derives CanEqual
case class OrderId(value: Int) derives CanEqual

def safeEqual[A](left: A, right: A)(using CanEqual[A, A]): Boolean =
    left == right

@main def multiversalEquality(): Unit =

    // =========================================================
    // Same-type comparisons are allowed
    // =========================================================

    val user1 = UserId(1)
    val user2 = UserId(2)
    println(user1 == user2)
    println(safeEqual(user1, UserId(1)))
    println(safeEqual("scala", "scala"))

    // =========================================================
    // Different-type comparisons are intentionally rejected
    // =========================================================

    val order = OrderId(1)
    println(order)
    // println(user1 == order)   // does not compile under strict equality
