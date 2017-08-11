package lvluming.processor;

import com.google.common.collect.Lists;
import lvluming.model.AlfredViewModel;
import lvluming.model.YoudaoApiResponse;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lvluming
 * @date 2017/8/11 21:05
 */
public class YoudaoTranslationParser implements AlfredViewResolver.Parser<YoudaoApiResponse> {

    @Override
    public AlfredViewModel parse(YoudaoApiResponse youdaoApiResponse) {

        List<AlfredViewModel.Item> viewItems = Lists.newArrayList();
        viewItems.add(createPhonetic(youdaoApiResponse));
        viewItems.add(createYoudaoDictExplain(youdaoApiResponse));
        viewItems.addAll(createWebTranslations(youdaoApiResponse));

        return new AlfredViewModel(viewItems);
    }

    private Collection<AlfredViewModel.Item> createWebTranslations(YoudaoApiResponse youdaoApiResponse) {

        String[] webTranslations = youdaoApiResponse.formatWebTranslations();
        return Arrays.stream(webTranslations)
                .map(this::createWebItem)
                .collect(Collectors.toList());
    }

    private AlfredViewModel.Item createWebItem(String webTranslation) {
        return AlfredViewModel.ItemBuilder.create().setTitle(webTranslation).setSubtitle("Web Translation").build();
    }

    private AlfredViewModel.Item createYoudaoDictExplain(YoudaoApiResponse youdaoApiResponse) {
        return AlfredViewModel.ItemBuilder.create().setTitle(youdaoApiResponse.formatBasicExplains()).setSubtitle("Youdao Dict").build();
    }

    private AlfredViewModel.Item createPhonetic(YoudaoApiResponse youdaoApiResponse) {
        return AlfredViewModel.ItemBuilder.create().setTitle(youdaoApiResponse.formatPhonetic()).build();
    }
}
