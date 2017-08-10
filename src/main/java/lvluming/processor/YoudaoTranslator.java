package lvluming.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Throwables;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lvluming.common.Handler;
import lvluming.common.Request;
import lvluming.common.Response;
import lvluming.model.YoudaoApiResponse;
import lvluming.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author lvluming
 * @date 2017/8/6 20:04
 */
public class YoudaoTranslator implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(YoudaoTranslator.class);

    private static final String API_URL = "http://fanyi.youdao.com/openapi.do?keyfrom=alfred-llm-0858&key=425214878&type=data&doctype=json&version=1.1&";

    @Override
    public void handle(Request request, Response response) {
        String query = request.getQuery();
        try {
            YoudaoApiResponse apiResponse = callApi(query);
            request.getContext().setAttribute("youdaoApiResponse", apiResponse);
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
                try {
                    return JsonUtil.getDefaultObjectMapper().readValue(value, valueType);
                } catch (IOException e) {
                    LOGGER.error("call api error.", e);
                    throw Throwables.propagate(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return JsonUtil.getDefaultObjectMapper().writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    LOGGER.error("call api error.", e);
                    throw Throwables.propagate(e);
                }
            }
        });

        return Unirest.get(API_URL)
                .queryString("q", query)
                .asObject(YoudaoApiResponse.class)
                .getBody();
    }
}
