package pl.fox.freefetcher;

import java.util.Scanner;

public class Controller {

    private static final String KANYE_URL = "https://api.kanye.rest/";
    private static final String SENTIM_URL = "https://sentim-api.herokuapp.com/";

    private final Scanner get = new Scanner(System.in);

    public void run(){
        /*System.out.println("> Input number of quotes to get [5 - 20]");
        int input = 0;
        while(input < 5 || input > 20){
            input = get.nextInt();
        }*/

        try{
            System.out.println(Parser.parseJSON(Fetcher.fetch(KANYE_URL)));
        }catch (Parser.JSONParseException jpe){
            jpe.printStackTrace();
        }
    }


}
