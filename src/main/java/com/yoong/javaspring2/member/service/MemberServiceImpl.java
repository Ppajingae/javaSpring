package com.yoong.javaspring2.member.service;

import com.yoong.javaspring2.member.entity.Member;
import com.yoong.javaspring2.member.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findByMember(Long id) {
        return memberRepository.findByIdOrNull(id);
    }

}
