package com.zl.basedao;

import com.zl.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 所有dao层类的父类:该父类中提供查询以及增删改的公共方法
 */
public abstract class BaseDao {
    protected Connection conn;
    protected PreparedStatement ps;
    protected ResultSet rs;

    /**
     *打印sql语句,以及参数列表
     */
    public void showSql(String sql, List<Object> args){
        System.out.println("---------------------------------------------");
        System.out.println("sql语句:\t"+sql);
        System.out.print("参数列表:\t");
        if(args!=null) {
            for (Object obj : args) {
                System.out.print(obj + "   ");
            }
        }
        System.out.println();
        System.out.println("---------------------------------------------");
    }

    /**
     * 增删改的公共方法
     */
    public int update(String sql, List<Object> args){
        showSql(sql,args);
        int flag=0;
        conn= JDBCUtil.init().getConnection();
        try {
            //conn.setAutoCommit(false);//关闭自动提交
            ps=conn.prepareStatement(sql);
            if(args!=null&&args.size()!=0){
                for(int i=0;i<args.size();i++){
                    ps.setObject(i+1,args.get(i));
                }
            }
            flag=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.init().close(conn,ps,rs);
        }
        return flag;
    }
    /**
     * 查询的公共方法
     */
    public List query(String sql,List<Object> args){
        showSql(sql,args);
        conn= JDBCUtil.init().getConnection();
        try {
            ps=conn.prepareStatement(sql);
            if(args!=null&&args.size()!=0){
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

    /**
     *
     * 在该抽象方法中处理结果集,不同的dao类继承BaseDao都必须要重写该方法
     * 那个dao继承该类,那么结果集中的数据就是那个dao对应的表
     */
    public abstract  List getResultObject(ResultSet rs);
}
