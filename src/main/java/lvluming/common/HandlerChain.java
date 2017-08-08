package lvluming.common;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author lvluming
 * @date 2017/8/6 21:28
 */
public class HandlerChain implements Handler {

    private Context context;

    private List<Handler> handlers;

    private HandlerChain(Context context) {
        this.context = context;
    }

    public static Handler init(Context context, Handler... handlers) {
        if (handlers != null) {
            HandlerChain chain = new HandlerChain(context);
            chain.handlers = Lists.newArrayList(handlers);
            return chain;
        }
        return null;
    }


    @Override
    public void handle() {

    }

    @Override
    public Context getContext() {
        return context;
    }
}
