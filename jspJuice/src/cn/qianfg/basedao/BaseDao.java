package cn.qianfg.basedao;

import cn.qianfg.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    protected Connection conn;
    protected PreparedStatement ps;
    protected ResultSet rs;

    /**
     * 打印sql语句和args参数
     */
    public void showSql(String sql, List<Object> args) {
        System.out.println("---------------------------------------------");
        System.out.println("sql语句:" + sql);
        if (args != null && args.size() != 0) {
            for (Object obj : args) {
                System.out.print(obj + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * 公共增删改方法
     */
    public int update(String sql, List<Object> args) {
        int flag = -1;
        showSql(sql, args);
        //连接数据库
        conn = JDBCUtil.init().getConnection();
        //处理sql语句
        try {
            ps = conn.prepareStatement(sql);
            //给sql语句的占位符绑定值
            if (args != null && args.size() != 0) {
                for (int i = 0; i < args.size(); i++) {
                    ps.setObject(i + 1, args.get(i));
                }
            }
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭数据库
            JDBCUtil.init().closeAll(conn,ps,rs);
        }
        return flag;
    }

    /**
     * 公共查询方法
     */
    public List query(String sql, List<Object> args) {
        showSql(sql,args);
        conn = JDBCUtil.init().getConnection();
        try {
            ps = conn.prepareStatement(sql);
            if (args != null && args.size() != 0) {
                for (int i = 0; i < args.size(); i++) {
                    ps.setObject(i + 1, args.get(i));
                }
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getResultObject(rs);
    }

    /**
     * 以列表形式返回查询结果
     */
    public abstract List getResultObject(ResultSet rs);
}
