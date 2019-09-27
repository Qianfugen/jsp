package com.zl.service.impl;

import com.zl.dao.EmpDao;
import com.zl.dao.impl.EmpDaoImpl;
import com.zl.pojo.Emp;
import com.zl.pojo.FenYe;
import com.zl.pojo.Query;
import com.zl.service.EmpService;

import java.util.List;

public class EmpServiceImpl implements EmpService {
    private EmpDao ed=new EmpDaoImpl();
    /**
     * 添加员工
     *
     * @param emp
     */
    @Override
    public int addEmp(Emp emp) {
        return ed.addEmp(emp);
    }

    /**
     * 根据empno删除员工
     *
     */
    @Override
    public int deleteEmp(String[] empNos) {
        if(empNos!=null&&empNos.length>0){
            for(String empNo:empNos){
                ed.deleteEmp(new Integer(empNo));
            }
        }
        return 1;
    }

    /**
     * 根据empno修改员工
     *
     * @param emp
     */
    @Override
    public int updateEmp(Emp emp) {
        return ed.updateEmp(emp);
    }

    /**
     * 根据empno查询员工
     *
     * @param empNo
     */
    @Override
    public Emp queryEmpById(int empNo) {
        return ed.queryEmpById(empNo);
    }

    /**
     * 查询所有员工
     */
    @Override
    public List<Emp> queryAllEmp() {
        return ed.queryAllEmp();
    }

    /**
     * 根据ename模糊查询员工,根据入职日期范围查询
     * 多个条件的解决方式:
     * 1. 提供一个专门做为查询条件的对象
     * 2. 使用Map集合
     *
     * @param query
     */
    @Override
    public List<Emp> queryEmpByLike(Query query) {
        return ed.queryEmpByLike(query);
}

    /**
     * 多条件分页查询
     *
     * @param fy
     * @param pg
     * @param query
     */
    @Override
    public List<Emp> queryEmpByFy(FenYe fy, String pg, Query query) {//xabd1234
        /*
         * 处理分页对象
         */
        //设置符合要求的记录总数
        fy.setRowsCount(queryEmpCount(query));
        //设置当前页码
        Integer page=null;
        if(pg!=null){
            page=new Integer(pg);
            if(page<=0){
                page=1;
            }
            if(page>fy.getPageCount()){
                page=fy.getPageCount();
            }
        }else{
            page=1;
        }
        fy.setPage(page);
        //设置条件查询对象
        fy.setQuery(query);
        return ed.queryEmpByFy(fy);
    }

    /**
     * 查询符合要求的记录总数
     *
     * @param query
     */
    @Override
    public int queryEmpCount(Query query) {
        return ed.queryEmpCount(query);
    }
}
