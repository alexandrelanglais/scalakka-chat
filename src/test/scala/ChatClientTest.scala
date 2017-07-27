import akka.actor.ActorSystem
import akka.testkit.{TestKit, TestProbe}
import fr.demandeatonton.akkachat.actors.ChatClient
import org.scalatest.{BeforeAndAfterAll, FlatSpec, FlatSpecLike, Matchers}

class ChatClientTest(_system: ActorSystem) extends TestKit(_system)
  with Matchers
  with FlatSpecLike
  with BeforeAndAfterAll {

  def this() = this(ActorSystem("akkaChat-test"))

  override def afterAll(): Unit = shutdown(system)

}
