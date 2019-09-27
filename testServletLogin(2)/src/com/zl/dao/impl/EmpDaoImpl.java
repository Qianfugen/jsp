package com.zl.dao.impl;

import com.zl.basedao.BaseDao;
import com.zl.dao.EmpDao;
import com.zl.pojo.Emp;
import com.zl.pojo.FenYe;
import com.zl.pojo.Query;
import com.zl.util.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDaoImpl extends BaseDao implements EmpDao {
    /**
     * 添加员工
     *
     * @param emp
     */
    @Override
    public int addEmp(Emp emp) {
        List<Object> args=new ArrayList<Object>();
        String sql="insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno,pic) values(?,?,?,?,?,?,?,?,?)";
        args.add(emp.getEmpNo());
        args.add(emp.geteName());
        args.add(emp.getJob());
        args.add(emp.getMgr());
        if(emp.getHireDate()!=null) {
            args.add(new Date(emp.getHireDate().getTime()));
        }else{
            args.add(null);
        }
        args.add(emp.getSal());
        args.add(emp.getComm());
        args.add(emp.getDeptNo());
        args.add(emp.getPic());
        return update(sql,args);
    }
    /**
     * 根据empno删除员工
     *
     * @param empNo
     */
    @Override
    public int deleteEmp(int empNo) {
        List<Object> args=new ArrayList<Object>();
        String sql="delete from emp where empno=?";
        args.add(empNo);
        return update(sql,args);
    }
    /**
     * 根据empno修改员工
     *
     * @param emp
     */
    @Override
    public int updateEmp(Emp emp) {
        List<Object> args=new ArrayList<Object>();
        String sql="update emp set ename=?,job=?,mgr=?,hiredate=?,sal=?,comm=?,deptno=?,pic=? where empno=?";
        args.add(emp.geteName());
        args.add(emp.getJob());
        args.add(emp.getMgr());
        if(emp.getHireDate()!=null) {
            args.add(new Date(emp.getHireDate().getTime()));
        }else{
            args.add(null);
        }
        args.add(emp.getSal());
        args.add(emp.getComm());
        args.add(emp.getDeptNo());
        args.add(emp.getPic());
        args.add(emp.getEmpNo());
        return update(sql,args);
    }
    @Override
    public Emp queryEmpById(int empNo) {// "12345 or 1=1"
        String sql="select * from emp where empno=?";
        List<Object> args=new ArrayList<Object>();
        args.add(empNo);
        List<Emp> es=query(sql,args);
        if(es.size()==1){
            return es.get(0);
        }
        return null;
    }
    /**
     * 查询所有员工
     */
    @Override
    public List<Emp> queryAllEmp() {
        String sql="select * from emp";
        return query(sql,null);
    }
    /**
     * 根据ename模糊查询员工,根据入职日期范围查询
     * 多个条件的解决方式:
     * 1. 提供一个专门做为查询条件的对象
     * 2. 使用Map集合
     */
    @Override
    public List<Emp> queryEmpByLike(Query query) {
        //存放sql语句中的参数
        List<Object> args=new ArrayList<Object>();
        String sql="select * from emp where 1=1";
        if(query.getQeName()!=null && !"".equals(query.getQeName())){
            sql+=" and ename like concat('%',concat(?,'%'))";
            args.add(query.getQeName());
        }
        if(query.getqStartHiredate()!=null){
            sql+=" and hiredate>?";
            args.add(new Date(query.getqStartHiredate().getTime()));
        }
        if(query.getqEndHiredate()!=null){
            sql+=" and hiredate<?";
            args.add(new Date(query.getqEndHiredate().getTime()));
        }
        return query(sql,args);
    }

    /**
     * 多条件分页查询
     *
     * @param fy
     */
    @Override
    public List<Emp> queryEmpByFy(FenYe fy) {
        List<Object> args=new ArrayList<Object>();
        String sql="select * from (select e.*,rownum r from (select * from emp where 1=1 ";
        if(fy.getQuery().getQeName()!=null &&!"".equals(fy.getQuery().getQeName())){
            sql+=" and ename like concat('%',concat(?,'%'))";
            args.add(fy.getQuery().getQeName());
        }
        if(fy.getQuery().getqStartHiredate()!=null){
            sql+=" and hiredate>=?";
            args.add(new Date(fy.getQuery().getqStartHiredate().getTime()));
        }
        if(fy.getQuery().getqEndHiredate()!=null){
            sql+=" and hiredate<=?";
            args.add(new Date(fy.getQuery().getqEndHiredate().getTime()));
        }
        sql+=" ) e) where r>? and r<=?";
        args.add(fy.getRowStart());
        args.add(fy.getRowEnd());
        return query(sql,args);
    }

    /**
     * 查询符合要求的记录总数
     *
     * @param query
     */
    @Override
    public int queryEmpCount(Query query) {
        int count=0;
        List<Object> args=new ArrayList<Object>();
        conn=JDBCUtil.init().getConnection();
        String sql="select count(1) from emp where 1=1 ";
        if(query.getQeName()!=null && !"".equals(query.getQeName())){
            sql+=" and ename like concat('%',concat(?,'%'))";
            args.add(query.getQeName());
        }
        if(query.getqStartHiredate()!=null){
            sql+=" and hiredate>?";
            args.add(new Date(query.getqStartHiredate().getTime()));
        }
        if(query.getqEndHiredate()!=null){
            sql+=" and hiredate<?";
            args.add(new Date(query.getqEndHiredate().getTime()));
        }
        try {
            ps=conn.prepareStatement(sql);

            for(int i=0;i<args.size();i++){
                ps.setObject(i+1,args.get(i));
            }
            rs=ps.executeQuery();
            rs.next();
            count=rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtil.init().close(conn,ps,rs);
        }
        return count;
    }

    /**
     * 在该抽象方法中处理结果集,不同的dao类继承BaseDao都必须要重写该方法
     * 那个dao继承该类,那么结果集中的数据就是那个dao对应的表
     *
     *  1. 如果之类重写的父类中的方法,那么在次调用该方法的时候执行的是之类重写过后的方法
     */
    @Override
    public List<Emp> getResultObject(ResultSet rs) {
        List<Emp> es=new ArrayList<Emp>();
        try {
            while(rs.next()){
                Emp emp=new Emp();
                emp.setEmpNo(rs.getInt(1));//根据字段的序号从结果集中获取数据
                emp.seteName(rs.getString("enAmE"));//根据字段的名称获取结果集中数据
                emp.setJob(rs.getString("job"));
                /**
                 * getInt():如果数据库字段的值为空白(null),name默认返回0
                 */
                if(rs.getInt("mgr")!=0) {
                    emp.setMgr(rs.getInt("mgr"));
                }
                emp.setHireDate(rs.getDate("hiredate"));
                emp.setSal(rs.getDouble("sal"));
                emp.setComm(rs.getDouble("comm"));
                if(rs.getInt("deptno")!=0) {
                    emp.setDeptNo(rs.getInt("deptno"));
                }
                emp.setPic(rs.getString("pic"));
                es.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.init().close(conn,ps,rs);
        }
        return es;
    }
}
