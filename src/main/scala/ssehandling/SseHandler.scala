package ssehandling

import akka.actor.{Actor, ActorLogging, Props}

case class StartSseHandler()
case class TweetMessage(message: String)

class SseHandler extends Actor with ActorLogging{

  override def receive: Receive = {
    case StartSseHandler =>
      val tweet_1 = context.actorOf(Props(new SseConnector("/tweets/1")), name = "tweet_1")
      val tweet_2 = context.actorOf(Props(new SseConnector("/tweets/2")), name = "tweet_2")
      tweet_1 ! StartConnection
      tweet_2 ! StartConnection
    case x : TweetMessage => {
      //TODO: Send the message to the server
      println(x.message.length)
    }

  }
}
