package lvluming.processor;

/**
 * @author lvluming
 * @date 2017/8/6 17:11
 */
@Deprecated
public interface Translator<T> {

    T translate(String query);
}
