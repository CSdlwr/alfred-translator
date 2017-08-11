package lvluming;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lvluming.common.Context;
import lvluming.common.Handler;
import lvluming.common.Request;
import lvluming.common.Response;
import lvluming.processor.AlfredViewResolver;
import lvluming.processor.SimpleXmlViewer;
import lvluming.processor.YoudaoTranslationParser;
import lvluming.processor.YoudaoTranslator;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;
import java.util.Map;

/**
 * @author lvluming
 * @date 2017/8/6 17:07
 */
public class Server {

    public static void main(String[] args) {

        if (ArrayUtils.isEmpty(args)) {
            return;
        }

        String query = args[0];

        Context ctx = buildContext();
        Request request = buildRequest(query, ctx);
        Response response = buildResponse();

        List<Handler> handlers = Lists.newArrayList(
                new YoudaoTranslator(),
                new AlfredViewResolver(new YoudaoTranslationParser(), new SimpleXmlViewer())
        );

        handlers.forEach(h -> h.handle(request, response));
        System.out.println(response.getResult());
    }

    private static Response buildResponse() {
        return new Response() {

            private String result;

            @Override
            public String getResult() {
                return result;
            }

            @Override
            public void setResult(String result) {
                this.result = result;
            }
        };
    }

    private static Request buildRequest(String query, Context ctx) {
        return new Request() {

            @Override
            public String getQuery() {
                return query;
            }

            @Override
            public Context getContext() {
                return ctx;
            }
        };
    }

    private static Context buildContext() {
        return new Context() {

            private Map<Object, Object> attributes = Maps.newHashMap();
            private boolean shouldAbort;

            @Override
            public void setAttribute(Object key, Object value) {
                attributes.put(key, value);
            }

            @Override
            @SuppressWarnings("unchecked")
            public <T> T getAttribute(Object key) {
                return (T) attributes.get(key);
            }

            @Override
            public boolean shouldAbort() {
                return shouldAbort;
            }

            @Override
            public void confirmAbort() {
                shouldAbort = true;
            }
        };
    }
}
