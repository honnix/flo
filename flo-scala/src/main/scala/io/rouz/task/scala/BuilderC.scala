package io.rouz.task.scala

import io.rouz.task.dsl.TaskBuilder._
import io.rouz.task.dsl.{TaskBuilder => JTB}
import io.rouz.task.{Task, scala}

abstract class BuilderC[A, Y, Z] extends scala.TaskBuilderC[A, Y, Z] { self =>

  import Util._

  type JA
  type JY
  val convA: JA => A
  val convY: Y => JY
  val builder: JTB.TaskBuilderC[JA, JY, Z]

  override def process(code: A => Y): Task[Z] =
    builder.process(f1(a => convY(code(convA(a)))))

  override def in[B](task: => Task[B]): scala.TaskBuilderC[B, A => Y, Z] = new BuilderCPlain[B, A => Y, Z] {
    type JA = B
    type JY = F1[self.JA, self.JY]
//    val builder: JTB.TaskBuilderC[JA, JY, Z] = self.builder.in(f0(task))
  }

  override def ins[B](tasks: => List[Task[B]]): scala.TaskBuilderC[List[B], A => Y, Z] = ???

//  override def in[B](task: => Task[B]): TaskBuilderC[B, A => Y, Z] = new BuilderC[B, A => Y, Z] {
//    private val in1: JTB.TaskBuilderC[B, F1[self.JA, self.JY], Z] = self.builder.in(f0[Task[B]](task))
//    in1
//
//    type JA = B
//    type JY = F1[self.JA, self.JY]
//    val convA: JA => B = identity
//    val convY: (A => Y) => JY = ???
//    val builder = in1
//  }
//
//  override def ins[B](tasks: => List[Task[B]]): TaskBuilderC[List[B], A => Y, Z] =
//    new BuilderC[List[B], A => Y, Z] {
//      type JA = java.util.List[B]
//      type JY = F1[self.JA, self.JY]
//      val convA: JA => List[B] = b => JavaConversions.iterableAsScalaIterable(b).toList
//      val convY: (A => Y) => JY = f1
//      val builder = self.builder.ins(javaList(tasks))
//    }
}

trait BuilderCPlain[A, Y, Z] extends scala.TaskBuilderC[A, Y, Z] { self =>

  type JA
  type JY
  type JavaBuilder = JTB.TaskBuilderC[JA, JY, Z]
//  val builder: JTB.TaskBuilderC[JA, JY, Z]

  override def process(code: A => Y): Task[Z] = ???
//    builder.process(f1(code))

  override def in[B](task: => Task[B]): scala.TaskBuilderC[B, A => Y, Z] = new BuilderCPlain[B, A => Y, Z] {
    type JA = B
    type JY = F1[self.JA, self.JY]
//    val builder: JTB.TaskBuilderC[JA, JY, Z] = self.builder.in(f0(task))

  }

  override def ins[B](tasks: => List[Task[B]]): scala.TaskBuilderC[List[B], A => Y, Z] = ???
}
