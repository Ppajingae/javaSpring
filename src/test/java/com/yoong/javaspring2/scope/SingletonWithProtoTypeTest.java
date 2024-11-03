package com.yoong.javaspring2.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

public class SingletonWithProtoTypeTest {

    @Test
    void protoTypeFindTest(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);

        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
        protoTypeBean1.addCount();

        Assertions.assertThat(protoTypeBean1.getCount()).isEqualTo(1);

        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);
        protoTypeBean1.addCount();
        Assertions.assertThat(protoTypeBean2.getCount()).isEqualTo(1);


    }

    @Test
    void singletonClientUseProtoTypeTest(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, ProtoTypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);

        int count1 = clientBean1.logic();

        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);

        int count2 = clientBean2.logic();

        Assertions.assertThat(count2).isEqualTo(2);

    }

    @Scope
    @RequiredArgsConstructor
    @Configuration
    static class ClientBean{

        private Provider<ProtoTypeBean> protoTypeBeanProvider;

        public int logic(){
            ProtoTypeBean protoTypeBean = protoTypeBeanProvider.get();
            protoTypeBean.addCount();
            return protoTypeBean.getCount();
        }

    }


    @Getter
    @Scope("prototype")
    static class ProtoTypeBean{

        private int count = 0;

        public void addCount(){
            count++;
        }

        @PostConstruct
        public void init(){
            System.out.println("ProtoTypeBean.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("ProtoTypeBean.destroy" + this);
        }
    }
}
