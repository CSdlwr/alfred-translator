package lvluming.model;

import java.util.List;

/**
 * @author lvluming
 * @date 2017/8/8 18:59
 */
public class AlfredViewModel {

    private List<Item> items;

    static class Item {

        private String uid;
        private String arg;

        private String title;
        private String icon;
        private String subtitle;

    }
}
