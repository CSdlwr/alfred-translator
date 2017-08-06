package lvluming.processor;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lvluming.model.YoudaoApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lvluming
 * @date 2017/8/6 20:04
 */
public class YoudaoTranslator implements Translator<YoudaoApiResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(YoudaoTranslator.class);

    private static final String API_URL = "http://fanyi.youdao.com/openapi.do?keyfrom=alfred-llm-0858&key=425214878&type=data&doctype=json&version=1.1&";

    @Override
    public YoudaoApiResponse translate(String query) {
        try {
            HttpResponse<YoudaoApiResponse> response = Unirest.get(API_URL)
                    .queryString("q", query)
                    .asObject(YoudaoApiResponse.class);
            return response.getBody();
        } catch (UnirestException e) {
            LOGGER.error("youdao translate error", e);
        }
        return null;
    }
}
