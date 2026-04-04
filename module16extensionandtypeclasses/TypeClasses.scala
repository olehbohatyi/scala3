package module16extensionandtypeclasses

// Summary: Type classes model behavior separately from data types.
// In Scala 3, type classes are commonly implemented with traits plus givens.
// This pattern is ideal when you want generic behavior without forcing inheritance.
// Encoders, orderings, validators, and serializers are common type-class examples.
// This file demonstrates a small JSON encoder type class with generic list support.

trait JsonEncoder[A]:
    def encode(value: A): String

object JsonEncoder:
    def apply[A](using encoder: JsonEncoder[A]): JsonEncoder[A] = encoder

    given JsonEncoder[String] with
        def encode(value: String): String = s"\"$value\""

    given JsonEncoder[Int] with
        def encode(value: Int): String = value.toString

    given [A](using encoder: JsonEncoder[A]): JsonEncoder[List[A]] with
        def encode(values: List[A]): String = values.map(encoder.encode).mkString("[", ", ", "]")

case class User(name: String, level: Int)

object User:
    given JsonEncoder[User] with
        def encode(user: User): String =
            val encodedName = JsonEncoder[String].encode(user.name)
            s"{" + s"\"name\":$encodedName,\"level\":${user.level}" + s"}"

def toJson[A: JsonEncoder](value: A): String =
    JsonEncoder[A].encode(value)

@main def typeClasses(): Unit =

    // =========================================================
    // Import given encoders
    // =========================================================

    import JsonEncoder.given
    import User.given

    println(toJson("Scala"))
    println(toJson(42))
    println(toJson(List(1, 2, 3)))
    println(toJson(User("Alice", 5)))
