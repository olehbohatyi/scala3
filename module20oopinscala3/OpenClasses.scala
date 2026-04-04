package module20oopinscala3

// Summary: In Scala 3, classes are closed for inheritance by default unless marked open.
// This makes inheritance an explicit design choice instead of an accidental extension point.
// The feature encourages safer APIs and more deliberate object-oriented modeling.
// When inheritance is intended, open communicates that choice clearly to readers.
// This file shows a small hierarchy built from an explicitly open base class.

open class Notification(val channel: String):
    def deliver(message: String): String = s"[$channel] $message"

class EmailNotification extends Notification("email"):
    override def deliver(message: String): String =
        super.deliver(s"$message (sent)")

class SmsNotification extends Notification("sms"):
    override def deliver(message: String): String =
        super.deliver(s"$message (queued)")

@main def openClasses(): Unit =

    // =========================================================
    // Inheritance is explicit because the base class is open
    // =========================================================

    val email = EmailNotification()
    val sms = SmsNotification()

    println(email.deliver("Interview scheduled"))
    println(sms.deliver("Code review reminder"))
