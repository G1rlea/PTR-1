import akka.NotUsed
import akka.actor.ActorSystem
import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import de.heikoseeberger.akkasse.scaladsl.model.ServerSentEvent
import de.heikoseeberger.akkasse.scaladsl.unmarshalling.EventStreamUnmarshalling

case class MailServer(url: String, username: String, password: String)

object Main extends App{
  implicit val system = ActorSystem()

  import EventStreamUnmarshalling._
  import system.dispatcher
  Http()
    .singleRequest(Get("http://localhost:8000/tweets/1"))
    .flatMap(Unmarshal(_).to[Source[ServerSentEvent, NotUsed]])
    .foreach(_.runForeach(println))


}
