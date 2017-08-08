package lvluming.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import lvluming.model.AlfredViewModel;

/**
 * @author lvluming
 * @date 2017/8/8 19:22
 */
public class XmlUtil {

    private static XStream X_STREAM = new XStream(new Xpp3Driver() {
    });

    static {
        X_STREAM.processAnnotations(AlfredViewModel.class);
    }

    public static <T> String toXml(T object) {
        return X_STREAM.toXML(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXml(String xml) {
        return (T) X_STREAM.fromXML(xml);
    }
}
