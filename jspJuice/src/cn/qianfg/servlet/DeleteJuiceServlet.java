package cn.qianfg.servlet;

import cn.qianfg.service.JuiceService;
import cn.qianfg.service.impl.JuiceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteJuiceServlet")
public class DeleteJuiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JuiceService js=new JuiceServiceImpl();
        String[] ids=request.getParameterValues("id");
        int flag=js.deleteJuice(ids);
        if(flag>0){
            request.getRequestDispatcher("juiceMainServlet").forward(request,response);
        }else {
            System.out.println("删除失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
