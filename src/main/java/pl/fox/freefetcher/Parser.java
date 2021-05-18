package pl.fox.freefetcher;

import org.json.JSONObject;

/**
 * @author Bartosz Chotkowski
 * Class for parsing quotes and swapping keys of JSONObjects
 */
public class Parser {

    /**
     * Parse Quote from JSONObject
     * @param object Object with fetched data as JSON
     * @return Quote object containing same data, easier to access
     */
    public static Quote makeQuote(JSONObject object){
        JSONObject result = object.getJSONObject("result");

        return new Quote(Double.parseDouble(result.get("polarity").toString()), result.getString("type"),
                object.getJSONArray("sentences")
                .getJSONObject(0)
                .get("sentence")
                .toString());
    }

    /**
     * Make a new object with same value, but different key
     * @param object object containing desired value
     * @param key old key
     * @param newKey new key to swap
     * @return JSONObject with new key and old value
     */
    public static JSONObject swapKeys(JSONObject object, String key, String newKey){
        return new JSONObject().put(newKey, object.get(key));
    }

}
