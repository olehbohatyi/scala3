package module16extensionandtypeclasses

import scala.Conversion
import scala.language.implicitConversions

// Summary: Scala 3 still supports implicit conversions, but they should be used carefully.
// The preferred tools are extension methods, explicit constructors, and type classes.
// When implicit conversions are used, they should be small, local, and unsurprising.
// Controlled DSL-style conversions are one of the few reasonable use cases.
// This file demonstrates two simple conversions and explains their effect through output.

case class Kilometers(value: Double):
    def toMiles: Double = value * 0.621371

case class User(value: String)

given Conversion[Double, Kilometers] with
    def apply(value: Double): Kilometers = Kilometers(value)

given Conversion[String, User] with
    def apply(value: String): User = User(value.trim)

def travel(distance: Kilometers): String =
    f"distance=${distance.value}%.1f km (${distance.toMiles}%.1f mi)"

def lookupUser(id: User): String = s"user:${id.value}"

@main def implicitConversions(): Unit =

    // =========================================================
    // Double is converted to Kilometers where needed
    // =========================================================

    println(travel(12.5))

    // =========================================================
    // String is converted to UserId where needed
    // =========================================================

    println(lookupUser("  user-42  "))

    // =========================================================
    // Prefer explicit code unless the conversion is truly natural
    // =========================================================

    val explicit = travel(Kilometers(5.0))
    println(explicit)
