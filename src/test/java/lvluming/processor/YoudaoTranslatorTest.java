package lvluming.processor;

import lvluming.model.YoudaoApiResponse;
import lvluming.util.JsonUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * @author lvluming
 * @date 2017/8/9 21:58
 */
public class YoudaoTranslatorTest {

    @Test
    public void callApi() throws Exception {

        YoudaoTranslator underTest = new YoudaoTranslator();
//        String apiResponse = underTest.callApi("name");
        YoudaoApiResponse apiResponse = underTest.callApi("name");
//        System.out.println(JsonUtil.formatJsonString(apiResponse));

//        YoudaoApiResponse response = JsonUtil.fromJson(apiResponse, YoudaoApiResponse.class);
        System.out.println(JsonUtil.toPrettyJson(apiResponse));
    }

    @Test
    public void objectMapperTest() throws IOException {
        final String source = "{\"translation\":[\"的名字\"],\"basic\":{\"us-phonetic\":\"nem\",\"phonetic\":\"neɪm\",\"uk-phonetic\":\"neɪm\",\"explains\":[\"n. 名称，名字；姓名；名誉\",\"adj. 姓名的；据以取名的\",\"vt. 命名，任命；指定；称呼；提名；叫出\",\"n. (Name)人名；(日)滑(姓)；(英)内姆\"]},\"query\":\"name\",\"errorCode\":0,\"web\":[{\"value\":[\"姓名\",\"名称\",\"名字\"],\"key\":\"Name\"},{\"value\":[\"名字\",\"名\",\"用汉语拼音表示\"],\"key\":\"first name\"},{\"value\":[\"笔名\",\"笔名\",\"囚犯的号码\"],\"key\":\"pen name\"}]}";
        System.out.println(JsonUtil.formatJsonString(source));

        JsonUtil.getDefaultObjectMapper().readValue(source, YoudaoApiResponse.class);
    }


}