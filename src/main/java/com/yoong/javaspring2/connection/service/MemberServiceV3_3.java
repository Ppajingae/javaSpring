package com.yoong.javaspring2.connection.service;

import com.yoong.javaspring2.connection.domain.Member;
import com.yoong.javaspring2.connection.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@RequiredArgsConstructor
@Slf4j
public class MemberServiceV3_3 {

    private final MemberRepositoryV3 memberRepository;

    @Transactional
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {

        businessLogic(fromId, toId, money);
    }

    private static void validation(Member member){
        if(member.getMemberId().equals("ex")){
            throw new IllegalStateException();
        }
    }
    
    private void businessLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }
}
