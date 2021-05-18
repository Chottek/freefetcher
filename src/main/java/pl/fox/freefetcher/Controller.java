package pl.fox.freefetcher;

import java.util.Scanner;

public class Controller {

    private static final String KANYE_URL = new APIBuilder().withHTTPS().withSite("api.kanye.rest").getFinalURL();
    private static final String SENTIM_URL = new APIBuilder().withHTTPS().withSite("sentim-api.herokuapp.com").withSubsite("api/").withSubsite("v1/").getFinalURL();

    private final Scanner get = new Scanner(System.in);

    public void run(){
        /*System.out.println("> Input number of quotes to get [5 - 20]");
        int input = 0;
        while(input < 5 || input > 20){
            input = get.nextInt();
        }*/
        try{
            String requestJSON = Parser.makeJSON(Parser.parseJSON(RequestHandler.fetch(KANYE_URL)));
            System.out.println(RequestHandler.post(SENTIM_URL, requestJSON));


        }catch (Parser.JSONParseException | Parser.MapParseException pe){
            pe.printStackTrace();
        }
    }


}
