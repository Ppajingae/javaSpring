package com.yoong.javaspring2.singleton;

import com.yoong.javaspring2.AppConfig;
import com.yoong.javaspring2.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("Spring이 없는 순수한 DI 컨테이너")
    void singletonDiTest(){

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService);
        System.out.println("memberService2 = " + memberService2);

    }
}
