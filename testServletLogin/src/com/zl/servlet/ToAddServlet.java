package com.zl.servlet;

import com.zl.pojo.Dept;
import com.zl.service.DeptService;
import com.zl.service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/emp/toAddServlet")
public class ToAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询所有的部门列表
        DeptService ds=new DeptServiceImpl();
        List<Dept> depts=ds.queryAllDept();
        request.setAttribute("depts",depts);
        request.getRequestDispatcher("../addEmp.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
