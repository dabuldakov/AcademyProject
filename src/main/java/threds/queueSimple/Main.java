package threds.queueSimple;

import java.util.LinkedList;
import java.util.Queue;

class Main {

    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {

        Thread threadProducer = new Thread(new Producer());
        Thread threadConsumer = new Thread(new Consumer());

        threadProducer.start();
        threadConsumer.start();

    }
}
