package com.yoong.javaspring2.beanfind;

import com.yoong.javaspring2.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void getBeans(){
        String[] beans = ac.getBeanDefinitionNames();

        for(String bean : beans){

            Object beanInfo = ac.getBean(bean);

            System.out.println("bean : " + bean);
            System.out.println("beanInfo : " + beanInfo);
        }
    }

    @Test
    @DisplayName("application 빈 출력")
    void findApplicationBeans(){
        String[] beans = ac.getBeanDefinitionNames();

        for(String bean : beans){

            BeanDefinition beanDefinition = ac.getBeanDefinition(bean);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("bean : " + bean);
            }
        }
    }
}
