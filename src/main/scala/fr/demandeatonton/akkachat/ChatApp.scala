package fr.demandeatonton.akkachat

import akka.actor.ActorSystem
import com.typesafe.scalalogging.LazyLogging
import fr.demandeatonton.akkachat.actors.{ChatClient, ChatServer}
import jdk.internal.instrumentation.Logger

import scala.io.StdIn

object ChatApp extends App with LazyLogging {

  val system = ActorSystem("scalakka-chat")

  val server = system.actorOf(ChatServer.props)

  val Seq(client1, client2, client3) =
    (1 to 3) map { i =>
      system.actorOf(ChatClient.props(s"client$i", server), s"client$i")
    }
  logger.info("Type ENTER to exit")
  StdIn.readLine()

  system.terminate()
}
