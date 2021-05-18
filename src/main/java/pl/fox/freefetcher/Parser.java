package pl.fox.freefetcher;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, String> parseJSON(String input) throws JSONParseException {
        if(input.charAt(0) != '{' && input.charAt(input.length() - 1) != '}'){
            throw new JSONParseException();
        }

        Map<String, String> s = new HashMap<>();



        return s;
    }




    static class JSONParseException extends Exception {
        public JSONParseException() {
            super("Input isn't a valid JSON!");
        }
    }
}
