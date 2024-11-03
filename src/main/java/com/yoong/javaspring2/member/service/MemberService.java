package com.yoong.javaspring2.member.service;


import com.yoong.javaspring2.member.entity.Member;

public interface MemberService {

    void join(Member member);

    Member findByMember(Long id);
}
