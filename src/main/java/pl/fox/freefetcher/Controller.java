package pl.fox.freefetcher;

import java.util.Scanner;

public class Controller {

    private static final String KANYE_URL = "https://api.kanye.rest/";
    private static final String SENTIM_URL = "https://sentim-api.herokuapp.com/";

    private final Scanner get = new Scanner(System.in);

    public void run(){
        int input = get.nextInt();
        System.out.println(Fetcher.fetch(KANYE_URL));



    }


}
