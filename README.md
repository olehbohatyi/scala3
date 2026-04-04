# Scala 3 Interview Compact Course

Compact, runnable Scala 3 notes for interview prep, knowledge refresh, mentoring, and course creation.

## Goals

- Keep every topic short, practical, and runnable.
- Use small files with focused examples.
- Make navigation simple enough for fast review before interviews.
- Combine Scala syntax, collections, OOP, FP, and domain modeling in one place.

## Repository Conventions

- All repository-facing text is in English.
- Each module uses a package name like `module01setup`.
- Each `.scala` file is a compilable Scala program with `@main`.
- Comments use only `//`.
- Every Scala file starts with a short summary comment.
- README files stay brief and action-oriented.

## Quick Start

```bash
scala-cli run module01setup/
scala-cli run module07collections/
scala-cli run module10functions/
scala-cli run module13projects/
```

## Course Map

| Module | Focus | Key Files |
|--------|-------|-----------|
| `module01setup` | Scala 3 setup and syntax basics | `WhyScala3.scala`, `IndentationSyntax.scala`, `CommentsAndCodeBlocks.scala` |
| `module02basics` | `val`, `var`, types, REPL, printing | `ValFields.scala`, `VarFields.scala`, `ExplicitTypes.scala` |
| `module03strings` | String interpolation and methods | `StringInterpolators.scala`, `StringMethods.scala`, `MultilineStrings.scala` |
| `module04numbers` | Numeric types and math expressions | `NumericTypes.scala`, `MathExpressions.scala` |
| `module05constructs` | Control flow constructs | `IfElseAndEOP.scala`, `ForLoops.scala`, `ForExpressions.scala`, `WhileLoop.scala`, `TryCatchFinally.scala` |
| `module06tuples` | Tuples and destructuring | `Tuples.scala` |
| `module07collections` | Immutable collections and sequence operations | `ListClass.scala`, `MapAndFilter.scala`, `SequenceMethods.scala` |
| `module08arraybuffer` | Mutable collections, `ArrayBuffer`, `Array` | `ArrayBufferBasics.scala`, `ArrayVsArrayBuffer.scala` |
| `module09mapset` | `Map`, `Set`, mutable vs immutable | `MapCollection.scala`, `MutableMap.scala`, `SetCollection.scala` |
| `module10functions` | Pure functions, HOFs, closures, currying | `PureFunctions.scala`, `HigherOrderFunctions.scala`, `Currying.scala` |
| `module11domainoop` | OOP and domain-oriented modeling | `ClassesAndConstructors.scala`, `TraitsAndMixins.scala`, `EnumsBasics.scala` |
| `module12domainfp` | Functional domain modeling | `CaseClasses.scala`, `SealedTraitsAndAdts.scala`, `OptionBasics.scala`, `EitherBasics.scala`, `TryBasics.scala` |
| `module13projects` | Mini-projects for interviews and portfolio | `ExpenseTrackerApp.scala`, `InterviewPipelineApp.scala`, `LibraryRentalApp.scala`, `SupportTicketApp.scala` |
| `module14scala3syntax` | Scala 3 syntax updates | `OptionalBraces.scala`, `TopLevelDefinitions.scala`, `MainMethods.scala`, `WildcardTypes.scala` |
| `module15givensandusing` | Contextual abstractions basics | `GivenInstances.scala`, `UsingClauses.scala`, `SummonAndContextParameters.scala`, `ContextBounds.scala`, `GivenImports.scala` |
| `module16extensionandtypeclasses` | Extension methods, type classes, equality, conversions | `ExtensionMethods.scala`, `TypeClasses.scala`, `MultiversalEquality.scala`, `ImplicitConversions.scala` |

## Navigation by Goal

### Refresh Scala syntax fast

1. `module01setup`
2. `module02basics`
3. `module03strings`
4. `module04numbers`
5. `module05constructs`

### Strengthen collections and data transformation

1. `module06tuples`
2. `module07collections`
3. `module08arraybuffer`
4. `module09mapset`
5. `module10functions`

### Prepare for OOP + FP interviews

1. `module10functions`
2. `module11domainoop`
3. `module12domainfp`
4. `module13projects`

## Interview Focus Areas

- **Collections**: `module06`–`module09`
- **Functional Programming**: `module10`, `module12`
- **OOP and design**: `module11`, `module13`
- **Error handling**: `module05`, `module12`
- **Domain modeling**: `module11`, `module12`, `module13`
- **Scala 3-specific features**: `module14`, `module15`, `module16`

## Recommended Review Order Before Interviews

### 60-minute review

- `module05constructs`
- `module07collections`
- `module10functions`
- `module12domainfp`
- `module13projects`

### 1-day review

- `module01setup` to `module05constructs`
- `module07collections` to `module10functions`
- `module11domainoop` to `module13projects`

## Supporting Files

- `INTERVIEW_FAST_TRACK.md` — shortest high-value interview review path
- `INTERVIEW_QUESTIONS_AND_ANSWERS.md` — compact interview rehearsal answers
- `INTERVIEW_CHECKLIST.md` — last-minute checklist before interviews
- `INTERVIEW_INDEX.md` — topic-based interview navigation
- `POLISH_AUDIT.md` — repository consistency check after the polish pass
- `project.scala` — shared Scala CLI configuration
