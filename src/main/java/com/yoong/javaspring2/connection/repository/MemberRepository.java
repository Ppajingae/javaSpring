package com.yoong.javaspring2.connection.repository;

import com.yoong.javaspring2.connection.domain.Member;

public interface MemberRepository {

    Member save(Member member);

    Member findById(String memberId);

    void update(String memberId, int money);

    void delete(String memberId);
}
