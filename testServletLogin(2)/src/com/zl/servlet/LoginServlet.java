package com.zl.servlet;

import com.zl.pojo.User;
import com.zl.service.UserService;
import com.zl.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/emp/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名和密码
        String name=request.getParameter("name");
        String pwd=request.getParameter("pwd");
        //接受用户是否想要保存用户名密码
        String isno=request.getParameter("isno");
        //调用JDBC完成对数据库的查
        User u = new User();
        u.setName(name);
        u.setPwd(pwd);
        //调用jdbc
        UserService us=new UserServiceImpl();
        u=us.login(u);
        if(u!=null){
            //说明登录成功,判断用户是否保存cookie
            if("yes".equals(isno)){
                //说明用户需要保存
                Cookie cName=new Cookie("cName", URLEncoder.encode(name,"utf-8"));//保存用户名,cookie默认不能保存汉字
                Cookie cPwd=new Cookie("cPwd",pwd);//保存密码
                //设置cookie有效时间
                cName.setMaxAge(10*24*60*60);
                cPwd.setMaxAge(10*24*60*60);
                //设置cookie跟路径
                cName.setPath("/testServletLogin");
                cPwd.setPath("/testServletLogin");
                //通过response发送给客户端
                response.addCookie(cName);
                response.addCookie(cPwd);
            }
            /**
             * 开启一次会话:把登陆用户信息保存在session作用域中
             *      注意:session对象已经创建好了,在我们访问login.jsp的时候就已经创建了
             */
            request.getSession().setAttribute("loginUser",u);
            //请求转发
            response.sendRedirect("empMainServlet");
        }else{
            //用户名或者密码错误
            response.sendRedirect("../login.jsp?error="+URLEncoder.encode("用户名或者密码错误!!!","utf-8"));
        }
    }
}
