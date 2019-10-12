package cn.qianfg.servlet;

import cn.qianfg.pojo.FenYe;
import cn.qianfg.pojo.News;
import cn.qianfg.pojo.QueryNews;
import cn.qianfg.pojo.User;
import cn.qianfg.service.NewsService;
import cn.qianfg.service.impl.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/newsMainServlet")
public class NewsMainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取登录页面数据,返回Object类型,需要强转
        User user = (User) request.getSession().getAttribute("loginUser");
        String userName="";
        if(user!=null) {
            userName = user.getName();
        }

        //查询页面
        String qTitle=request.getParameter("qTitle");
        String qContext=request.getParameter("qContext");
        if(qTitle==null){
            System.out.println("qTitle为空");
            qTitle="";
        }
        if(qContext==null){
            System.out.println("qContext为空");
            qContext="";
        }
        System.out.println("查询标题:" + qTitle+",查询内容:"+qContext);

        //封装成QueryNews对象
        NewsService ns = new NewsServiceImpl();
        QueryNews queryNews=new QueryNews();
        queryNews.setqTitle(qTitle);
        queryNews.setqContext(qContext);

        //处理分页对象
        FenYe fy=new FenYe();
        System.out.println("方法调用前:"+fy);
        String pg=request.getParameter("pg");
        List<News> newsList=ns.queryNewsByFy(fy,pg,queryNews);
        System.out.println("newsList: "+newsList);
        System.out.println("方法调用后:"+fy);

        //把newsList,queryNews,分页对象和pg添加到request
        request.setAttribute("newsList",newsList);
        request.setAttribute("fy",fy);
        request.setAttribute("pg",pg);
        request.setAttribute("queryNews",queryNews);

        //请求转发
        request.getRequestDispatcher("main.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
