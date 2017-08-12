package lvluming.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author lvluming
 * @date 2017/8/12 23:10
 */
public class BeanFactoryHolder implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanFactoryHolder.beanFactory = beanFactory;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) beanFactory.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> type) {
        return beanFactory.getBean(beanName, type);
    }
}
