package lvluming.biz;

import lvluming.common.Response;

/**
 * @author lvluming
 * @date 2017/8/16 22:19
 */
public class BizResponse implements Response {

    private Object result;

    @Override
    public String getResult() {
        return result.toString();
    }

    @Override
    public void setResult(Object result) {
        this.result = result;
    }
}
