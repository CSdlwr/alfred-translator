package lvluming.processor;

import lvluming.common.Handler;
import lvluming.common.Request;
import lvluming.common.Response;
import lvluming.model.AlfredViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lvluming
 * @date 2017/8/6 00:07
 */
public class AlfredViewResolver implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlfredViewResolver.class);

    private Parser parser;

    private Viewer viewer;

    public AlfredViewResolver(Parser parser, Viewer viewer) {
        this.parser = parser;
        this.viewer = viewer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handle(Request request, Response response) {

        long s = System.currentTimeMillis();

        Object source = request.getContext().getAttribute("youdaoApiResponse");
        AlfredViewModel viewModel = parser.parse(source);
        String view = viewer.view(viewModel);
        response.setResult(view);

        LOGGER.info("viewResolver cost: {}", System.currentTimeMillis() - s);
    }

    interface Parser<T> {

        AlfredViewModel parse(T t);
    }

    interface Viewer {

        String view(AlfredViewModel viewModel);
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }
}
