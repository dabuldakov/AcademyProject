package threds.queueInOutFile;

import java.io.*;

class ThreadsInOutFile{

    private final Object mutex1 = new Object();
    private FileWriter fileWriter;

    ThreadsInOutFile(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    void writeInFile() {

        double number = 0;
        for (int i = 0; i <= 100; i++) {
            number = Math.sqrt(number + i) * i;
            String string = Thread.currentThread().getName() + " Writing data in File: " + i + ", number: " + number;
            try {
                synchronized (mutex1) {
                    fileWriter.write(string);
                    fileWriter.append('\n');
                    fileWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
