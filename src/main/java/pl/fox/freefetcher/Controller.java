package pl.fox.freefetcher;

import org.json.JSONObject;

import java.util.Scanner;

public class Controller {

    private static final String KANYE_URL = new APIBuilder().withHTTPS().withSite("api.kanye.rest").getFinalURL();
    private static final String SENTIM_URL = new APIBuilder().withHTTPS().withSite("sentim-api.herokuapp.com").withSubsite("api/").withSubsite("v1/").getFinalURL();
    private static final String KANYE_KEY = "quote";
    private static final String SENTIM_KEY = "text";

    public void run(){
        final Scanner get = new Scanner(System.in);

        System.out.println("> Input number of quotes to get [5 - 20]");
        int input = 0;
        while(input < 5 || input > 20){
            input = get.nextInt();
        }

        java.util.List<Quote> quotes = new java.util.ArrayList<>();

        for(int i = 0; i < input; i++){
            quotes.add(
                    Parser.makeQuote(
                            RequestHandler.post(SENTIM_URL, Parser.swapKeys(
                                    RequestHandler.fetch(KANYE_URL), KANYE_KEY, SENTIM_KEY).toString())));
        }


        System.out.println(getMostPolar(quotes).toString());
        System.out.println(getLeastPolar(quotes).toString());
    }

    private Quote getMostPolar(java.util.List<Quote> quotes){
        Quote highest = quotes.get(0);
        double polarity = -2;
        for(Quote q: quotes){
            if(q.getPolarity() > polarity){
                highest = q;
            }
        }
        return highest;
    }

    private Quote getLeastPolar(java.util.List<Quote> quotes){
        Quote lowest = quotes.get(0);
        double polarity = 2;
        for(Quote q: quotes){
            if(q.getPolarity() < polarity){
                lowest = q;
            }
        }
        return lowest;
    }


}
