package com.zl.util;

import java.sql.*;

/**
 * 产生数据库连接以及关闭资源的工具类:单例状态
 */
public class JDBCUtil {
    //懒汉模式单例
    private static JDBCUtil jdbcUtil;
    private JDBCUtil(){
        //加载启动
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public synchronized static JDBCUtil init(){
        if(jdbcUtil==null){
            jdbcUtil=new JDBCUtil();
        }
        return jdbcUtil;
    }
    /**
     * 提供静态方法用于返回数据库连接
     */
    public Connection getConnection(){
        Connection conn= null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","java38","123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 提供静态的用于关闭资源的方法
     */
    public void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
