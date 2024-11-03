package com.yoong.javaspring2.exception.translator;

import com.yoong.javaspring2.connection.domain.Member;
import com.yoong.javaspring2.connection.repository.exception.RuntimeDuplicatedException;
import com.yoong.javaspring2.connection.repository.exception.RuntimeSQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static com.yoong.javaspring2.connection.ConnectionConst.*;

@Slf4j
public class ExceptionTranslatorV1Test {


    Repository repository;
    Service service;

    @BeforeEach
    void setup(){
        DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        repository = new Repository(dataSource);
        service = new Service(repository);
    }

    @Test
    void duplicatedSaveKey(){

        service.create("myId");
        service.create("myId");

    }


    @Slf4j
    @RequiredArgsConstructor
    static class Service{

        private final Repository repository;

        void create(String memberId){

            try {
                repository.save(new Member(memberId, 0));
                log.info("saveId = {}", memberId);
            }catch (RuntimeDuplicatedException e){
                log.info("키 중복!! 애러 복구를 시도 합니다");
                String newMemberId = generateNewString(memberId);
                log.info("newMemberId = {}", newMemberId);
                repository.save(new Member(newMemberId, 0));
            }catch (RuntimeSQLException e){

                log.info("데이터 접근 계층 애러");
            }

        }

        private String generateNewString(String memberId){
            return memberId + new Random().nextInt(10000);
        }
    }

    @RequiredArgsConstructor
    static class Repository{

        private final DataSource dataSource;

        public Member save(Member member){
            String sql = "insert into member(member_id, money) values(?,?)";

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {

                connection = dataSource.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, member.getMemberId());
                preparedStatement.setInt(2, member.getMoney());
                preparedStatement.executeUpdate();
                return member;

            }catch (SQLException e){

                if(e.getErrorCode() == 23505){
                    throw new RuntimeDuplicatedException(e);
                }else{
                    throw new RuntimeSQLException(e);
                }
            }finally {
                JdbcUtils.closeStatement(preparedStatement);
                JdbcUtils.closeConnection(connection);
            }
        }



    }
}
