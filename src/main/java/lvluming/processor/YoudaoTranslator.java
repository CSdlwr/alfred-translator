package lvluming.processor;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author lvluming
 * @date 2017/8/6 00:06
 */
public class YoudaoTranslator implements Translator<String> {

    private static final String API_URL = "http://fanyi.youdao.com/openapi.do?keyfrom=alfred-llm-0858&key=425214878&type=data&doctype=json&version=1.1&";

    public String translate(String query) {
        try {
            HttpResponse<String> response = Unirest.get(API_URL)
                    .queryString("q", query)
                    .asString();
            return response.getBody();
        } catch (UnirestException e) {
            return null;
        }
    }
}
