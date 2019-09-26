package com.zl.filter;

import com.zl.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class UserLoginFilter implements Filter {
    public void destroy() {
        System.out.println("用户是否登录过滤器销毁....");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        User loginUser= (User) request.getSession().getAttribute("loginUser");
        String path=request.getServletPath();
        System.out.println("当前拦截到的请求为:"+path);
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        if(loginUser!=null || "/login.jsp".equals(path) || "/emp/loginServlet".equals(path)){
            //说明用户已经登录:放行
            chain.doFilter(request,response);
        }else{
            /**
             * http://localhost:8090/testServletLogin/login.jsp
             *
             * http://localhost:8090/testServletLogin/emp/login.jsp
             */
            response.sendRedirect(basePath+"login.jsp?error="+ URLEncoder.encode("非法请求,请先登录!!","utf-8"));
        }
    }
    public void init(FilterConfig config) throws ServletException {
        System.out.println("用户是否登录过滤器创建....");
    }

}
