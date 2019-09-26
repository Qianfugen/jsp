package com.zl.service.impl;

import com.zl.dao.DeptDao;
import com.zl.dao.impl.DeptDaoImpl;
import com.zl.pojo.Dept;
import com.zl.service.DeptService;

import java.util.List;

public class DeptServiceImpl implements DeptService {
    private DeptDao dd=new DeptDaoImpl();
    /**
     * 查询所有部门
     */
    @Override
    public List<Dept> queryAllDept() {
        return dd.queryAllDept();
    }
}
