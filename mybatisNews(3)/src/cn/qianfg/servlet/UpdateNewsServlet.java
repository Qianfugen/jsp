package cn.qianfg.servlet;

import cn.qianfg.pojo.News;
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
import java.net.URLEncoder;
import java.util.List;

@WebServlet("/updateNewsServlet")
public class UpdateNewsServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        NewsService ns = new NewsServiceImpl();
        NewsService ns= (NewsService) getBean(NewsServiceImpl.class);
//        FileUploadNews fu = new FileUploadNewsImpl();
        FileUploadNews fu= (FileUploadNews) getBean(FileUploadNewsImpl.class);
        if (ServletFileUpload.isMultipartContent(request)) {//判断是否是多部件表单
            //创建缓存对象
            DiskFileItemFactory dif = new DiskFileItemFactory();
            //创建上传的核心工具类
            ServletFileUpload sfu = new ServletFileUpload(dif);
            //解析request请求
            List<FileItem> fs = null;
            try {
                fs = sfu.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            News news = fu.upload(fs);
            System.out.println(news);
            int flag = ns.updateNews(news);
            if(flag>0){
                System.out.println("更新成功");
            }else{
                System.out.println("更新失败");
            }
            response.sendRedirect("newsMainServlet");
        } else {
            request.getRequestDispatcher("toUpdateNewsServlet?error="+ URLEncoder.encode("更新失败,请重试...","utf-8"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
