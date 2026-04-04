package module11domainoop

// Summary: Scala 3 enums define a closed set of cases with first-class syntax.
// Enums can be simple (no parameters) or parameterized like mini case classes.
// Methods can live on the enum itself, and companion-style utilities can be added in the enum body.
// Enums are great for domain states, roles, commands, and finite workflow steps.
// They are a bridge toward Algebraic Data Types used heavily in functional domain modeling.

@main def enumsBasics(): Unit =

    // =========================================================
    // Simple enum
    // =========================================================

    enum Role:
        case Admin, Editor, Viewer

    val role = Role.Admin
    println(role)
    println(role.ordinal)        // 0-based index of the case
    println(Role.values.toList)  // all cases

    // =========================================================
    // Enum with methods
    // =========================================================

    enum AccessLevel:
        case Guest, Member, Moderator, Owner

        def canEdit: Boolean = this match
            case Guest     => false
            case Member    => true
            case Moderator => true
            case Owner     => true

        def rank: Int = this.ordinal

    val levels = AccessLevel.values.toList
    levels.foreach(level => println(s"$level canEdit=${level.canEdit} rank=${level.rank}"))

    // =========================================================
    // Parameterized enum cases
    // =========================================================

    enum HttpStatus(val code: Int, val isSuccess: Boolean):
        case Ok                  extends HttpStatus(200, true)
        case Created             extends HttpStatus(201, true)
        case BadRequest          extends HttpStatus(400, false)
        case Unauthorized        extends HttpStatus(401, false)
        case InternalServerError extends HttpStatus(500, false)

        def category: String =
            if code < 300 then "success"
            else if code < 500 then "client-error"
            else "server-error"

    println(HttpStatus.Ok.code)
    println(HttpStatus.BadRequest.category)
    println(HttpStatus.values.map(_.code).toList)

    // =========================================================
    // Enum with companion-style helpers in the body
    // =========================================================

    enum Priority(val level: Int):
        case Low    extends Priority(1)
        case Medium extends Priority(2)
        case High   extends Priority(3)

    object Priority:
        def fromInt(value: Int): Option[Priority] =
            Priority.values.find(_.level == value)

    println(Priority.fromInt(2))
    println(Priority.fromInt(7))

    // =========================================================
    // Pattern matching on enums
    // =========================================================

    def render(role: Role): String = role match
        case Role.Admin  => "full-access"
        case Role.Editor => "edit-content"
        case Role.Viewer => "read-only"

    Role.values.foreach(r => println(s"$r -> ${render(r)}"))

    // =========================================================
    // Practical workflow state enum
    // =========================================================

    enum OrderState:
        case Draft, Submitted, Paid, Shipped, Cancelled

        def isTerminal: Boolean = this match
            case Shipped | Cancelled => true
            case _                   => false

    val journey = List(OrderState.Draft, OrderState.Submitted, OrderState.Paid, OrderState.Shipped)
    journey.foreach(s => println(s"$s terminal=${s.isTerminal}"))

    // =========================================================
    // Additional: parsing enums from strings safely
    // =========================================================

    object Role:
        def parse(value: String): Option[Role] =
            Role.values.find(_.toString.equalsIgnoreCase(value.trim))

    println(Role.parse("admin"))
    println(Role.parse("viewer"))
    println(Role.parse("unknown"))
