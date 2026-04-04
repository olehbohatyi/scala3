package module18newtypes1

// Summary: Union types let one value be one of several possible types.
// Intersection types describe values that must satisfy several types at the same time.
// Together, they make APIs more expressive without forcing extra wrapper hierarchies.
// Union types are good for small heterogeneous results, while intersections fit capability composition.
// This file demonstrates both features with clear, interview-friendly examples.

trait Camera:
    def shoot(): String

trait Phone:
    def call(number: String): String

case class SmartPhone(model: String) extends Camera, Phone:
    def shoot(): String = s"$model took a photo"
    def call(number: String): String = s"$model called $number"

def renderValue(value: Int | String | Boolean): String = value match
    case i: Int     => s"int:$i"
    case s: String  => s"text:$s"
    case b: Boolean => s"bool:$b"

def useBoth[A <: Camera & Phone](device: A): String =
    device.shoot() + " | " + device.call("123-456")

@main def unionAndIntersectionTypes(): Unit =

    // =========================================================
    // Union type examples
    // =========================================================

    println(renderValue(42))
    println(renderValue("Scala 3"))
    println(renderValue(true))

    // =========================================================
    // Intersection type example
    // =========================================================

    val device = SmartPhone("Pixel")
    println(useBoth(device))
