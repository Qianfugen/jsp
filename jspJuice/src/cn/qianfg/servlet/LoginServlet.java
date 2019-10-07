package cn.qianfg.servlet;

import cn.qianfg.polo.User;
import cn.qianfg.service.UserService;
import cn.qianfg.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取用户名和密码

        String loginName = request.getParameter("userName");
        String passWord = request.getParameter("pwd");
        String isno = request.getParameter("isno");
        User user = new User();
        user.setName(loginName);
        user.setPwd(passWord);

        //验证登录
        UserService us = new UserServiceImpl();
        user = us.login(user);
        if (user != null) {
            if ("yes".equals(isno)) {
                //创建cookie
                Cookie cName = new Cookie("cName", URLEncoder.encode(loginName, "utf-8"));
                Cookie cPwd = new Cookie("cPwd", passWord);
                //设置时间
                cName.setMaxAge(10 * 24 * 60 * 60);
                cPwd.setMaxAge(10 * 24 * 60 * 60);
                //设置保存路径
                cName.setPath("/jspJuice");
                cPwd.setPath("/jspJuice");
                //添加cookie到response
                response.addCookie(cName);
                response.addCookie(cPwd);
                System.out.println("cookie已保存");
            }
            //把user存入session
            request.getSession().setAttribute("loginUser", user);
            //跳转到JuiceMainServlet
            request.getRequestDispatcher("/juiceMainServlet").forward(request, response);
        } else {
            request.getRequestDispatcher("login.jsp?error=" + URLEncoder.encode("用户名或密码错误!", "utf-8")).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
