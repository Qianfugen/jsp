package cn.qianfg.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@Service
public class BaseServlet extends HttpServlet {
    private ApplicationContext app;
    @Override
    public void init() throws ServletException {
        System.out.println("aaaaaaa");
        app= WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }
    public Object getBean(Class c){
        return app.getBean(c);
    }
}
