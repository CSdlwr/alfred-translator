package lvluming.common;

/**
 * @author lvluming
 * @date 2017/8/6 21:14
 */
public interface Handler {

    void handle(Request request, Response response);

    boolean shouldAbort();

    default void smartHandle(Request request, Response response) {
        if (!shouldAbort()) {
            handle(request, response);
        }
    }
}
