package threds.queueSimple;

class Consumer implements Runnable {

    private int count;
    private int countWork;

    @Override
    public void run() {
        while (true) {
            count++;
            Integer poll = Main.queue.poll();
            if (poll != null) {
                countWork++;
                System.out.println("Consumer poll number: " + poll +
                        " Thread count: " + count +
                        " Consumer count: " + countWork +
                        " Queue size: " + Main.queue.size());
            }
        }
    }
}
