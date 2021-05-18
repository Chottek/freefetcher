package pl.fox.freefetcher;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Fetcher {

    public static String fetch(String URL){
        String content = null;
        try {
            URLConnection connection = new URL(URL).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch (Exception ex ) {
            ex.printStackTrace();
        }
        return content;
    }

}
