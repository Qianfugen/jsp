package cn.qianfg.util;
/**
 * 工具类
 */

import java.sql.*;

public class JDBCUtil {
    //单例模式
    private static JDBCUtil jdbcUtil;
    public JDBCUtil(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static JDBCUtil init(){
        if(jdbcUtil == null){
            jdbcUtil=new JDBCUtil();
        }
        return jdbcUtil;
    }

    /**
     * 获取连接数据库的方法
     * @return 返回连接实例对象
     */
    public Connection getConnection(){
        Connection conn=null;
        try{
            conn=DriverManager.getConnection("jdbc:oracle:thin:@10.1.13.44:1522:orcl","qianfg","123456");
        }catch(Exception e){
            System.out.println("连接数据库异常，连接失败");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接的方法
     * @param conn 连接实例对象
     * @param ps sql语句实例对象
     * @param rs
     */
    public void closeAll(Connection conn,PreparedStatement ps,ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(conn!=null){
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
