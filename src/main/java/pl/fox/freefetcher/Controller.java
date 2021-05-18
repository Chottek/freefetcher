package pl.fox.freefetcher;

import org.json.JSONObject;

import java.util.Scanner;

public class Controller {

    private static final String KANYE_URL = new APIBuilder().withHTTPS().withSite("api.kanye.rest").getFinalURL();
    private static final String SENTIM_URL = new APIBuilder().withHTTPS().withSite("sentim-api.herokuapp.com").withSubsite("api/").withSubsite("v1/").getFinalURL();
    private static final String KANYE_KEY = "quote";
    private static final String SENTIM_KEY = "text";

    private final Scanner get = new Scanner(System.in);

    public void run(){
        /*System.out.println("> Input number of quotes to get [5 - 20]");
        int input = 0;
        while(input < 5 || input > 20){
            input = get.nextInt();
        }*/

        JSONObject kanyeQuote = RequestHandler.fetch(KANYE_URL);
        JSONObject postJSON = Parser.swapKeys(kanyeQuote, KANYE_KEY, SENTIM_KEY);
        JSONObject response = RequestHandler.post(SENTIM_URL, postJSON.toString());
        System.out.println(Parser.makeQuote(response).toString());


    }


}
