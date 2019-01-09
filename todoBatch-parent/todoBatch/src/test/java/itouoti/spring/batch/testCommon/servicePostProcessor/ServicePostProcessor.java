package itouoti.spring.batch.testCommon.servicePostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * ServiceImplの@Transactional(propagation = Propagation.REQUIRES_NEW) </br>
 * を無効にする
 * @author itouoti
 */
public class ServicePostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(
            Object bean,
            String beanName) throws BeansException {

        // @Transactional(propagation = Propagation.REQUIRES_NEW)
        // を上書くため
        return ServiceImplList.serviceImplInstanceOf(bean);

    }

    @Override
    public Object postProcessAfterInitialization(
            Object bean,
            String beanName) throws BeansException {
        return bean;
    }

}
