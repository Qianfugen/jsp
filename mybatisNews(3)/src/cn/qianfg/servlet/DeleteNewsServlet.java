package cn.qianfg.servlet;

import cn.qianfg.service.NewsService;
import cn.qianfg.service.impl.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/deleteNewsServlet")
public class DeleteNewsServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids=request.getParameterValues("id");
        System.out.println("ids: "+ Arrays.toString(ids));
        int flag=-1;
        if(ids!=null||ids.length!=0){
//            NewsService ns=new NewsServiceImpl();
            NewsService ns=(NewsService) getBean(NewsServiceImpl.class);
            for(String id:ids){
                flag=ns.deleteNews(new Integer(id));
            }
            if(flag>0){
                response.sendRedirect("newsMainServlet");
            }else {
                System.out.println("删除失败");
            }
        }else{
            System.out.println("没有选中ID");
            response.sendRedirect("newsMainServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
