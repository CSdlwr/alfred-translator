package lvluming.processor;

import lvluming.common.Handler;
import lvluming.common.Request;
import lvluming.common.Response;
import lvluming.model.AlfredViewModel;

/**
 * @author lvluming
 * @date 2017/8/6 00:07
 */
public class AlfredViewResolver implements Handler {

    private Parser parser;

    private Viewer viewer;

    public AlfredViewResolver(Parser parser, Viewer viewer) {
        this.parser = parser;
        this.viewer = viewer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handle(Request request, Response response) {
        Object dataSource = request.getContext().getAttribute("youdaoApiResponse");
        AlfredViewModel viewModel = parser.parse(dataSource);
        String view = viewer.view(viewModel);
        response.setResult(view);
    }

    interface Parser<T> {

        AlfredViewModel parse(T t);
    }

    interface Viewer {

        String view(AlfredViewModel viewModel);
    }

}
