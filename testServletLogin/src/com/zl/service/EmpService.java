package com.zl.service;

import com.zl.pojo.Emp;
import com.zl.pojo.FenYe;
import com.zl.pojo.Query;

import java.util.List;

public interface EmpService {
    /**
     * 添加员工
     */
    public int addEmp(Emp emp);
    /**
     * 根据empno删除员工
     */
    public int deleteEmp(String[] empNos);
    /**
     * 根据empno修改员工
     */
    public int updateEmp(Emp emp);
    /**
     * 根据empno查询员工
     */
    public Emp queryEmpById(int empNo);
    /**
     * 查询所有员工
     */
    public List<Emp> queryAllEmp();
    /**
     * 根据ename模糊查询员工,根据入职日期范围查询
     *      多个条件的解决方式:
     *          1. 提供一个专门做为查询条件的对象
     *          2. 使用Map集合
     */
    public List<Emp> queryEmpByLike(Query query);
    /**
     * 多条件分页查询
     */
    public List<Emp> queryEmpByFy(FenYe fy, String pg, Query query);
    /**
     * 查询符合要求的记录总数
     */
    public int queryEmpCount(Query query);
}
