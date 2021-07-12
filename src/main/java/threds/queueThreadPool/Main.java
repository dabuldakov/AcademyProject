package threds.queueThreadPool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {


    public static void main(String[] args) {


        ExecutorService executorServiceConsumer = Executors.newFixedThreadPool(10);
        ExecutorService executorServiceProducer = Executors.newFixedThreadPool(10);

        Fabric fabric = new Fabric();

        for (int i = 0; i < 10; i++) {
            executorServiceProducer.submit(() -> {
                try {
                    fabric.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            executorServiceConsumer.submit(() -> {
                try {
                    fabric.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }


    }
}
