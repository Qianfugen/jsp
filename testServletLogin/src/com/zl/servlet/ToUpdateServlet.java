package com.zl.servlet;

import com.zl.pojo.Dept;
import com.zl.pojo.Emp;
import com.zl.service.DeptService;
import com.zl.service.EmpService;
import com.zl.service.impl.DeptServiceImpl;
import com.zl.service.impl.EmpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/emp/toUpdateServlet")
public class ToUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询当前的员工
        String empNo=request.getParameter("empNo");
        EmpService es=new EmpServiceImpl();
        DeptService ds=new DeptServiceImpl();
        Emp emp=new Emp();
        if(empNo!=null &&!"".equals(empNo)){
            emp=es.queryEmpById(new Integer(empNo));
        }
        //查询所有的部门列表
        List<Dept> depts=ds.queryAllDept();
        request.setAttribute("emp",emp);
        request.setAttribute("depts",depts);
        request.getRequestDispatcher("../updateEmp.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
