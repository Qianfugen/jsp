package cn.qianfg.servlet;

import cn.qianfg.pojo.User;
import cn.qianfg.service.UserService;
import cn.qianfg.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/loginServlet")
public class LoginServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取login.jsp的用户名,密码,cookie
        String name=request.getParameter("username");
        String pwd=request.getParameter("pwd");
        String isno=request.getParameter("isno");
        //调用JDBC完成对数据库的查
        User user= (User) getBean(User.class);
        user.setName(name);
        user.setPwd(pwd);
        //调用JDBC
//        UserService us=new UserServiceImpl();
        UserService us= (UserService) getBean(UserServiceImpl.class);
        user=us.login(user);
        if(user!=null){     //存在该用户,密码正确
            if("yes".equals(isno)){     //勾选了保存登录信息
                //创建cookie
                Cookie cName=new Cookie("cName", URLEncoder.encode(name,"utf-8"));
                Cookie cPwd=new Cookie("cPwd",pwd);
                //设置生命周期
                cName.setMaxAge(10*24*60*60);
                cPwd.setMaxAge(10*24*60*60);
                //设置保存路径
                cName.setPath("/mybatisNews");
                cPwd.setPath("/mybatisNews");
                //添加到response
                response.addCookie(cName);
                response.addCookie(cPwd);
            }
            /**
             * 开启一次会话:把登陆用户信息保存在session作用域中
             *      注意:session对象已经创建好了,在我们访问login.jsp的时候就已经创建了
             */
            request.getSession().setAttribute("loginUser",user);
            //重定向
            response.sendRedirect("userMain.jsp");
        }else{
            response.sendRedirect("login.jsp?errorInfo="+URLEncoder.encode("用户名或密码错误","utf-8"));
        }
    }
}
