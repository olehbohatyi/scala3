package module18newtypes1

// Summary: Opaque types become even more useful when creation is controlled through validation.
// This combines zero-overhead domain modeling with smart-constructor style safety.
// The result is stronger than passing raw strings through the system.
// The pattern is especially practical for emails, identifiers, and constrained textual values.
// This file demonstrates an opaque Email type created through a validating API.

object Emails:
    opaque type Email = String

    object Email:
        def from(value: String): Either[String, Email] =
            val normalized = value.trim.toLowerCase
            if normalized.contains("@") then Right(normalized)
            else Left("Email must contain @")

    extension (email: Email)
        def value: String = email
        def domain: String = email.split("@").lastOption.getOrElse("")

import Emails.*

@main def opaqueTypesWithValidation(): Unit =

    // =========================================================
    // Creation is validated before the opaque value enters the domain
    // =========================================================

    val valid = Email.from("Alice@Example.com")
    val invalid = Email.from("broken-email")

    println(valid)
    println(invalid)

    // =========================================================
    // Domain helpers stay available through extensions
    // =========================================================

    val domain = valid.toOption.map(_.domain)
    println(domain)
