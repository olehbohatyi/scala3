package module19newtypes2

// Summary: Dependent function types let a function's result type depend on its input value.
// They build on path-dependent types and can express precise relationships between arguments and results.
// This is an advanced Scala 3 feature, but it becomes clearer with small examples.
// The syntax looks unusual at first, yet it encodes useful type information directly in the function type.
// This file demonstrates extracting values from entries whose payload types differ by instance.

trait Entry:
    type Value
    def name: String
    def value: Value

case class IntEntry(name: String, value: Int) extends Entry:
    type Value = Int

case class TextEntry(name: String, value: String) extends Entry:
    type Value = String

val extractValue: (entry: Entry) => entry.Value =
    (entry: Entry) => entry.value

@main def dependentFunctionTypes(): Unit =

    // =========================================================
    // Result type depends on the specific entry instance
    // =========================================================

    val age = IntEntry("age", 42)
    val language = TextEntry("language", "Scala")

    val ageValue: age.Value = extractValue(age)
    val languageValue: language.Value = extractValue(language)

    println(ageValue + 1)
    println(languageValue.toUpperCase)
