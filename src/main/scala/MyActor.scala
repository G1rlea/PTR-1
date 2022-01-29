import akka.actor.{Actor, ActorRef}
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import org.w3c.dom.TypeInfo

class MyActor extends Actor{

  def receive: Receive = {
    case s: String =>
      Thread.sleep(3000)
      println(Thread.currentThread().getName)
      println("We got a string")
    case s: Int =>
      println(Thread.currentThread().getName)
      Thread.sleep(2000)
      println("We got a integer")
    case _ =>
      println(Thread.currentThread().getName)
      println("I really don't give a shit")
  }

}
