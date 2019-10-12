package cn.qianfg.servlet;

import cn.qianfg.service.FileUploadNews;
import cn.qianfg.service.NewsService;
import cn.qianfg.service.impl.FileUploadNewsImpl;
import cn.qianfg.service.impl.NewsServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addNewsServlet")
public class AddNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsService es = new NewsServiceImpl();
        FileUploadNews fu = new FileUploadNewsImpl();
        if (ServletFileUpload.isMultipartContent(request)) {//判断是否为多部件表单
            //创建缓存对象
            DiskFileItemFactory dif = new DiskFileItemFactory();
            //上传核心工具类
            ServletFileUpload sfu = new ServletFileUpload(dif);
            //解析request请求
            List<FileItem> fs = null;
            try {
                fs = sfu.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            int flag = es.addNews(fu.upload(fs));

            response.sendRedirect("newsMainServlet");
        } else {
            response.sendRedirect("addNews.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
