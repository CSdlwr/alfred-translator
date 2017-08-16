package lvluming.biz;

import com.google.common.collect.ImmutableList;
import lvluming.common.Context;
import lvluming.common.Handler;
import lvluming.common.Request;
import lvluming.common.Response;
import lvluming.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author lvluming
 * @date 2017/8/15 22:36
 */
public class Biz {

    private static final Logger LOGGER = LoggerFactory.getLogger(Biz.class);

    private String query;

    private Context context;
    private Request request;
    private Response response;

    public static void initEnv() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    }

    public static Biz getBiz(String query) {
        return new Biz(query);
    }

    private Biz(String query) {
        this.query = query;
    }

    public void process() {

        long start = System.currentTimeMillis();

        context = new BizContext();
        request = new BizRequest(query, context);
        response = new BizResponse();

        List<Handler> handlers = ImmutableList.of(
                SpringContextHolder.getBean("translator"),
                SpringContextHolder.getBean("viewResolver")
        );

        handlers.forEach(h -> h.handle(request, response));
        System.out.println(response.getResult());
        LOGGER.info("translate [{}] total cost: {} ms", query, System.currentTimeMillis() - start);
    }
}
