package myactor

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.{Actor, ActorRef, Props, PoisonPill}
import com.typesafe.scalalogging.LazyLogging
import org.w3c.dom.TypeInfo

import java.util.UUID

class MyActor extends Actor with LazyLogging{

  var countReceivedMessages = 0;

  def concat: Unit = countReceivedMessages += 1
  def getName: String = UUID.randomUUID().toString;

  def receive: Receive = {
    case s: Tweet =>
      println(s"We got a tweet from ${self.path}")
//      sender() ! "adfaf"

    case s: String =>
      Thread.sleep(1000);
      concat
      if (countReceivedMessages > 4){
        context.system.terminate()
      }
      val childActor = context.actorOf(Props[MyActor](), name = getName)
      val childActor2 = context.actorOf(Props[MyActor](), name = getName)
      self ! Tweet("some twwt")
      childActor ! Tweet("Some tweet")
      childActor2 ! Tweet("Some tweet")
      logger.info("Actor 1 received the message")
      println(s"$s")
      sender() ! s

    case _ =>
      println(Thread.currentThread().getName)
      println("I really don't give a shit")
  }
}

class MyActorWithParam(actor: ActorRef) extends Actor with LazyLogging {
  def receive: Receive = {
    case "start" =>
      Thread.sleep(1000);
      logger.info("Actor 2 send the message")
      actor ! "message"
    case message: String =>
      Thread.sleep(1000);
      logger.info("Actor 2 received and send the message")
      sender() ! message
  }
}

case class Tweet(content: String)
