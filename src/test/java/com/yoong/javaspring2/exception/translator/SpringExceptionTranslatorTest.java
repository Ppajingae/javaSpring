package com.yoong.javaspring2.exception.translator;

import com.yoong.javaspring2.connection.domain.Member;
import com.yoong.javaspring2.connection.repository.exception.RuntimeDuplicatedException;
import com.yoong.javaspring2.connection.repository.exception.RuntimeSQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static com.yoong.javaspring2.connection.ConnectionConst.*;

@Slf4j
public class SpringExceptionTranslatorTest {

    DataSource dataSource;


    @BeforeEach
    void setup(){
        dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    }

    @Test
    void SQLExceptionErrorCode(){

        String sql = "select bad grammar";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();

        }catch (SQLException e){
            Assertions.assertThat(e.getErrorCode()).isEqualTo(42122);
            int errorCode = e.getErrorCode();

            log.info("erroeCode = {}", errorCode);
            log.info("error", e);
        }
    }

    //org.springframework.jdbc.support.sql-error-codes.xml
    @Test
    void exceptionTranslator(){
        String sql = "select bad grammar";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();
        }catch (SQLException e){
            Assertions.assertThat(e.getErrorCode()).isEqualTo(42122);

            SQLErrorCodeSQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator(dataSource);

            DataAccessException ex = translator.translate("select", sql, e);

            log.info("result", ex);

            Assertions.assertThat(ex.getClass()).isEqualTo(BadSqlGrammarException.class);
        }
    }
}
