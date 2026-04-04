# Interview Index

A topic-based index for fast interview preparation.

## OOP

- `module11domainoop/ClassesAndConstructors.scala` — class design, constructor parameters, encapsulation
- `module11domainoop/ObjectsAndCompanions.scala` — singleton objects, companions, smart constructors
- `module11domainoop/TraitsAndMixins.scala` — traits, mixins, composition over inheritance
- `module11domainoop/InheritanceAndOverrides.scala` — abstract classes, override, polymorphism, `final`, `sealed`
- `module11domainoop/EnumsBasics.scala` — Scala 3 enums as domain state models
- `module13projects/*.scala` — applying OOP services to real workflows

## FP

- `module05constructs/IfElseAndEOP.scala` — expression-oriented programming
- `module05constructs/ForExpressions.scala` — `for/yield`
- `module07collections/MapAndFilter.scala` — collection pipelines
- `module07collections/SequenceMethods.scala` — `flatMap`, `foldLeft`, `reduce`
- `module10functions/PureFunctions.scala` — purity and referential transparency
- `module10functions/HigherOrderFunctions.scala` — HOFs and function values
- `module10functions/Closures.scala` — closures and captured environments
- `module10functions/Currying.scala` — currying and DSL-style APIs
- `module12domainfp/SealedTraitsAndAdts.scala` — algebraic data types

## Collections

- `module06tuples/Tuples.scala` — tuples, zip, unzip
- `module07collections/ListClass.scala` — immutable `List`
- `module07collections/OtherSequences.scala` — `Vector`, `LazyList`, `ArraySeq`
- `module07collections/MapAndFilter.scala` — transformations
- `module07collections/SequenceMethods.scala` — aggregation and flattening
- `module07collections/Ranges.scala` — ranges and tabulation
- `module08arraybuffer/*.scala` — mutable sequence structures
- `module09mapset/*.scala` — `Map` and `Set`

## Errors

- `module05constructs/TryCatchFinally.scala` — exception-oriented baseline
- `module04numbers/NumericTypes.scala` — safe parsing with `toIntOption`
- `module12domainfp/OptionBasics.scala` — absence without `null`
- `module12domainfp/EitherBasics.scala` — explicit business errors
- `module12domainfp/TryBasics.scala` — exception capture as values
- `module13projects/*.scala` — domain validation with `Either`

## Domain Modeling

- `module11domainoop/EnumsBasics.scala` — finite states
- `module12domainfp/CaseClasses.scala` — immutable records and value objects
- `module12domainfp/PatternMatching.scala` — branching on data shape
- `module12domainfp/SealedTraitsAndAdts.scala` — closed-domain modeling
- `module13projects/ExpenseTrackerApp.scala` — finance-like workflow
- `module13projects/InterviewPipelineApp.scala` — hiring pipeline
- `module13projects/LibraryRentalApp.scala` — inventory + loan workflow
- `module13projects/SupportTicketApp.scala` — ticket lifecycle and metrics

## Interview Questions to Rehearse

### OOP

- When would you choose a class over a case class?
- When is inheritance appropriate, and when is a trait or composition better?
- Why use a companion object with a private constructor?

### FP

- What is referential transparency?
- How do `map`, `flatMap`, and `foldLeft` differ?
- What is the difference between currying and partial application?

### Errors

- When should you use `Option`, `Either`, or `Try`?
- Why is `Either` better than exceptions for domain validation?
- Why is `.get` on `Option` considered unsafe?

### Domain Modeling

- What does “make illegal states unrepresentable” mean?
- Why are sealed traits useful for workflow/state modeling?
- How would you split immutable domain data and stateful services in a real application?


## Scala 3 Features

- `module14scala3syntax/OptionalBraces.scala` — indentation syntax and end markers
- `module14scala3syntax/TopLevelDefinitions.scala` — top-level defs, vals, enums, case classes
- `module14scala3syntax/MainMethods.scala` — Scala 3 `@main` entry points
- `module14scala3syntax/WildcardTypes.scala` — `?` wildcard types
- `module15givensandusing/*.scala` — `given`, `using`, summon, context bounds, given imports
- `module16extensionandtypeclasses/ExtensionMethods.scala` — extension methods
- `module16extensionandtypeclasses/TypeClasses.scala` — type-class style programming
- `module16extensionandtypeclasses/MultiversalEquality.scala` — `CanEqual` and strict equality
- `module16extensionandtypeclasses/ImplicitConversions.scala` — controlled conversions and trade-offs
