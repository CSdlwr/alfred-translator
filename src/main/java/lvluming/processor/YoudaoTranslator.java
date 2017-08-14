package lvluming.processor;

import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import lvluming.common.Handler;
import lvluming.common.Request;
import lvluming.common.Response;
import lvluming.model.YoudaoApiResponse;
import lvluming.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author lvluming
 * @date 2017/8/6 20:04
 */
public class YoudaoTranslator implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(YoudaoTranslator.class);

    @Value("${youdao.api.url}")
    private String apiUrl;

    @Override
    public void handle(Request request, Response response) {

        long s = System.currentTimeMillis();

        String query = request.getQuery();
        try {
            YoudaoApiResponse apiResponse = callApi(query);
            request.getContext().setAttribute("youdaoApiResponse", apiResponse);
            LOGGER.info("youdao api cost: {}, content = {}",
                    System.currentTimeMillis() - s, apiResponse);
        } catch (UnirestException e) {
            LOGGER.error("youdao translator call api error, query = {}", query,
                    e);
            request.getContext().confirmAbort();
            response.setResult(StringUtils.EMPTY);
        }

    }

    YoudaoApiResponse callApi(String query) throws UnirestException {

        Unirest.setObjectMapper(new ObjectMapper() {
            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                //                    return JsonUtil.getDefaultObjectMapper().readValue(value, valueType);
                return JsonUtil.fromJson(value, valueType);
            }

            @Override
            public String writeValue(Object value) {
                return JsonUtil.toJson(value);
                //                    return JsonUtil.getDefaultObjectMapper().writeValueAsString(value);
            }
        });

        HttpRequest getRequest = Unirest.get(apiUrl).queryString("q", query);
        String url = getRequest.getUrl();

        LOGGER.info("api url = [{}]", url);
        return getRequest.asObject(YoudaoApiResponse.class).getBody();
    }
}
