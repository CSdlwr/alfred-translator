package lvluming.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.omg.CORBA.OBJ_ADAPTER;

import java.lang.reflect.Type;

/**
 * @author lvluming
 * @date 2017/8/6 00:04
 */
public class JsonUtil {

    private static final GsonBuilder GSON_BUILDER = new GsonBuilder();
    private static final Gson DEFAULT_GSON = GSON_BUILDER.create();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String toJson(T t) {
        return DEFAULT_GSON.toJson(t);
    }

    public static <T> String toPrettyJson(T t) {
        System.out.println(t.getClass());
        return GSON_BUILDER.setPrettyPrinting().create().toJson(t);
    }

    public static String formatJsonString(String source) {
        return GSON_BUILDER.setPrettyPrinting().create().toJson(new JsonParser().parse(source));
    }

    @Deprecated
    public static <T> T fromJson(String json) {
        return DEFAULT_GSON.fromJson(json, new TypeToken<T>(){}.getType());
    }

    public static <T> T fromJson(String json, Type type) {
        return DEFAULT_GSON.fromJson(json, type);
    }

    public static ObjectMapper getDefaultObjectMapper() {
        return OBJECT_MAPPER;
    }
}
