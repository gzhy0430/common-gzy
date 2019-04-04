package com.indi.design.tmplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/22.
 * 模板模式重构JDBC操作业务场景
 */
public class JDBCTemplateTestX {
    public static void main(String[] args) {
        MemberDao dao = new MemberDao(null);
        List<?> result = dao.selectAll();
        System.out.println(result);
    }
}

abstract class JDBCTemplate{
    private DataSource dataSource;
    JDBCTemplate(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public List<?> excuteQuery(String sql, RowMapper<?> rowMapper, Object[] values){
        try {
            Connection conn = this.getConnection();
            PreparedStatement pstm = this.createPrepareStatement(conn, sql);
            ResultSet rs = this.excuteQuery(pstm, values);
            List<?> result = this.parseResultSet(rs, rowMapper);
            this.closeResultSet(rs);
            this.closeStatement(pstm);
            this.closeConnection(conn);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    private PreparedStatement createPrepareStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    private ResultSet excuteQuery(PreparedStatement pstm, Object[] values) throws SQLException {
        for(int i = 0; i < values.length; i++){
            pstm.setObject(i, values[i]);
        }
        return pstm.executeQuery();
    }

    private List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws SQLException {
        List<Object> result = new ArrayList<>();
        int rowNum = 1;
        while(rs.next()){
            result.add(rowMapper.mapRow(rs, rowNum++));
        }
        return result;
    }

    private void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }

    private void closeStatement(PreparedStatement pstm) throws SQLException {
        pstm.close();
    }

    private void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }
}

interface RowMapper<T>{
    Object mapRow(ResultSet rs, int rowNum) throws SQLException;
}

class MemberDao extends JDBCTemplate{
    MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> selectAll(){
        String sql = "select * from t_member";
        return super.excuteQuery(sql, (rs, rowNum)->{
            Member m = new Member();
            m.setUsername(rs.getString("username"));
            return m;
        }, null);
    }

}

class Member{
    private String username;
    private String password;
    private String nickName;

    private int age;
    private String addr;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
