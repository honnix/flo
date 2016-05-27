package io.rouz.task.scala

import io.rouz.task.{Task, TaskContext}

object Test extends App {

  case class TransitionsMatrixTable(date: String) {
    val table = s"monetization-data-science:flatex_test.transition_matrix_$date"
  }

  def transitionMatrix(date: String): Task[TransitionsMatrixTable] =
    FloTask.named("TransitionMatrix", date)
        .in(transitionMatrix(date))
        .in(transitionMatrix(date))
        .in(transitionMatrix(date))
        .process((a, b, c) => a)


  val t = transitionMatrix("2016-05-12")
  println(t.`type`())


  def simple: Task[Int] = FloTask.named("Simple").process(5)

  private val process: Task[Int] = FloTask.named("Foobar").curried
      .in(simple)
      .in(simple)
      .in(simple)
      .in(simple)
      .process(a => b => c => d => a + b + c + d)

  import Util._

  TaskContext.inmem().evaluate(process).consume(fn0(println))
}
