package lvluming.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * @author lvluming
 * @date 2017/8/6 00:04
 */
public class JsonUtil {

    private static final GsonBuilder GSON_BUILDER = new GsonBuilder();
    private static final Gson DEFAULT_GSON = GSON_BUILDER.create();

    public static <T> String toJson(T t) {
        return DEFAULT_GSON.toJson(t);
    }

    public static <T> String toPrettyJson(T t) {
        return GSON_BUILDER.setPrettyPrinting().create().toJson(t);
    }

    public static String formatJsonString(String source) {
        return GSON_BUILDER.setPrettyPrinting().create().toJson(new JsonParser().parse(source));
    }

    public static <T> T fromJson(String json) {
        return DEFAULT_GSON.fromJson(json, new TypeToken<T>(){}.getType());
    }

}
