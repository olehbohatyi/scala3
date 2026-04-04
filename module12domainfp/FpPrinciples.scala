package module12domainfp

// Summary: Functional programming in Scala starts with a few core habits rather than advanced jargon.
// Prefer immutable values, pure functions, and explicit modeling of errors and states.
// Functions can be passed around like any other value, which enables reusable pipelines.
// Scala supports FP well while still remaining practical for mixed-style applications.
// This file demonstrates these principles in one compact example.

case class Invoice(total: BigDecimal, paid: Boolean)

def markPaid(invoice: Invoice): Invoice = invoice.copy(paid = true)

def renderStatus(invoice: Invoice): String =
    if invoice.paid then s"paid:${invoice.total}" else s"open:${invoice.total}"

@main def fpPrinciples(): Unit =

    // =========================================================
    // Immutable data and pure transformations
    // =========================================================

    val invoice = Invoice(BigDecimal(99.50), paid = false)
    val updated = markPaid(invoice)

    println(invoice)
    println(updated)
    println(renderStatus(updated))
