package lvluming.biz;

import lvluming.common.Context;
import lvluming.common.Request;

/**
 * @author lvluming
 * @date 2017/8/16 22:18
 */
public class BizRequest implements Request {

    private String query;
    private Context context;

    public BizRequest(String query, Context context) {
        this.query = query;
        this.context = context;
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public Context getContext() {
        return context;
    }
}
