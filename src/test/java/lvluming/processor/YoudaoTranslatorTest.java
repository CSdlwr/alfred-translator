package lvluming.processor;

import lvluming.model.YoudaoApiResponse;
import lvluming.util.JsonUtil;
import org.junit.Test;

/**
 * @author lvluming
 * @date 2017/8/9 21:58
 */
public class YoudaoTranslatorTest {

    @Test
    public void callApi() throws Exception {

        YoudaoTranslator underTest = new YoudaoTranslator();
        String apiResponse = underTest.callApi("name");
        System.out.println(JsonUtil.formatJsonString(apiResponse));

        YoudaoApiResponse response = JsonUtil.fromJson(apiResponse, YoudaoApiResponse.class);
        System.out.println(JsonUtil.toPrettyJson(response));
    }

}