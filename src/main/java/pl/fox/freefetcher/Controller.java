package pl.fox.freefetcher;

import java.util.Scanner;

/**
 * @author Bartosz Chotkowski
 * Controller class handling input and managing data with outputs
 */
public class Controller {

    private static final String KANYE_URL = new APIBuilder().withHTTPS().withSite("api.kanye.rest").getFinalURL();
    private static final String SENTIM_URL = new APIBuilder().withHTTPS().withSite("sentim-api.herokuapp.com").withSubsite("api/").withSubsite("v1/").getFinalURL();
    private static final String KANYE_KEY = "quote";
    private static final String SENTIM_KEY = "text";

    /**
     * Main method used to run everything -> handles getting data,
     * sending requests, showing output to user
     */
    public void run(){
        final Scanner get = new Scanner(System.in);

        System.out.println("> Input number of quotes to get [5 - 20]");
        int number = 0;
        String input = "";
        while(number < 5 || number > 20){
            input = get.nextLine();

            if(isParseAble(input)){
                number = Integer.parseInt(input);
                if(number < 5 || number > 20){
                    System.out.println("Input a number between 5 and 20");
                }
            } else{
                System.out.println("Wrong input! Try again");
            }

        }

        java.util.List<Quote> quotes = new java.util.ArrayList<>();

        for(int i = 0; i < number; i++){
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

    /**
     * Get a Quote object that has the most polarity
     * @param quotes java.util.List of Quote objects
     * @return Quote that has the highest polarity
     */
    public static Quote getMostPolar(java.util.List<Quote> quotes){
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

    /**
     * Get a Quote object that has the least polarity
     * @param quotes java.util.List of Quote objects
     * @return Quote that has the least polarity
     */
    public static Quote getLeastPolar(java.util.List<Quote> quotes){
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

    /**
     * Counting polarity types (positive, neutral and negative)
     * @param quotes java.util.List of Quote objects
     * @return array of int containing values of positive, neutral and negative polarities count
     */
    public static int[] countPolarityTypes(java.util.List<Quote> quotes){
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

    /**
     * Check if string is parseable, so if it's a number
     * @param s String to check
     * @return boolean if string is a number or not
     */
    public static boolean isParseAble(String s){
        try{
            Integer.parseInt(s);
        }catch (Exception e){
            return false;
        }
        return true;
    }


}
