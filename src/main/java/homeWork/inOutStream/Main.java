package homeWork.inOutStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
        try {


            Path path = Paths.get("C:\\Users\\s4k47d\\Documents\\2020-02-18_17-46-11.mp4");
            System.out.println(path.toAbsolutePath());
//            readFile();
             copyFile("C:\\Users\\s4k47d\\Documents\\2020-02-18_17-46-11.mp4",
                   "C:\\Users\\s4k47d\\Documents\\2020-02-18_17-46-11_version2.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() throws IOException {
        try (FileInputStream fis = new FileInputStream("test.json");
             Reader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {

            String line = br.readLine();
            while (line != null){
                System.out.println(line);
                line = br.readLine();
            }
        }
    }

    private static void copyFile(String source, String destination) throws IOException {

        try(InputStream is = new FileInputStream(source);
        OutputStream os = new FileOutputStream(destination)){

            byte[] buffer = new byte[4096];
            int read = is.read(buffer);
            while (read != -1){
                os.write(buffer, 0, read);
                read = is.read(buffer);
            }
        }
    }
}

