package lvluming.biz;

import com.google.common.collect.Maps;
import lvluming.common.Context;

import java.util.Map;

/**
 * @author lvluming
 * @date 2017/8/16 22:16
 */
public class BizContext implements Context {

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
}
