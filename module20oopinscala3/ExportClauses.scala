package module20oopinscala3

// Summary: export clauses let one type re-expose members of another type.
// This is useful for delegation-heavy designs where a façade should forward selected APIs.
// export reduces boilerplate and keeps the public surface of a wrapper class concise.
// It is especially practical in service layers and small composition-oriented designs.
// This file demonstrates a service that exports repository operations directly.

class UserRepository:
    def find(id: Int): String = s"user-$id"
    def all: List[String] = List("user-1", "user-2", "user-3")

class UserService(repo: UserRepository):
    export repo.find
    export repo.all

    def findUpper(id: Int): String = find(id).toUpperCase

@main def exportClauses(): Unit =

    // =========================================================
    // Exported methods look like native service methods
    // =========================================================

    val service = UserService(UserRepository())

    println(service.find(2))
    println(service.all)
    println(service.findUpper(3))
