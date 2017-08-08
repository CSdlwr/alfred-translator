package lvluming.model;

/**
 * @author lvluming
 * @date 2017/8/5 23:57
 */
public class YoudaoApiResponse {

    private int errorCode;
    private String query;
    private String[] translation;
    private Basic basic;
    private Pair[] web;

    static class Basic {

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
    }

    static class Pair {
        private String key;
        private String[] value;
    }
}
