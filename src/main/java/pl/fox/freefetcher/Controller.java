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
            Quote q = Parser.makeQuote(
                    RequestHandler.post(SENTIM_URL, Parser.swapKeys(
                            RequestHandler.fetch(KANYE_URL), KANYE_KEY, SENTIM_KEY).toString()));
            System.out.println(q.toString());
            quotes.add(q);
        }


        System.out.println("\nMost Polar: \n" + getMostPolar(quotes).toString());
        System.out.println("\nLeast Polar: \n" + getLeastPolar(quotes).toString());

        int[] polarityTypes = countPolarityTypes(quotes);

        System.out.println("\nPolarity types count: \n" + "positive: " + polarityTypes[0] + "\nneutral: " + polarityTypes[1] + "\nnegative: " + polarityTypes[2]);
    }

    private Quote getMostPolar(java.util.List<Quote> quotes){
        Quote highest = quotes.get(0);
        double polarity = -2;
        for(Quote q: quotes){
            if(q.getPolarity() > polarity){
                highest = q;
                polarity = q.getPolarity();
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
                polarity = q.getPolarity();
            }
        }
        return lowest;
    }

    private int[] countPolarityTypes(java.util.List<Quote> quotes){
        int positive = 0;
        int neutral = 0;
        int negative = 0;

        for(Quote q : quotes){
            switch(q.getType()){
                case "positive": positive++; break;
                case "neutral": neutral++; break;
                case "negative": negative++; break;
            }
        }

        return new int[]{positive, neutral, negative};
    }


}
