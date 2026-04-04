package module19newtypes2

// Summary: Type lambdas let you build anonymous type constructors inline.
// They are useful when a higher-kinded API expects a type of one parameter, but your data type has more.
// Scala 3 gives type lambdas a direct and readable syntax with =>>.
// This is especially helpful for Either-like types where one type argument is fixed.
// This file demonstrates a simple Wrap type class used with Option and an Either-based type lambda.

trait Wrap[F[_]]:
    def wrap[A](value: A): F[A]

object Wrap:
    given Wrap[Option] with
        def wrap[A](value: A): Option[A] = Some(value)

    given Wrap[[A] =>> Either[String, A]] with
        def wrap[A](value: A): Either[String, A] = Right(value)

def lift[F[_]: Wrap, A](value: A): F[A] =
    summon[Wrap[F]].wrap(value)

@main def typeLambdas(): Unit =

    // =========================================================
    // Wrap works with regular one-parameter type constructors
    // =========================================================

    import Wrap.given

    println(lift[Option, Int](42))

    // =========================================================
    // A type lambda fixes the left side of Either
    // =========================================================

    type ErrorOr[A] = Either[String, A]
    println(lift[ErrorOr, String]("Scala"))
    println(lift[[A] =>> Either[String, A], Boolean](true))
