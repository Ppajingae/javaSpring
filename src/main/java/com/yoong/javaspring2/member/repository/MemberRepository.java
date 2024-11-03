package com.yoong.javaspring2.member.repository;


import com.yoong.javaspring2.member.entity.Member;

public interface MemberRepository {

    void save(Member member);

    Member findByIdOrNull(Long id);
}
