package com.zl.servlet;

import com.zl.pojo.Emp;
import com.zl.pojo.FenYe;
import com.zl.pojo.Query;
import com.zl.service.EmpService;
import com.zl.service.impl.EmpServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/emp/empMainServlet")
public class EmpMainServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 在主页面查询所有的员工列表
         */
        EmpService es=new EmpServiceImpl();
        /**
         * 接受用户的搜索条件
         */
        Query query=new Query();
        try {
            String qeName=request.getParameter("qeName");
            if(qeName==null){
                qeName="";
            }
            String qStartHiredate=request.getParameter("qStartHiredate");
            String qEndHiredate=request.getParameter("qEndHiredate");
            query.setQeName(qeName);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            if(qStartHiredate!=null&&!"".equals(qStartHiredate)){
                query.setqStartHiredate(sdf.parse(qStartHiredate));
            }
            if(qEndHiredate!=null&&!"".equals(qEndHiredate)){
                query.setqEndHiredate(sdf.parse(qEndHiredate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 处理分页对象
         */
        FenYe fy=new FenYe();//xabd1234
        //获取当前用户请求的页码
        String pg=request.getParameter("pg");
        List<Emp> emps=es.queryEmpByFy(fy,pg,query);//xabd1234
        request.setAttribute("emps",emps);
        request.setAttribute("fy",fy);

        /*
        * 测试el表达式式的数据
        * */
        request.getServletContext().setAttribute("app","测试数据app");

        request.getRequestDispatcher("../main.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
