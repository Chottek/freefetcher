package pl.fox.freefetcher;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, String> parseJSON(String input) throws JSONParseException {
        if(input.charAt(0) != '{' && input.charAt(input.length() - 1) != '}'){
            throw new JSONParseException("Input isn't a valid JSON");
        }

        Map<String, String> jsons = new HashMap<>();
        StringBuilder sb = new StringBuilder(input);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);
        String[] madeInput = sb.toString().replaceAll("\"", "").split(":");

        if(madeInput.length < 2){
            throw new JSONParseException("Error while splitting key and values -> invalid length of key - values");
        }

        jsons.put(madeInput[0], madeInput[1]);

        return jsons;
    }




    static class JSONParseException extends Exception {
        public JSONParseException(String msg) {
            super(msg);
        }
    }
}
