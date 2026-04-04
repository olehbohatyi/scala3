package module07collections

// Summary: Reimplementing map once is a good way to internalize how collection transformations work.
// The goal is not to replace the standard library, but to understand the abstraction beneath it.
// A small recursive implementation shows that map preserves structure while changing element values.
// This kind of exercise improves intuition for FP-style collection programming.
// The example keeps the implementation intentionally small and interview-friendly.

@main def buildYourOwnMap(): Unit =

    // =========================================================
    // A tiny recursive map for List
    // =========================================================

    def mapList[A, B](values: List[A])(f: A => B): List[B] =
        values match
            case Nil => Nil
            case head :: tail => f(head) :: mapList(tail)(f)

    println(mapList(List(1, 2, 3))(_ * 2))
    println(mapList(List("a", "bb", "ccc"))(_.length))
