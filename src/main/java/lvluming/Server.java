package lvluming;

import com.google.common.base.Joiner;
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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.Map;

/**
 * @author lvluming
 * @date 2017/8/6 17:07
 */
public class Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    private static final Joiner PARAMETER_JOINER = Joiner.on(StringUtils.SPACE).skipNulls();

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        BeanFactory xmlBeanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println(xmlBeanFactory);

        LOGGER.info("server entry time: {}", start);

        if (ArrayUtils.isEmpty(args)) {
            return;
        }

        String query = PARAMETER_JOINER.join(args);

        Context ctx = buildContext();
        Request request = buildRequest(query, ctx);
        Response response = buildResponse();

        List<Handler> handlers = Lists.newArrayList(
                new YoudaoTranslator(),
                new AlfredViewResolver(new YoudaoTranslationParser(), new SimpleXmlViewer())
        );

        handlers.forEach(h -> h.handle(request, response));
        System.out.println(response.getResult());

        LOGGER.info("translate [{}] total cost: {}ms", query, System.currentTimeMillis() - start);
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
