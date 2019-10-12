package cn.qianfg.servlet;

import cn.qianfg.pojo.NewsType;
import cn.qianfg.service.NewsTypeService;
import cn.qianfg.service.impl.NewsTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/toAddNewsServlet")
public class ToAddNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsTypeService nts=new NewsTypeServiceImpl();
        List<NewsType> newsTypeList=nts.queryAllNewsType();
        request.setAttribute("newsTypeList",newsTypeList);
        System.out.println("newsTypeList: "+newsTypeList);
        request.getRequestDispatcher("addNews.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
