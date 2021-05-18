package pl.fox.freefetcher;

public class Quote {

    private double polarity;
    private String type;
    private String sentence;

    public Quote(double polarity, String type, String sentence) {
        this.polarity = polarity;
        this.type = type;
        this.sentence = sentence;
    }

    public double getPolarity() {
        return polarity;
    }

    public void setPolarity(double polarity) {
        this.polarity = polarity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
