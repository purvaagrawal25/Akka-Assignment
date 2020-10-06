
package com.akkax.actor

import java.io.{BufferedWriter, File, FileNotFoundException, FileWriter}

import scala.language.postfixOps
import akka.Done
import akka.actor.{Actor, OneForOneStrategy, SupervisorStrategy}
import com.akkax.actor.ProcessorActor.Create
import akka.actor.SupervisorStrategy._

import scala.concurrent.duration._

class ProcessorActor(path: String = "/home/knoldus/IdeaProjects/Akka-Assignment/src/main/temp/") extends Actor {


  println("Actor created with dispatcher :" + context.dispatcher)
  override val supervisorStrategy: SupervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: FileNotFoundException => Resume
      case _: NullPointerException => Restart
      case _: IllegalArgumentException => Stop
      case _: Exception => Escalate
    }

  override def receive: Receive = {
    case create: Create =>
      println(s"Message found by actor [${self.path.name}]: " + create.fileName + " Content " + create.content)
      val file = new File(path + create.fileName)
      val fw = new FileWriter(file, true)
      val bw = new BufferedWriter(fw)
      bw.write(create.content + "\n")
      bw.close()
      Thread.sleep(5000)
      sender() ! Done

    case msg => println(s"wrong message:$msg")
  }
}

object ProcessorActor {

  case class Create(fileName: String, content: String)

}
