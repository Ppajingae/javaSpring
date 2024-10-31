package com.yoong.javaspring2.connection.service;

import com.yoong.javaspring2.connection.domain.Member;
import com.yoong.javaspring2.connection.repository.MemberRepositoryV2;
import com.yoong.javaspring2.connection.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
@Slf4j
public class MemberServiceV3_1 {

    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {

        //트렌젝션 매니저 시작

        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            Member fromMember = memberRepository.findById(fromId);
            Member toMember = memberRepository.findById(toId);

            memberRepository.update(fromId, fromMember.getMoney() - money);
            validation(toMember);
            memberRepository.update(toId, toMember.getMoney() + money);
            transactionManager.commit(status);

        }catch (Exception e){

            transactionManager.rollback(status);
            throw new IllegalStateException();

        }
    }

    private static void validation(Member member){
        if(member.getMemberId().equals("ex")){
            throw new IllegalStateException();
        }
    }
}
