package pl.fox.freefetcher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bartosz Chotkowski
 * Class implemented to make api addresses making simpler
 */
public class APIBuilder {

    private String site;
    private String protocol;
    private final List<String> subsites = new ArrayList<>();
    private final Map<String, String> args = new LinkedHashMap<>();

    public APIBuilder withHTTP() {
        this.protocol = "http://";
        return this;
    }

    public APIBuilder withHTTPS() {
        this.protocol = "https://";
        return this;
    }

    public APIBuilder withSite(String site) {
        this.site = site;
        return this;
    }

    public APIBuilder withSubsite(String subsite){
        subsites.add(subsite);
        return this;
    }

    public APIBuilder withParam(String param, String value) {
        args.put(args.size() == 0 ? "?" + param : "&" + param,  "=" + value);
        return this;
    }

    public APIBuilder withCleanParam(String param){
        args.put(param, "");
        return this;
    }

    public String getFinalURL() {
        StringBuilder sb = new StringBuilder();
        sb.append(protocol).append(site).append("/");

        if(subsites.size() != 0){
            subsites.forEach(sb::append);
        }

        if(args.size() != 0){
            args.keySet().forEach(param -> sb.append(param).append(args.get(param)));
        }
        return sb.toString();
    }
}
