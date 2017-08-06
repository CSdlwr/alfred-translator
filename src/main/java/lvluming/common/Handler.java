package lvluming.common;

/**
 * @author lvluming
 * @date 2017/8/6 21:14
 */
public interface Handler<INPUT, OUTPUT> {

    OUTPUT handle(INPUT input);

    Context getContext();
}
