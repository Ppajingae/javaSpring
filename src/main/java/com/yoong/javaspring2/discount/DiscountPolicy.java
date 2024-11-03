package com.yoong.javaspring2.discount;

import com.yoong.javaspring2.member.entity.Member;

public interface DiscountPolicy {

    int discount(Member member, int price, int discountAmount);
}
