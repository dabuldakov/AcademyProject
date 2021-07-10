package threds;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {

        FileWriter writer = new FileWriter("C:\\Users\\s4k47d\\Documents\\ThreadsData.txt", true);

        ThreadsInOutFile threadsInOutFile = new ThreadsInOutFile(writer);
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        for (int i = 0; i < 50; i++) {
            executorService.submit(threadsInOutFile::writeInFile);
        }

        executorService.shutdown();

    }
}
