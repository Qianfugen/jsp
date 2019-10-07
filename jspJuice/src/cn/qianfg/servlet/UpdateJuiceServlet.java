package cn.qianfg.servlet;

import cn.qianfg.polo.Juice;
import cn.qianfg.service.FileUploadJuice;
import cn.qianfg.service.JuiceService;
import cn.qianfg.service.impl.FileUploadJuiceImpl;
import cn.qianfg.service.impl.JuiceServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateJuiceServlet")
public class UpdateJuiceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符为"utf-8"
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        JuiceService js=new JuiceServiceImpl();
        FileUploadJuice fu=new FileUploadJuiceImpl();
        if (ServletFileUpload.isMultipartContent(request)){//判断是否为多部件
            //创建缓存对象
            DiskFileItemFactory dif=new DiskFileItemFactory();
            //上传核心工具
            ServletFileUpload sfu=new ServletFileUpload(dif);
            //解析request请求
            List<FileItem> fs=null;

            try{
                fs=sfu.parseRequest(request);
            }catch(Exception e){
                e.printStackTrace();
            }
            Juice ju=fu.upload(fs);
            //先打印添加的Juice
            System.out.println("修改结果"+ju);
            int flag=js.updateJuice(ju);
            if(flag>0){
                System.out.println("修改成功");
            }

            response.sendRedirect("juiceMainServlet");
        }else{
            System.out.println("修改失败,返回修改页面");
            response.sendRedirect("toUpdateJuiceServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
