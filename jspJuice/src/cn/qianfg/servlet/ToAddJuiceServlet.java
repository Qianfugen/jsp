package cn.qianfg.servlet;

import cn.qianfg.polo.Juice;
import cn.qianfg.service.JuiceService;
import cn.qianfg.service.impl.JuiceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/toAddJuiceServlet")
public class ToAddJuiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JuiceService js=new JuiceServiceImpl();
        List<Juice> juiceList=js.queryAllJuice();
        request.setAttribute("juiceList",juiceList);
        request.getRequestDispatcher("addJuice.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
