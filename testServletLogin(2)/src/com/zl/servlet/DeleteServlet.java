package com.zl.servlet;

import com.zl.service.EmpService;
import com.zl.service.impl.EmpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/emp/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//接受需要删除的员工的编号
        String[] empNos=req.getParameterValues("empNo");
        EmpService es=new EmpServiceImpl();
        int flag=es.deleteEmp(empNos);
        if(flag>0){
            resp.sendRedirect("empMainServlet");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
