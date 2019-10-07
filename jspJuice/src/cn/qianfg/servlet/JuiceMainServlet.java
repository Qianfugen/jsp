package cn.qianfg.servlet;

import cn.qianfg.polo.FenYe;
import cn.qianfg.polo.Juice;
import cn.qianfg.polo.Query;
import cn.qianfg.polo.User;
import cn.qianfg.service.JuiceService;
import cn.qianfg.service.impl.JuiceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/juiceMainServlet")
public class JuiceMainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取登录页面用户信息,Object对象,需要强转
//        User user = (User) request.getSession().getAttribute("loginUser");

        //查询页面
        String qName = request.getParameter("qName");
        String qStartDate = request.getParameter("qStartDate");
        String qEndDate = request.getParameter("qEndDate");
        if (qName == null) {
            qName = "";
        }
        if (qStartDate == null) {
            qStartDate = "";
        }
        if (qEndDate == null) {
            qEndDate = "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Query query = new Query();
        query.setqName(qName);
        try {
            if(qStartDate!=null&&!"".equals(qStartDate)){
                query.setqStartDate(sdf.parse(qStartDate));
            }
            if(qEndDate!=null&&!"".equals(qEndDate)){
                query.setqEndDate(sdf.parse(qEndDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //打印query
        System.out.println(query);

        //处理分页对象
        JuiceService js = new JuiceServiceImpl();
        FenYe fy = new FenYe();
        String pg = request.getParameter("pg");
        List<Juice> juiceList = js.queryJuiceByFy(fy, pg, query);
        request.setAttribute("fy",fy);
        request.setAttribute("juiceList",juiceList);
        //打印juiceList
        for(Juice ju: juiceList){
            System.out.println(ju);
        }
        //跳转到主页面
        request.getRequestDispatcher("main.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
