package module24capstone

// Summary: This capstone focuses on domain modeling with opaque types, enums, extensions, and ADTs.
// The program models a small order flow with stronger types than raw strings and integers.
// Opaque types protect identifiers, while enums and sealed traits keep workflow states explicit.
// Extension methods add clear domain operations without inheritance boilerplate.
// This file shows how Scala 3 can make compact domain models safer and more expressive.

object Domain:
    opaque type OrderId = Int

    object OrderId:
        def apply(value: Int): OrderId = value

    extension (id: OrderId)
        def value: Int = id
        def show: String = s"order-$id"

    enum OrderStatus:
        case Draft, Paid, Cancelled

    sealed trait PaymentDecision
    object PaymentDecision:
        case object Approved extends PaymentDecision
        case object Rejected extends PaymentDecision

    case class Order(id: OrderId, amount: BigDecimal, status: OrderStatus)

import Domain.*

def decide(amount: BigDecimal): PaymentDecision =
    if amount <= 1000 then PaymentDecision.Approved
    else PaymentDecision.Rejected

@main def typedOrderSystem(): Unit =

    // =========================================================
    // Opaque identifiers and workflow modeling
    // =========================================================

    val order = Order(OrderId(101), BigDecimal(99.50), OrderStatus.Draft)
    println(order.id.show)
    println(order.status)
    println(decide(order.amount))
