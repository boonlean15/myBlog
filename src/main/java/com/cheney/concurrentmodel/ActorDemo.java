package com.cheney.concurrentmodel;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * 面向对象原生的并发解决方案
 */
public class ActorDemo {

    static class HelloActor extends UntypedAbstractActor {
        @Override
        public void onReceive(Object message) {
            System.out.println("Hello " + message);
        }
    }

    public static void main(String[] args) {
        //创建Actor系统
        ActorSystem actorSystem = ActorSystem.apply("HelloSystem");
        //创建HelloActor
        ActorRef helloActor = actorSystem.actorOf(Props.create(HelloActor.class));
        //发送消息给HelloActor
        helloActor.tell("cheney", ActorRef.noSender());
    }
}
