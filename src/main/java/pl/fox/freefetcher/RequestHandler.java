package pl.fox.freefetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.json.JSONObject;


/**
 * @author Bartosz Chotkowski
 * Class with fetch (GET) and POST method handlers
 */
public class RequestHandler {


    /**
     * Get data from URL using GET method
     * @param URL String URL of site to get data from
     * @return JSONObject made from fetched data
     */
    public static JSONObject fetch(String URL){
        String content = "";
        try {
            URLConnection connection = new URL(URL).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch (Exception ex ) {
            ex.printStackTrace();
        }
        return new JSONObject(content);
    }

    /**
     * Post data and get response
     * @param URL String URL of site to send data and get response from
     * @param JSONInput Input data as string to be converted to bytes to send as a POST request
     * @return new JSONObject containing data got as response
     */
    public static JSONObject post(String URL, String JSONInput){
        StringBuilder response = new StringBuilder();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            byte[] input = JSONInput.getBytes(StandardCharsets.UTF_8);

            os.write(input);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null){
                response.append(line.trim());
            }

            os.close();
            br.close();

        } catch(IOException ie){
            ie.printStackTrace();
        }

        return new JSONObject(response.toString());
    }

}
