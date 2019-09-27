package com.zl.dao;

import com.zl.pojo.Dept;

import java.util.List;

public interface DeptDao {
    /**
     * 查询所有部门
     */
    public List<Dept> queryAllDept();
}
