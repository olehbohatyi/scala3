package module21safetymodel

// Summary: Smart constructors help make invalid states harder to create.
// Instead of exposing unrestricted constructors, validation happens before a value enters the domain.
// This is one of the most practical safety techniques in Scala codebases.
// It works especially well with case classes, opaque types, and Either-based validation.
// This file demonstrates validated creation of Email and UserName values.

case class Email private (value: String)
object Email:
    def from(value: String): Either[String, Email] =
        val normalized = value.trim.toLowerCase
        if normalized.contains("@") then Right(Email(normalized))
        else Left("Email must contain @")

case class UserName private (value: String)
object UserName:
    def from(value: String): Either[String, UserName] =
        val normalized = value.trim
        if normalized.length >= 3 then Right(UserName(normalized))
        else Left("UserName must have at least 3 characters")

@main def smartConstructors(): Unit =

    // =========================================================
    // Domain objects are created through validated factories
    // =========================================================

    println(Email.from("alice@example.com"))
    println(Email.from("broken-email"))
    println(UserName.from("mentor"))
    println(UserName.from("ab"))
