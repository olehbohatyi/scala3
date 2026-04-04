package module18newtypes1

import scala.reflect.Selectable.reflectiveSelectable

// Summary: Structural types let you describe objects by the members they provide rather than by inheritance.
// They are flexible but rely on reflective selection, so they are usually not the first design choice.
// Traits and type classes are often better for stable public APIs.
// Structural types remain useful for adapters, experiments, and certain interoperability cases.
// This file shows a small example that works with any value having a close() method.

type Closable =
    // TODO https://docs.scala-lang.org/scala3/book/types-structural.html#:~:text=Structural%20types%20help%20in%20situations,and%20methods%20should%20be%20resolved.
    /*Selectable*/ {
        def close(): String
    }

case class FileHandle(name: String):
    def close(): String = s"closed file:$name"

case class NetworkConnection(host: String):
    def close(): String = s"closed host:$host"

def closeResource(resource: Closable): String = resource.close()

@main def structuralTypes(): Unit =

    // =========================================================
    // Different classes can satisfy the same structural contract
    // =========================================================

    println(closeResource(FileHandle("notes.txt")))
    println(closeResource(NetworkConnection("api.example.com")))
