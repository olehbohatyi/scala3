package module11domainoop

// Summary: Inheritance models an is-a relationship: subclass extends superclass behavior.
// override is mandatory when redefining a member from the parent.
// Abstract classes may contain both implemented and abstract members.
// Polymorphism means code can work against a base type while runtime dispatch picks the override.
// Prefer composition first; use inheritance when the subtype relationship is truly stable.

@main def inheritanceAndOverrides(): Unit =

    // =========================================================
    // Basic inheritance with override
    // =========================================================

    abstract class Shape:
        def area: Double
        def describe: String = s"shape area=$area"

    class Circle(val radius: Double) extends Shape:
        override def area: Double = math.Pi * radius * radius
        override def describe: String = f"Circle(radius=$radius%.1f, area=$area%.2f)"

    class Rectangle(val width: Double, val height: Double) extends Shape:
        override def area: Double = width * height
        override def describe: String = f"Rectangle($width%.1f x $height%.1f, area=$area%.2f)"

    val circle = new Circle(2.0)
    val rectangle = new Rectangle(3.0, 4.0)
    println(circle.describe)
    println(rectangle.describe)

    // =========================================================
    // Polymorphism — work against the parent type
    // =========================================================

    val shapes: List[Shape] = List(circle, rectangle, new Circle(1.5))
    val totalArea = shapes.map(_.area).sum
    shapes.foreach(shape => println(shape.describe))
    println(f"total area = $totalArea%.2f")

    // =========================================================
    // Protected members
    // =========================================================

    abstract class Employee(protected val baseSalary: BigDecimal):
        def role: String
        def salary: BigDecimal = baseSalary
        def summary: String = s"$role salary=$salary"

    class Developer(salary0: BigDecimal) extends Employee(salary0):
        override def role: String = "Developer"

    class Manager(salary0: BigDecimal, val bonus: BigDecimal) extends Employee(salary0):
        override def role: String = "Manager"
        override def salary: BigDecimal = baseSalary + bonus

    val dev = new Developer(BigDecimal(6000))
    val manager = new Manager(BigDecimal(7000), BigDecimal(1500))
    println(dev.summary)
    println(manager.summary)

    // =========================================================
    // final prevents overriding / extension
    // =========================================================

    final class Money(val amount: BigDecimal, val currency: String):
        override def toString: String = s"Money($amount $currency)"

    val salary = new Money(BigDecimal(10000), "EUR")
    println(salary)
    // class Crypto(amount: BigDecimal) extends Money(amount, "BTC") // does not compile

    // =========================================================
    // sealed restricts subclassing to the same file
    // =========================================================

    sealed abstract class PaymentMethod:
        def label: String

    final class Card(val last4: String) extends PaymentMethod:
        def label: String = s"Card(****$last4)"

    final class WireTransfer(val ibanSuffix: String) extends PaymentMethod:
        def label: String = s"Wire(****$ibanSuffix)"

    def fee(method: PaymentMethod): BigDecimal = method match
        case _: Card         => BigDecimal(1.5)
        case _: WireTransfer => BigDecimal(5)

    val methods: List[PaymentMethod] = List(new Card("1234"), new WireTransfer("6789"))
    methods.foreach(m => println(s"${m.label} fee=${fee(m)}"))

    // =========================================================
    // Additional: overriding vals and defs
    // =========================================================

    trait Environment:
        def name: String
        val debug: Boolean = false

    class DevEnvironment extends Environment:
        override val name: String = "dev"
        override val debug: Boolean = true

    class ProdEnvironment extends Environment:
        override val name: String = "prod"

    val envs: List[Environment] = List(new DevEnvironment, new ProdEnvironment)
    envs.foreach(env => println(s"env=${env.name}, debug=${env.debug}"))
