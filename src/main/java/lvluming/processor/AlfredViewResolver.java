package lvluming.processor;

import lvluming.common.Context;
import lvluming.common.Handler;
import lvluming.model.YoudaoApiResponse;

/**
 * @author lvluming
 * @date 2017/8/6 00:07
 */
public class AlfredViewResolver implements Handler {

    @Override
    public void handle() {
        Context context = getContext();
        YoudaoApiResponse apiResponse = context.getAttribute("");


    }

    @Override
    public Context getContext() {
        return null;
    }

    private static class YoudaoApiResponseParser {

    }
}
