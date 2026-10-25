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

case class UserLevel(name: String, level: Int)

object UserLevel:
    given JsonEncoder[UserLevel] with
        def encode(userLevel: UserLevel): String =
            val encodedName = JsonEncoder[String].encode(userLevel.name)
            s"{" + s"\"name\":$encodedName,\"level\":${userLevel.level}" + s"}"

def toJson[A: JsonEncoder](value: A): String =
    JsonEncoder[A].encode(value)

@main def typeClasses(): Unit =

    // =========================================================
    // Import given encoders
    // =========================================================

    // UserLevel's encoder lives in its companion, so it needs no import
    import JsonEncoder.given

    println(toJson("Scala"))
    println(toJson(42))
    println(toJson(List(1, 2, 3)))
    println(toJson(UserLevel("Alice", 5)))
