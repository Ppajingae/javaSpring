package com.yoong.javaspring2.connection.service;

import com.yoong.javaspring2.connection.domain.Member;
import com.yoong.javaspring2.connection.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class MemberServiceV4 {

    private final MemberRepository memberRepository;

    @Transactional
    public void accountTransfer(String fromId, String toId, int money){

        businessLogic(fromId, toId, money);
    }

    private static void validation(Member member){
        if(member.getMemberId().equals("ex")){
            throw new IllegalStateException();
        }
    }
    
    private void businessLogic(String fromId, String toId, int money){
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }
}
