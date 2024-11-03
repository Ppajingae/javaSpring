package com.yoong.javaspring2.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StateFulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        stateFulService1.order("kim", 1000);
        stateFulService2.order("lee", 2000);

        int price = stateFulService1.getPrice();

        Assertions.assertThat(stateFulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StateFulService stateFulService(){
            return new StateFulService();
        }
    }

}