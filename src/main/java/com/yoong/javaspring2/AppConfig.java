package com.yoong.javaspring2;

import com.yoong.javaspring2.discount.DiscountPolicy;
import com.yoong.javaspring2.discount.FixDiscountPolicy;
import com.yoong.javaspring2.member.repository.MemberRepository;
import com.yoong.javaspring2.member.repository.MemoryMemberRepository;
import com.yoong.javaspring2.member.service.MemberService;
import com.yoong.javaspring2.member.service.MemberServiceImpl;
import com.yoong.javaspring2.order.service.OrderService;
import com.yoong.javaspring2.order.service.OrderServiceImpl;

public class AppConfig {

    public MemberRepository memberRepository() {

        return new MemoryMemberRepository();
    }


    public DiscountPolicy discountPolicy(){

        return new FixDiscountPolicy();
    }


    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }


    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


}
