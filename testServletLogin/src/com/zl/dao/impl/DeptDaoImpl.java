package com.zl.dao.impl;

import com.zl.basedao.BaseDao;
import com.zl.dao.DeptDao;
import com.zl.pojo.Dept;
import com.zl.util.JDBCUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptDaoImpl extends BaseDao implements DeptDao {
    /**
     * 在该抽象方法中处理结果集,不同的dao类继承BaseDao都必须要重写该方法
     * 那个dao继承该类,那么结果集中的数据就是那个dao对应的表
     *
     * @param rs
     */
    @Override
    public List<Dept> getResultObject(ResultSet rs) {
        List<Dept> ds=new ArrayList<Dept>();
        try {
            while(rs.next()){
                Dept d=new Dept();
                d.setDeptNo(rs.getInt("deptNo"));
                d.setdName(rs.getString("dname"));
                d.setLoc(rs.getString("loc"));
                ds.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.init().close(conn,ps,rs);
        }
        return ds;
    }
    /**
     * 查询所有部门
     */
    @Override
    public List<Dept> queryAllDept() {
        String sql="select * from dept";
        return query(sql,null);
    }
}
