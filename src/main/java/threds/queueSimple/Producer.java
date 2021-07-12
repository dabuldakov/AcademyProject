package threds.queueSimple;

import java.util.Random;

class Producer implements Runnable {

    private Random random = new Random();
    private int count;
    private int countWork;
    private static final int MAX_QUEUE_LENGTH = 10;

    @Override
    public void run() {
        while (true) {
            count++;
            boolean offer;
            if (Main.queue.size() < MAX_QUEUE_LENGTH) {
                countWork++;
                offer = Main.queue.offer(random.nextInt(100));
                System.out.println("Producer offer number: " + offer +
                        " Thread count: " + count +
                        " Producer count: " + countWork +
                        " Queue size: " + Main.queue.size());
            }
        }
    }
}
