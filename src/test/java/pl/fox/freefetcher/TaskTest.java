package pl.fox.freefetcher;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testMostPolarGetter(){
        List<Quote> quotes = IntStream.range(0, 5)
                .mapToObj(i -> new Quote(i, null, "Polarity here is " + i)).collect(Collectors.toList());

        assertTrue(Controller.getMostPolar(quotes)
                .getSentence().equals("Polarity here is 4"));
    }

    @Test
    public void testLeastPolarGetter(){
        List<Quote> quotes = IntStream.range(0, 5).mapToObj(i -> new Quote(i, null, "Polarity here is " + i)).collect(Collectors.toList());

        assertTrue(Controller.getLeastPolar(quotes)
                .getSentence().equals("Polarity here is 0"));

    }

    @Test
    public void testKeySwapper(){
        final String key = "Good";
        final String newKey = "Bad";
        final JSONObject object = new JSONObject().put("Good", "Day");

        assertTrue(Parser.swapKeys(object, key, newKey).keySet().iterator().next().equals(newKey));
    }

}
