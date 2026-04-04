package module13projects

import scala.collection.immutable.ListMap

// Summary: This mini-project models a hiring pipeline with immutable feedback and a policy service.
// The domain uses enums and sealed decisions, while orchestration lives in a mutable pipeline class.
// Validation and policy outcomes use Either to keep business failures explicit.
// This is a strong interview example because it combines workflow modeling and collection-based analysis.
// It also demonstrates how OOP and FP complement each other in real application code.

@main def interviewPipelineApp(): Unit =

    // =========================================================
    // Domain model
    // =========================================================

    enum Stage:
        case Screening, Technical, SystemDesign, Leadership

    case class Candidate(id: String, name: String, targetRole: String)
    case class InterviewFeedback(stage: Stage, score: Int, notes: String):
        require(score >= 1 && score <= 5, s"score must be between 1 and 5: $score")

    sealed trait HiringDecision
    object HiringDecision:
        case object Hire extends HiringDecision
        case object StrongNoHire extends HiringDecision
        case class Hold(reason: String) extends HiringDecision

    trait DecisionPolicy:
        def decide(feedback: Vector[InterviewFeedback]): Either[String, HiringDecision]

    object DefaultDecisionPolicy extends DecisionPolicy:
        def decide(feedback: Vector[InterviewFeedback]): Either[String, HiringDecision] =
            if feedback.isEmpty then Left("cannot decide without feedback")
            else if feedback.exists(_.score <= 2) then Right(HiringDecision.StrongNoHire)
            else
                val average = feedback.map(_.score).sum.toDouble / feedback.size
                if average >= 4.5 then Right(HiringDecision.Hire)
                else Right(HiringDecision.Hold(f"average=$average%.2f"))

    // =========================================================
    // Application service
    // =========================================================

    class InterviewPipeline(val policy: DecisionPolicy):
        private var feedbackByCandidate: Map[String, Vector[InterviewFeedback]] = Map.empty

        def record(candidate: Candidate, feedback: InterviewFeedback): Either[String, Unit] =
            if candidate.id.trim.isEmpty then Left("candidate id must be non-empty")
            else if candidate.name.trim.isEmpty then Left("candidate name must be non-empty")
            else if feedback.notes.trim.isEmpty then Left("feedback notes must be non-empty")
            else
                val updated = feedbackByCandidate.getOrElse(candidate.id, Vector.empty) :+ feedback
                feedbackByCandidate = feedbackByCandidate.updated(candidate.id, updated)
                Right(())

        def allFeedback(candidate: Candidate): Vector[InterviewFeedback] =
            feedbackByCandidate.getOrElse(candidate.id, Vector.empty)

        def averageScore(candidate: Candidate): Double =
            val values = allFeedback(candidate)
            if values.isEmpty then 0.0 else values.map(_.score).sum.toDouble / values.size

        def scoreCard(candidate: Candidate): ListMap[Stage, Int] =
            val pairs = allFeedback(candidate).map(item => item.stage -> item.score)
            ListMap.from(pairs.sortBy(_._1.ordinal))

        def decision(candidate: Candidate): Either[String, HiringDecision] =
            policy.decide(allFeedback(candidate))

    // =========================================================
    // Pure helpers
    // =========================================================

    def strongestStage(feedback: Vector[InterviewFeedback]): Option[Stage] =
        feedback.sortBy(_.score).lastOption.map(_.stage)

    def summary(candidate: Candidate, pipeline: InterviewPipeline): String =
        val feedback = pipeline.allFeedback(candidate)
        val strongest = strongestStage(feedback).map(_.toString).getOrElse("none")
        f"candidate=${candidate.name}, avg=${pipeline.averageScore(candidate)}%.2f, strongest=$strongest"

    // =========================================================
    // Demo scenario
    // =========================================================

    val candidate = Candidate("cand-1", "Elena", "Senior Scala Engineer")
    val pipeline = new InterviewPipeline(DefaultDecisionPolicy)

    println(pipeline.record(candidate, InterviewFeedback(Stage.Screening, 5, "clear motivation and communication")))
    println(pipeline.record(candidate, InterviewFeedback(Stage.Technical, 5, "strong Scala and FP depth")))
    println(pipeline.record(candidate, InterviewFeedback(Stage.SystemDesign, 4, "good trade-off thinking")))
    println(pipeline.record(candidate, InterviewFeedback(Stage.Leadership, 4, "solid mentoring examples")))

    println(summary(candidate, pipeline))
    println(pipeline.scoreCard(candidate))
    println(pipeline.decision(candidate))
