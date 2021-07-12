package threds.queueWaitNotify;

class Main {
    public static void main(String[] args) {
        Fabric fabric = new Fabric();
        Thread threadProducer = new Thread(() -> {
            try {
                fabric.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadConsumer = new Thread(() -> {
            try {
                fabric.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadProducer.start();
        threadConsumer.start();
    }
}
