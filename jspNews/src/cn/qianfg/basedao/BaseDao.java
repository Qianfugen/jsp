package cn.qianfg.basedao;


import cn.qianfg.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    protected Connection conn;        //数据库连接对象
    protected PreparedStatement ps;   //sql语句处理
    protected ResultSet rs;           //结果集合

    //打印输入的sql语句和条件参数
    private void showSql(String sql, List<Object> args) {
        System.out.println("---------------------------------------------------------------");
        System.out.println("sql语句:" + sql);
        if (args != null) {
            for (Object obj : args) {
                System.out.println(obj + "  ");
            }
        }
        System.out.println("---------------------------------------------------------------");
    }

    /**
     * 增删改公用方法
     * @param sql sql语句
     * @param args 传入sql中参数
     * @return -1 操作失败; >0 操作成功
     */
    public int update(String sql, List<Object> args){
        int flag=-1;
        showSql(sql,args);
        conn= JDBCUtil.init().getConnection();
        try {
            ps=conn.prepareStatement(sql);
            if(args!=null && args.size()!=0){
                for(int i=0;i<args.size();i++){
                    ps.setObject(i+1,args.get(i));
                }
                flag=ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.init().closeAll(conn,ps,rs);
        }
        return flag;
    }

    public List query(String sql, List<Object> args){
        showSql(sql,args);
        conn= JDBCUtil.init().getConnection();
        try {
            ps=conn.prepareStatement(sql);
            if(args!=null && args.size()!=0){
                for(int i=0;i<args.size();i++){
                    ps.setObject(i+1,args.get(i));
                }
            }
            rs=ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getResultObject(rs);
    }

    public abstract List getResultObject(ResultSet rs);

}
