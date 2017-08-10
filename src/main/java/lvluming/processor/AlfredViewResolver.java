package lvluming.processor;

import lvluming.common.Handler;
import lvluming.common.Request;
import lvluming.common.Response;
import lvluming.model.AlfredViewModel;
import lvluming.model.YoudaoApiResponse;

/**
 * @author lvluming
 * @date 2017/8/6 00:07
 */
public class AlfredViewResolver implements Handler {

//    public void handle() {
//        Context context = getContext();
//        YoudaoApiResponse apiResponse = context.getAttribute("");
//
//        List<AlfredViewModel.Item> items = Lists.newArrayList();
//
//        items.add(createPhonetic(apiResponse));
//        items.add(createYoudaoExpainItem(apiResponse));
//
//        for (String webItem : apiResponse.formatWebTranslations()) {
//            items.add(createWebTranslationItem(webItem));
//        }
//    }

    private Parser parser;

    private Viewer viewer;

    private AlfredViewModel.Item createWebTranslationItem(String webItem) {
        return AlfredViewModel.ItemBuilder.create().setTitle(webItem).setSubtitle("Web translation").build();
    }

    private AlfredViewModel.Item createYoudaoExpainItem(
            YoudaoApiResponse apiResponse) {
        return AlfredViewModel.ItemBuilder.create().setTitle(apiResponse.formatBasicExplains()).setSubtitle("Youdao dict").build();
    }

    private AlfredViewModel.Item createPhonetic(YoudaoApiResponse apiResponse) {
        return AlfredViewModel.ItemBuilder.create().setTitle(apiResponse.formatPhonetic()).build();
    }

    @Override
    public void handle(Request request, Response response) {
        Object dataSource = request.getContext().getAttribute("youdaoApiResponse");
        AlfredViewModel viewModel = parser.parse(dataSource);
        String view = viewer.view(viewModel);
        response.setResult(view);
    }

    interface Parser {

        <T> AlfredViewModel parse(T t);
    }

    interface Viewer {

        String view(AlfredViewModel viewModel);
    }

}
