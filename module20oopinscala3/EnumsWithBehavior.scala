package module20oopinscala3

// Summary: Scala 3 enums can hold parameters and methods, making them stronger domain types.
// They are useful for finite state models that also need small pieces of behavior.
// Rich enums often replace older sealed-trait hierarchies when the domain is compact.
// This keeps related states and logic together in one readable definition.
// This file demonstrates an enum with fields, methods, and derived domain behavior.

enum PaymentStatus(val isFinal: Boolean):
    case Pending extends PaymentStatus(false)
    case Confirmed extends PaymentStatus(true)
    case Failed extends PaymentStatus(true)

    def label: String = productPrefix.toLowerCase

    def message: String =
        if isFinal then s"$label is final"
        else s"$label can still change"

@main def enumsWithBehavior(): Unit =

    // =========================================================
    // Enums can expose both data and methods
    // =========================================================

    val statuses = PaymentStatus.values.toList
    println(statuses.map(_.label))
    println(statuses.map(_.message))
