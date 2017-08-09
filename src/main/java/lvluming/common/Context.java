package lvluming.common;

/**
 * @author lvluming
 * @date 2017/8/6 21:35
 */
public interface Context {

    @Deprecated
    void setAttribute(Object key, Object value);

    <T> T getAttribute(Object key);

    boolean shouldAbort();
}
