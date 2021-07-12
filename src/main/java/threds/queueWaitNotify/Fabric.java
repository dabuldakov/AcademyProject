package threds.queueWaitNotify;

import threds.Constants;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Fabric {

    private Queue<Integer> queue = new LinkedList<>();
    private static final int MAX_QUEUE_LENGTH = 10;

    private Random random = new Random();
    private int countProducer;
    private int countConsumer;
    private final Object lock = new Object();

    void produce() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                lock.notify();
                while (queue.size() == MAX_QUEUE_LENGTH) {
                    lock.wait();
                }
                countProducer++;
                int data = random.nextInt(1000);
                queue.offer(data);
                System.out.println(Constants.ANSI_RED + "Producer" + Constants.ANSI_RESET + " offer number: " + data +
                        " Producer count: " + countProducer +
                        " Queue size: " + queue.size());
            }
        }
    }

    void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                lock.notify();
                while (queue.size() == 0) {
                    lock.wait();
                }
                Integer poll = queue.poll();
                countConsumer++;
                Thread.sleep(100);
                System.out.println(Constants.ANSI_GREEN + "Consumer" + Constants.ANSI_RESET + " poll  number: " + poll +
                        " Consumer count: " + countConsumer +
                        " Queue size: " + queue.size());
            }
        }
    }
}
