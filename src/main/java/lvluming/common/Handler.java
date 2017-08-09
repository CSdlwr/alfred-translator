package lvluming.common;

/**
 * @author lvluming
 * @date 2017/8/6 21:14
 */
public interface Handler {

    void handle(Request request, Response response);

    default boolean shouldAbort(Request request) {
        return request.getContext().shouldAbort();
    }

    default void smartHandle(Request request, Response response) {
        if (!shouldAbort(request)) {
            handle(request, response);
        }
    }
}
