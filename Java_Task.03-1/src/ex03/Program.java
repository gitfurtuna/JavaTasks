package ex03;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {

    public static class Downloader implements Runnable {
        private final String url;
        private final int fileNumber;

        public Downloader(String url, int fileNumber) {
            this.url = url;
            this.fileNumber = fileNumber;
        }


        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start download file number " + fileNumber);
            try {
                downloadFile(url,"./result", fileNumber);
                System.out.println(Thread.currentThread().getName() + " finish download file number " + fileNumber);
            } catch (IOException e) {
                System.err.println("Failed to download file number " + fileNumber + ": " + e.getMessage());
            }
        }


        private void downloadFile(String fileURL, String targetDirectory, int fileNumber) throws IOException {
            try (InputStream in = new URL(fileURL).openStream()) {
                File directory = new File(targetDirectory);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                String fileExtension = getFileExtension(fileURL);
                File targetFile = new File(directory, "downloadedFile_" + fileNumber + fileExtension);
                Files.copy(in, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }

        private String getFileExtension(String fileURL) {
            int lastDotIndex = fileURL.lastIndexOf('.');
            if (lastDotIndex != -1 && lastDotIndex < fileURL.length() - 1) {
                return fileURL.substring(lastDotIndex);
            }
            return "";
        }
        }




    public static  void main(String[] args) {

        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.out.println("Instruction: java Program --threadsCount=<number>");
            return;
        }

        int threadsCount = Integer.valueOf(args[0].split("=")[1]);

        ExecutorService executor = Executors.newFixedThreadPool(threadsCount);

        try (BufferedReader reader = new BufferedReader(new FileReader("files_urls.txt"))) {
            String line;
            int fileCount = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length < 2) continue;
                String url = parts[1];
                executor.execute(new Downloader(url, fileCount));
                fileCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

}


