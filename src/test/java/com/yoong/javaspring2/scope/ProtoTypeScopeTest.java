package com.yoong.javaspring2.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class ProtoTypeScopeTest {

    @Test
    void protoTypeBeanFind(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);

        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);

        Assertions.assertThat(protoTypeBean1).isNotSameAs(protoTypeBean2);
        
        ac.close();
    }

    @Scope("singleton")
    static class ProtoTypeBean{

        @PostConstruct
        public void init(){
            System.out.println("singletonBean init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("singletonBean destroy");
        }
    }
}
