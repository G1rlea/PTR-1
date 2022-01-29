import akka.actor.{ActorRef, ActorSystem, Props}
import com.typesafe.scalalogging.*

object Main extends App{
  val system: ActorSystem = ActorSystem("MySystem")
  val actor1 = system.actorOf(Props[MyActor](), name = "myactor1")
  val actor2 = system.actorOf(Props[MyActor](), name = "myactor2")
  println("Main thread 1")
  actor1 ! "Hi There"
  actor1 ! 6
  actor2 ! 6
  println("Main thread 2")
  actor1 ! "Hi There"
  actor1 ! Option[1]
  println("Main thread 3")
//  system.terminate()
}
