package fr.demandeatonton.akkachat.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import fr.demandeatonton.akkachat.actors.ChatServer.{Broadcast, Join, Participant, SendMessage}

object ChatClient {
  def props(name: String, chatServer: ActorRef): Props = Props(new ChatClient(name, chatServer))
}

class ChatClient private(name: String, chatServer: ActorRef) extends Actor with ActorLogging {
  var alias: Option[String] = None

  override def receive: Receive = {
    case Broadcast(who, message) =>
      log.info(s"Broadcast from ${who} : $message")
  }

  chatServer ! Join(Participant(self, name))
  chatServer ! SendMessage(Participant(self, name), s"Salut c'est $name")
}
