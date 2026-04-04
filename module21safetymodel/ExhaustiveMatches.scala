package module21safetymodel

// Summary: Closed models make pattern matching safer because all cases are known.
// Scala 3 can then help you write exhaustive matches over those states.
// This improves maintainability when workflows evolve and new states are added.
// ADT-style modeling is one of the strongest safety patterns in Scala.
// This file demonstrates a small job-state workflow with total pattern matching.

sealed trait JobState
object JobState:
    case object Draft extends JobState
    case object Running extends JobState
    case object Succeeded extends JobState
    case object Failed extends JobState

import JobState.*

def nextStep(state: JobState): String = state match
    case Draft     => "validate inputs"
    case Running   => "monitor progress"
    case Succeeded => "publish results"
    case Failed    => "inspect errors"

@main def exhaustiveMatches(): Unit =

    // =========================================================
    // Every known state is handled explicitly
    // =========================================================

    val states = List(Draft, Running, Succeeded, Failed)
    println(states.map(nextStep))
