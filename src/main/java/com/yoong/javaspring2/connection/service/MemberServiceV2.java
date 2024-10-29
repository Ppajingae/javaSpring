package com.yoong.javaspring2.connection.service;

import com.yoong.javaspring2.connection.domain.Member;
import com.yoong.javaspring2.connection.repository.MemberRepositoryV1;
import com.yoong.javaspring2.connection.repository.MemberRepositoryV2;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
@Slf4j
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            connection.setAutoCommit(false);

            Member fromMember = memberRepository.findById(connection, fromId);
            Member toMember = memberRepository.findById(connection, toId);

            memberRepository.update(connection, fromId, fromMember.getMoney() - money);
            validation(toMember);
            memberRepository.update(connection, toId, toMember.getMoney() + money);

            connection.commit(); // 성공 시에 Commit

        }catch (Exception e){

            connection.rollback(); //실패 시에 rollback
            throw new IllegalStateException();

        }finally {
            release(connection);
        }

    }

    private void release(Connection connection) {
        if(connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            }catch (Exception e){
                log.info("error", e);
            }
        }
    }

    private static void validation(Member member){
        if(member.getMemberId().equals("ex")){
            throw new IllegalStateException();
        }
    }
}
