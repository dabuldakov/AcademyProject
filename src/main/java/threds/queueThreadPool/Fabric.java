package threds.queueThreadPool;

import threds.Constants;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Fabric {

    private static final int MAX_QUEUE_LENGTH = 10;
    private static Queue<Integer> queue = new LinkedList<>();
    private final Random random = new Random();
    private int countProducer;
    private int countConsumer;
    private final Object lock = new Object();
    private final Object lock2 = new Object();

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
                Thread.sleep(300);
                System.out.println(Constants.ANSI_RED + Thread.currentThread().getName() + " " + "Producer" + Constants.ANSI_RESET + " offer number: " + data +
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
                Thread.sleep(300);
                System.out.println(Constants.ANSI_GREEN + Thread.currentThread().getName() + " " + "Consumer" + Constants.ANSI_RESET + " poll  number: " + poll +
                        " Consumer count: " + countConsumer +
                        " Queue size: " + queue.size());
            }
        }
    }
}
