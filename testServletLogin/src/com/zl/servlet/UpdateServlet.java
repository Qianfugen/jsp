package com.zl.servlet;

import com.zl.pojo.Emp;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/emp/updateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpService es=new EmpServiceImpl();
        FileUploadEmp fu=new FileUploadEmpImpl();
        Emp emp=null;
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
            emp=fu.upload(fs);
            int flag=es.updateEmp(emp);
            response.sendRedirect("empMainServlet");
        }else{
            String empNo=request.getParameter("empNo");
            response.sendRedirect("toUpdateServlet?empNo="+empNo);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
