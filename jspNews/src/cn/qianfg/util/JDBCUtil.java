package cn.qianfg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
    private static JDBCUtil jdbcUtil;

    //加载启动项
    public JDBCUtil() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //懒汉模式
    public static synchronized JDBCUtil init() {
        if (jdbcUtil == null) {
            jdbcUtil = new JDBCUtil();
        }
        return jdbcUtil;
    }

    //连接数据库
    public Connection getConnection() {
        Connection conn = null;
        try {
            //本地数据库
//            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "qianfg", "123456");
            //ALiBaBa数据库
            conn = DriverManager.getConnection("jdbc:oracle:thin:@47.100.61.199:1521:orcl", "qianfg", "123456");
            //hongkong数据库
//            conn = DriverManager.getConnection("jdbc:oracle:thin:@47.240.621.160:1521:orcl", "qianfg", "123456");
            //虚拟机数据库
//            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.13.44:1522:orcl", "qianfg", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭连接
    public void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
