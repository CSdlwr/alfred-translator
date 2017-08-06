package lvluming.util;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author lvluming
 * @date 2017/8/6 00:04
 */
public class JsonUtil {

    private static final GsonBuilder GSON_BUILDER = new GsonBuilder();

    public static <T> String toJson(T t) {
        return GSON_BUILDER.create().toJson(t);
    }

    public static <T> T fromJson(String json) {
        return GSON_BUILDER.create().fromJson(json, new TypeToken<T>(){}.getType());
    }
}
