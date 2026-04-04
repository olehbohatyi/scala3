package module16extensionandtypeclasses

// Summary: Extension methods let you add methods to existing types without inheritance or wrapper classes.
// They replace many old implicit-class patterns with clearer and more direct syntax.
// Extension methods are excellent for DSLs, domain-specific helpers, and small ergonomic improvements.
// Generic extensions can also add reusable utilities to many types at once.
// This file demonstrates both concrete and generic extension methods.

extension (value: String)
    def asSlug: String = value.trim.toLowerCase.replace(' ', '-')
    def quoted: String = s"\"$value\""

extension (amount: BigDecimal)
    def eur: String = s"€$amount"

extension [A](values: List[A])
    def secondOption: Option[A] = values.drop(1).headOption
    def pairWithIndex: List[(A, Int)] = values.zipWithIndex

@main def extensionMethods(): Unit =

    // =========================================================
    // String extensions
    // =========================================================

    println("Scala 3 Course".asSlug)
    println("mentor".quoted)

    // =========================================================
    // Numeric formatting extension
    // =========================================================

    println(BigDecimal("19.99").eur)

    // =========================================================
    // Generic list extensions
    // =========================================================

    println(List(10, 20, 30).secondOption)
    println(List("a", "bb", "ccc").pairWithIndex)
