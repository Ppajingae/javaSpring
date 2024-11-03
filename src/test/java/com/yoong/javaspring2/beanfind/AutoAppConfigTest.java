package com.yoong.javaspring2.beanfind;

import com.yoong.javaspring2.AutoAppConfig;
import com.yoong.javaspring2.member.service.MemberService;
import com.yoong.javaspring2.member.service.MemberServiceImpl;
import com.yoong.javaspring2.order.service.OrderService;
import com.yoong.javaspring2.order.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void autoAppConfig(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderService orderService = ac.getBean("orderService", OrderServiceImpl.class);

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
}
