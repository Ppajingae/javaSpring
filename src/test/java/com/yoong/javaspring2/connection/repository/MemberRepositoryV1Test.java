package com.yoong.javaspring2.connection.repository;

import com.yoong.javaspring2.connection.domain.Member;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import static com.yoong.javaspring2.connection.ConnectionConst.*;


// JDBC DataSource 사용
@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 memberRepositoryV1;

    @BeforeEach
    void beforeEach() {

        //기본 driver Manager -> 새로운 Connection 획득

//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(URL);
        hikariDataSource.setUsername(USERNAME);
        hikariDataSource.setPassword(PASSWORD);

        memberRepositoryV1 = new MemberRepositoryV1(hikariDataSource);
    }

    @Test
    void crud() throws SQLException {
        Member member = new Member("v5", 10000);

        memberRepositoryV1.save(member);

        Member findMember = memberRepositoryV1.findById(member.getMemberId());
        log.info("findMember = {}", findMember);

        Assertions.assertThat(findMember).isEqualTo(member);

        memberRepositoryV1.update(member.getMemberId(), 20000);
        Member updatedMember = memberRepositoryV1.findById(member.getMemberId());
        Assertions.assertThat(updatedMember.getMoney()).isEqualTo(20000);

        memberRepositoryV1.delete(member.getMemberId());
        Assertions.assertThatThrownBy(()-> memberRepositoryV1.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);


    }

}