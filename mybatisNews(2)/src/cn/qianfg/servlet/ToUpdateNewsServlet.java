package cn.qianfg.servlet;

import cn.qianfg.pojo.News;
import cn.qianfg.service.NewsService;
import cn.qianfg.service.impl.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

@WebServlet("/toUpdateNewsServlet")
public class ToUpdateNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取添加页面属性值
        String id=request.getParameter("id");
        if(id==null){
            System.out.println("未获取到新闻ID");
        }
        //根据id查询news对象
        NewsService ns=new NewsServiceImpl();
        News news=ns.queryNewsById(new Integer(id));
        System.out.println("news: "+news);
        request.setAttribute("news",news);

        //获取错误信息
        String error=request.getParameter("error");
        if(error==null){
            error="";
        }
        //请求转发
        request.getRequestDispatcher("updateNews.jsp?error="+ URLEncoder.encode(error,"utf-8")).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
