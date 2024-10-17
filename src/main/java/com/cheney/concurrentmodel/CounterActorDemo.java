package com.cheney.concurrentmodel;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CounterActorDemo {

    //累加器
    static class CounterActor extends UntypedAbstractActor{
        private int count = 0;

        @Override
        public void onReceive(Object message) {
            //如果接收到的消息是数字类型，执行累加操作，否则打印counter的值
            if(message instanceof Number){
                count += ((Number) message).intValue();
            }else {
                System.out.println("count: " + count);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        ActorSystem actorSystem = ActorSystem.create("CountSystem");//创建Actor系统
        ActorRef counterActor = actorSystem.actorOf(Props.create(CounterActor.class));//创建CounterActor
        ExecutorService executorService = Executors.newFixedThreadPool(4);//4个线程生产消息
        //生产4*100000个消息
        for(int i = 0; i < 4; i++){
            executorService.execute(() -> {
                for(int j = 0; j < 10000; j++){
                    counterActor.tell(1, ActorRef.noSender());
                }
            });
        }
        executorService.shutdown();//关闭线程池
        Thread.sleep(100);//等待CounterActor处理完所有消息
        counterActor.tell("",ActorRef.noSender());//打印结果
        System.out.println(System.currentTimeMillis());
        actorSystem.terminate();//关闭Actor系统
    }

}