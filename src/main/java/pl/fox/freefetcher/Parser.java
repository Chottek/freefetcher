package pl.fox.freefetcher;

import org.json.JSONObject;

public class Parser {

    public static Quote makeQuote(JSONObject object){
        JSONObject result = object.getJSONObject("result");

        return new Quote(Double.parseDouble(result.get("polarity").toString()), result.getString("type"),
                object.getJSONArray("sentences")
                .getJSONObject(0)
                .get("sentence")
                .toString());
    }

    public static JSONObject swapKeys(JSONObject object, String key, String newKey){
        return new JSONObject().put(newKey, object.get(key));
    }

}
