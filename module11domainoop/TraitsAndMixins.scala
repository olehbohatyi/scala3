package module11domainoop

// Summary: A trait is a reusable unit of behavior and/or an abstract contract.
// Traits can define abstract members, concrete methods, vals, and given state-less behavior.
// A class can mix in multiple traits — Scala supports multiple inheritance of behavior via traits.
// Mixins are powerful for composition and often preferred over deep inheritance trees.
// Traits are heavily used in FP libraries for typeclasses, capabilities, and service interfaces.

@main def traitsAndMixins(): Unit =

    // =========================================================
    // Trait as a contract
    // =========================================================

    trait Logger:
        def log(message: String): Unit

    class ConsoleLogger extends Logger:
        def log(message: String): Unit = println(s"[console] $message")

    val logger = new ConsoleLogger
    logger.log("application started")

    // =========================================================
    // Trait with concrete behavior
    // =========================================================

    trait TimestampLogger:
        def now: String = "2026-04-04T09:55:00"
        def logWithTimestamp(message: String): Unit =
            println(s"[$now] $message")

    class AuditService extends TimestampLogger
    val audit = new AuditService
    audit.logWithTimestamp("user signed in")

    // =========================================================
    // Mix multiple traits into one class
    // =========================================================

    trait Identified:
        def id: String

    trait Named extends Identified:
        def name: String
        def label: String = s"$name#$id"

    class Project(val id: String, val name: String) extends Identified, Named

    val project = new Project("p-101", "InterviewPrep")
    println(project.label)

    // =========================================================
    // Stackable mixins — override abstract override pattern
    // =========================================================

    trait Notifier:
        def send(message: String): String

    class BaseNotifier extends Notifier:
        def send(message: String): String = s"sent: $message"

    trait Uppercase extends Notifier:
        abstract override def send(message: String): String =
            super.send(message.toUpperCase)

    trait PrefixWithChannel extends Notifier:
        abstract override def send(message: String): String =
            super.send(s"[email] $message")

    val notifier1 = new BaseNotifier with Uppercase with PrefixWithChannel
    val notifier2 = new BaseNotifier with PrefixWithChannel with Uppercase

    println(notifier1.send("deploy tonight"))
    println(notifier2.send("deploy tonight"))

    // =========================================================
    // Traits as service-like dependencies
    // =========================================================

    trait Clock:
        def currentHour(): Int

    trait GreetingService:
        def clock: Clock
        def greeting(name: String): String =
            val part =
                if clock.currentHour() < 12 then "Good morning"
                else if clock.currentHour() < 18 then "Good afternoon"
                else "Good evening"
            s"$part, $name"

    object FixedMorningClock extends Clock:
        def currentHour(): Int = 9

    class Greeter(val clock: Clock) extends GreetingService

    val greeter = new Greeter(FixedMorningClock)
    println(greeter.greeting("Scala"))

    // =========================================================
    // Anonymous class with traits
    // =========================================================

    val customLogger = new Logger:
        def log(message: String): Unit = println(s"[custom] ${message.reverse}")

    customLogger.log("abc")

    // =========================================================
    // Additional: trait with self-type style requirement preview
    // =========================================================

    trait Repository:
        def find(id: String): String

    trait RepositoryBacked:
        def repository: Repository
        def loadLabel(id: String): String = s"loaded=${repository.find(id)}"

    object InMemoryRepository extends Repository:
        def find(id: String): String = s"entity:$id"

    class Loader(val repository: Repository) extends RepositoryBacked

    val loader = new Loader(InMemoryRepository)
    println(loader.loadLabel("42"))
