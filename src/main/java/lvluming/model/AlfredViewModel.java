package lvluming.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * @author lvluming
 * @date 2017/8/8 18:59
 */
@XStreamAlias("items")
public class AlfredViewModel {

    @XStreamImplicit
    private List<Item> items;

    public AlfredViewModel(List<Item> items) {
        this.items = items;
    }

    private static class Item {

        @XStreamAsAttribute
        private String uid;

        @XStreamAsAttribute
        private String arg;

        private String title;
        private String icon;
        private String subtitle;

        private Item(ItemBuilder builder) {
            this.uid =  builder.uid;
            this.arg = builder.arg;
            this.title = builder.title;
            this.icon = builder.icon;
            this.subtitle = builder.subtitle;
        }
    }

    static class ItemBuilder {

        private String uid;
        private String arg;
        private String title;
        private String icon;
        private String subtitle;

        private ItemBuilder() {
        }

        public static ItemBuilder create() {
            return new ItemBuilder();
        }

        public Item build() {
            return new Item(this);
        }

        public ItemBuilder setUid(String uid) {
            this.uid = uid;
            return this;
        }

        public ItemBuilder setArg(String arg) {
            this.arg = arg;
            return this;
        }

        public ItemBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ItemBuilder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public ItemBuilder setSubtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }
    }
}
