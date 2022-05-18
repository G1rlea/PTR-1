import akka.actor.{ActorSystem, Props}
import ssehandling.{SseHandler, StartSseHandler}


object Main extends App{
  implicit val system: ActorSystem = ActorSystem()



  val connector = system.actorOf(Props(new SseHandler), name = "SSE-Connector")
  connector ! StartSseHandler


}
