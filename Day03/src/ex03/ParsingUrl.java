package ex03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ParsingUrl {
    private final Map<Integer, URL> urls = new HashMap<>();
    File file = new File("files_urls.txt");


    public void parsingUrl() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null) {
            String[] tmp = line.split(" ");
            line = reader.readLine();
            int key = Integer.parseInt(tmp[0]) ;
            URL url = new URL(tmp[1]);
            urls.put(key,url);
        }
    }

    public Map<Integer, URL> getUrls() {
        return urls;
    }

}


