import akka.actor.{ActorSystem, Props}
import myactor.{MyActor, MyActorWithParam}

object Main extends App{
  val system: ActorSystem = ActorSystem("MySystem")
  val actor1 = system.actorOf(Props[MyActor](), name = "myactor1")
  val actor2 = system.actorOf(Props(new MyActorWithParam(actor1)), name = "actor2")
  actor2 ! "start"
}
