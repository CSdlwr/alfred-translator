package lvluming.model;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author lvluming
 * @date 2017/8/5 23:57
 */
public class YoudaoApiResponse {

    private static final Joiner SEMICOLON_JOINER = Joiner.on("; ").skipNulls();
    private static final Joiner COLON_JOINER = Joiner.on(": ").skipNulls();

    private int errorCode;
    private String query;
    private String[] translation;
    private Basic basic;
    private Pair[] web;

    public String formatTranslation() {
        return SEMICOLON_JOINER.join(translation);
    }

    public Basic getBasic() {
        return basic;
    }

    public String formatPhonetic() {
        if (basic == null) {
            return "null";
        }
        return basic.getPhonetic();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("query", query)
                .add("translation", translation)
                .add("basic", basic)
                .add("web", web).toString();
    }

    public String formatBasicExplains() {
        Basic basic;
        String[] explains;

        if ((basic = getBasic()) == null
                || (explains = basic.getExplains()) == null) {
            return "null";
        }
        return basic.formatExplains();
    }

    public Pair[] getWeb() {
        return web;
    }

    public String[] formatWebTranslations() {
        if (this.web == null) {
            return new String[0];
        }
        return Arrays.stream(web)
                .map(Pair::toString)
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

    public static class Basic {

        private String phonetic;
        private String ukPhonetic;
        private String usPhonetic;
        private String[] explains;

        public String getPhonetic() {
            return phonetic;
        }

        public String[] getExplains() {
            return explains;
        }

        public String formatExplains() {
            return SEMICOLON_JOINER.join(explains);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("phonetic", phonetic)
                    .add("ukPhonetic", ukPhonetic)
                    .add("usPhonetic", usPhonetic)
                    .add("explains", explains).toString();
        }
    }

    public static class Pair {

        private String key;
        private String[] value;

        @Override
        public String toString() {
            return COLON_JOINER.join(key, SEMICOLON_JOINER.join(value));
        }
    }

}
