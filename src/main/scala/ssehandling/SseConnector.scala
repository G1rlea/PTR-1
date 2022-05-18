package ssehandling

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.sse.ServerSentEvent
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, Uri}
import akka.stream.alpakka.sse.scaladsl.EventSource

import scala.concurrent.Future

case class StartConnection()

class SseConnector(address: String) extends Actor with ActorLogging {
  implicit val system: ActorSystem = context.system

  val send: HttpRequest => Future[HttpResponse] = Http().singleRequest(_)
  val uri: Uri = Uri(s"http://localhost:8088/${address}");

  def receive(): Receive = {
    case StartConnection =>
      val senderActor: ActorRef = sender()
      EventSource(uri = uri, send = send)
        .runForeach(event => {
          val tweetValue: TweetMessage = TweetMessage(event.data);
          senderActor ! tweetValue
        })
  }
}
