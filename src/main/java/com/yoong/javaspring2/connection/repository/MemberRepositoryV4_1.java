package com.yoong.javaspring2.connection.repository;

import com.yoong.javaspring2.connection.domain.Member;
import com.yoong.javaspring2.connection.repository.exception.RuntimeSQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV4_1 implements MemberRepository {

    private final DataSource dataSource;

    public MemberRepositoryV4_1(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member){

        String sql = "insert into member (member_id, money) values (?, ?)";

        Connection connection = null;

        // DB에 쿼리를 날리는 것
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getMemberId());
            preparedStatement.setInt(2, member.getMoney());
            preparedStatement.executeUpdate();
            return member;
        }catch (SQLException e){
            log.error("db error", e);
            throw new RuntimeSQLException(e);
        }finally {
            close(connection, preparedStatement, null);
        }
    }

    @Override
    public Member findById(String memberId){
        String sql = "select * from member where member_id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Member member = new Member();
                member.setMemberId(resultSet.getString("member_id"));
                member.setMoney(resultSet.getInt("money"));

                return member;
            }else{
                throw new NoSuchElementException("memberId is not found, member id: " + memberId);
            }

        }catch (SQLException e){
            log.error("db error", e);
            throw new RuntimeSQLException(e);
        }finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void update(String memberId, int money){

        String sql = "update member set money=? where member_id=?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, memberId);
            int resultSize = preparedStatement.executeUpdate();
            log.info("resultSize = {}" , resultSize);

        }catch (SQLException e){
            log.error("db error", e);
            throw new RuntimeSQLException(e);
        }finally {
            close(connection, preparedStatement, null);
        }
    }

    @Override
    public void delete(String memberId){

        String sql = "delete from member where member_id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberId);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            log.error("db error", e);
            throw new RuntimeSQLException(e);
        }finally {
            close(connection, preparedStatement, null);
        }
    }

    private void close(Connection connection, Statement statement, ResultSet resultSet){

        JdbcUtils.closeResultSet(resultSet);
        JdbcUtils.closeStatement(statement);
        //트렌젝션 동기화 사용시에 dataSourceUtils를 사용 해서 닫아준다
        DataSourceUtils.releaseConnection(connection, dataSource);
    }

    private Connection getConnection() throws SQLException{

        Connection connection = DataSourceUtils.getConnection(dataSource);
        log.info("get connection = {}, class = {}", connection, connection.getClass());

        return connection;
    }
}
