package com.epam.java.training.spring_boot.task1.beanpostprocessor;

import com.epam.java.training.spring_boot.task1.annotation.InjectRandomString;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Random;

@Component
public class InjectRandomStringFromPredefinedListBeanPostProcessor implements BeanPostProcessor {

    private Random random = new Random();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomString annotation = field.getAnnotation(InjectRandomString.class);
            //Have annotation check
            if (annotation != null) {
                if (!field.getType().equals(String.class))
                    throw new RuntimeException("don't put @InjectRandomString above " + field.getType());
                //Field is not final
                if (Modifier.isFinal(field.getModifiers())) {
                    throw new RuntimeException("can't inject to final fields");
                }
                String[] arr = annotation.randomString();
                //random count from list String
                int randomNumber = random.nextInt(arr.length);
                String randomString = arr[randomNumber];
                try {
                    field.setAccessible(true);
                    field.set(bean, randomString);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        return bean;
    }
}
