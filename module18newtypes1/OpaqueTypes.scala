package module18newtypes1

// Summary: Opaque types create a new domain type without runtime overhead.
// Outside the defining scope, the opaque type is distinct from its underlying representation.
// This helps model domain concepts like UserId, Email, or Money more safely than raw primitives.
// Opaque types are a major Scala 3 feature for precise domain modeling.
// This file demonstrates opaque identifiers with companion methods and extensions.

object UserIds:
    opaque type UserId = Int

    object UserId:
        def apply(value: Int): UserId = value

    extension (id: UserId)
        def value: Int = id
        def show: String = s"user-$id"
        def next: UserId = id + 1

import UserIds.*

@main def opaqueTypes(): Unit =

    // =========================================================
    // Opaque type behaves like a distinct domain type
    // =========================================================

    val id = UserId(100)
    println(id.show)
    println(id.value)

    // =========================================================
    // Domain-safe operations can be exposed through extensions
    // =========================================================

    val nextId = id.next
    println(nextId.show)

    // =========================================================
    // Underlying representation stays hidden behind the API
    // =========================================================

    val ids = List(id, nextId).map(_.show)
    println(ids)
