

package com.akkax.actors

import akka.Done
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.akkax.actor.ProcessorActor
import akka.testkit.{ImplicitSender, TestKit}
import com.akkax.actor.ProcessorActor.Create
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AsyncWordSpecLike
import org.scalatest.BeforeAndAfterAll

import scala.concurrent.duration._
import scala.language.postfixOps

class ProcessorActorSpec()
  extends TestKit(ActorSystem("MySpec")) with ImplicitSender with AsyncWordSpecLike with Matchers
    with BeforeAndAfterAll {
  implicit val timeout: Timeout = Timeout(12 seconds)

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "ProcessorActor" must {
    "write files successfully" in {
      val writer = system.actorOf(Props(new ProcessorActor("/home/knoldus/IdeaProjects/Akka-Assignment/src/main/temp/")))
      (writer ? Create("test_log_1", "File Content 1")).map {
        result => assert(result === Done)
      }
    }
    "write files with failure" in {
      val writer = system.actorOf(Props(new ProcessorActor("/home/knoldus/IdeaProjects/Akka-Assignment/src/main/temp/")))
      (writer ? Create("test_log_1", "File Content 1")).map {
        result => assert(result === Done)
      }
    }
  }

}
