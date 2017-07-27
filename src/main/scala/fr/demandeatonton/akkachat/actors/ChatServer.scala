package fr.demandeatonton.akkachat.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import fr.demandeatonton.akkachat.actors.ChatServer.{Broadcast, Join, Participant, SendMessage}

class ChatServer extends Actor with ActorLogging {
  var participants = List.empty[Participant]
  // créer une map qui contient les clients
  // les watcher
  // et broadcaster les messages reçus
  override def receive: Receive = {
    case Join(who) =>
      log.info("{} joined the chat", who.name)
      participants.foreach(p => p.who ! Broadcast("system", s"${who.name} joined the chat"))
      who :: participants
    case SendMessage(who, message) =>
      log.info("Message received from {}: {}", who.name, message)
  }
}

object ChatServer {
  def props(): Props = Props(new ChatServer)

  case class Participant(who: ActorRef, name: String)

  sealed trait ChatEvent

  case class Join(who: Participant) extends ChatEvent
  case class Leave(who: Participant) extends ChatEvent
  case class SendMessage(who: Participant, msg: String) extends ChatEvent
  case class Broadcast(author: String, msg: String) extends ChatEvent
}
