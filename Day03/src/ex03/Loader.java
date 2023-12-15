package ex03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    ParserArguments parserArguments;
    ParsingUrl parsingUrl;
    Map<Integer, URL> urls;

    public Loader(String[] args) {
        parserArguments = new ParserArguments(args);
        parserArguments.parsingArguments();
        parsingUrl = new ParsingUrl();
        try {
            parsingUrl.parsingUrl();
            urls = parsingUrl.getUrls();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void fileDownloader() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(parserArguments.getCountThreads());
        for (Map.Entry<Integer, URL> entry : urls.entrySet()) {
            int threadId = entry.getKey();
            URL url = entry.getValue();

            executorService.execute(() -> {
                String numberThread = Thread.currentThread().getName();
                int index = numberThread.lastIndexOf("-");
                int number = Integer.parseInt(numberThread.substring(index + 1));

                Thread.currentThread().setName("Thread-" + number);

                System.out.println(Thread.currentThread().getName() + " start download file number " + threadId);
                String fileName = entry.getValue().toString().substring(entry.getValue().toString().lastIndexOf("/") + 1);
                downloadFile(url, fileName);
                System.out.println(Thread.currentThread().getName() + " finish download file number " + threadId);
            });
        }
        executorService.shutdown();
    }

    private static void downloadFile(URL url, String fileName) {
        try {
            URLConnection connection = url.openConnection();
            try (InputStream inputStream = connection.getInputStream()) {
                Path targetPath = new File(fileName).toPath();
                Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + e.getMessage() + " not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
