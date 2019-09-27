package com.zl.service;

import com.zl.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     */
    public List<Dept> queryAllDept();
}
