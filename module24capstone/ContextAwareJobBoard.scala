package module24capstone

// Summary: This capstone combines smart constructors, givens, context functions, and export clauses.
// The goal is to show how Scala 3 features can keep a service compact while preserving strong modeling.
// The domain is a simple job board with validated inputs and context-driven behavior.
// A small repository is exported through a service façade to reduce boilerplate.
// This file is designed as an interview-friendly integration example.

case class JobTitle private (value: String)
object JobTitle:
    def from(value: String): Either[String, JobTitle] =
        val normalized = value.trim
        if normalized.nonEmpty then Right(JobTitle(normalized))
        else Left("Job title must not be empty")

case class Job(title: JobTitle, remote: Boolean)

trait Audit:
    def log(message: String): Unit

case class ConsoleAudit(prefix: String) extends Audit:
    def log(message: String): Unit = println(s"[$prefix] $message")

case class RequestContext(user: String)

type Program[A] = (RequestContext, Audit) ?=> A

class JobRepository:
    private val jobs = List(
        Job(JobTitle.from("Scala Engineer").toOption.get, remote = true),
        Job(JobTitle.from("Platform Developer").toOption.get, remote = false)
    )

    def all: List[Job] = jobs

class JobService(repo: JobRepository):
    export repo.all

    def remoteTitles: Program[List[String]] =
        summon[Audit].log(s"user=${summon[RequestContext].user} requested remote titles")
        all.filter(_.remote).map(_.title.value)

@main def contextAwareJobBoard(): Unit =

    // =========================================================
    // Validated creation plus contextual workflow
    // =========================================================

    println(JobTitle.from("Senior Scala Mentor"))
    println(JobTitle.from("   "))

    given RequestContext = RequestContext("alice")
    given Audit = ConsoleAudit("job-board")

    val service = JobService(JobRepository())
    println(service.all.map(_.title.value))
    println(service.remoteTitles)
