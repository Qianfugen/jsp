package cn.qianfg.basedao;

import cn.qianfg.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * 公共方法
 */
public abstract class BaseDao {
    //公共属性
    protected Connection conn;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public int update(String sql, List<Object> args) {
        int flag = -1;
        conn = JDBCUtil.init().getConnection();
        try {
            ps = conn.prepareStatement(sql);
            if (args != null && args.size() != 0) {
                for (int i = 0; i < args.size(); i++) {
                    ps.setObject(i + 1, args.get(i));
                }
            }
            flag = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.init().closeAll(conn, ps, rs);
        }
        return flag;
    }

    public List query(String sql, List<Object> args) {
        conn = JDBCUtil.init().getConnection();
        try {
            System.out.println("sql语句："+sql);
            ps = conn.prepareStatement(sql);
            if (args != null && args.size() != 0) {
                for (int i = 0; i < args.size(); i++) {
                    ps.setObject(i + 1, args.get(i));
                    System.out.println("参数不为空，有值");
                    System.out.println("参数值："+args.get(i));
                }
            }
            rs = ps.executeQuery();
            if(rs!=null){
                System.out.println("rs不为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getResult(rs);
    }

    public abstract List getResult(ResultSet rs);
}
