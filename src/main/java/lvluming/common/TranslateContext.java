package lvluming.common;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author lvluming
 * @date 2017/8/6 21:14
 */
@Deprecated
public class TranslateContext implements Context {

    private Map<Object, Object> attributes = Maps.newHashMap();

    @Override
    public void setAttribute(Object key, Object value) {
        if (value != null) {
            attributes.put(key, value);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getAttribute(Object key) {
        return (T) attributes.get(key);
    }

    @Override
    public boolean shouldAbort() {
        return false;
    }

    @Override
    public void confirmAbort() {

    }
}
