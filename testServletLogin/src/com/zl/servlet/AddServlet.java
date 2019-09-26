package com.zl.servlet;

import com.zl.service.EmpService;
import com.zl.service.FileUploadEmp;
import com.zl.service.impl.EmpServiceImpl;
import com.zl.service.impl.FileUploadEmpImpl;
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

@WebServlet("/emp/addServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpService es=new EmpServiceImpl();
        FileUploadEmp fu=new FileUploadEmpImpl();
        if(ServletFileUpload.isMultipartContent(request)){//判断是否是多部件表单
            //创建缓存对象
            DiskFileItemFactory dif=new DiskFileItemFactory();
            //创建上传的核心工具类
            ServletFileUpload sfu=new ServletFileUpload(dif);
            //解析request请求
            List<FileItem> fs= null;
            try {
                fs = sfu.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            int flag=es.addEmp(fu.upload(fs));

            response.sendRedirect("empMainServlet");
            //http://localhost:8090/testServletLogin/emp/addEmp.jsp
        }else{
            response.sendRedirect("../addEmp.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
