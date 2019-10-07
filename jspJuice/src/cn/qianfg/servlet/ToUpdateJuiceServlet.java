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

@WebServlet("/toUpdateJuiceServlet")
public class ToUpdateJuiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        String id=request.getParameter("id");
        //打印ID
        System.out.println("id为"+id);

        JuiceService js=new JuiceServiceImpl();
        Juice ju=new Juice();
        //查找对应的果汁
        if(id!=null&&!"".equals(id)){
            ju=js.queryJuiceById(new Integer(id));
        }
        request.setAttribute("ju",ju);
        //打印juice
        System.out.println("ju为"+ju);
        //用于显示果汁菜单
        List<Juice> juiceList=js.queryAllJuice();
        System.out.println(juiceList);
        request.setAttribute("juiceList",juiceList);
        request.getRequestDispatcher("updateJuice.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
