package lvluming.processor;

/**
 * @author lvluming
 * @date 2017/8/6 00:06
 */
public class SimpleTranslator implements Translator<String> {

    @Override
    public String translate(String query) {
        return query;
    }
}
