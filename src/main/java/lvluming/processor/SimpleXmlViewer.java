package lvluming.processor;

import lvluming.model.AlfredViewModel;
import lvluming.util.XmlUtil;

/**
 * @author lvluming
 * @date 2017/8/11 21:27
 */
public class SimpleXmlViewer implements AlfredViewResolver.Viewer {

    @Override
    public String view(AlfredViewModel viewModel) {
        return XmlUtil.toXml(viewModel);
    }
}
