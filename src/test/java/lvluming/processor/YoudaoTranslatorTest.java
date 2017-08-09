package lvluming.processor;

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
        System.out.println(JsonUtil.formatJsonString(underTest.callApi("name")));
    }

}