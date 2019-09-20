<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="qianfg.cn.polo.User" %>
<%@ page import="qianfg.cn.service.UserService" %>
<%@ page import="qianfg.cn.service.impl.UserServiceImpl" %>
<%@ page import="java.net.URLEncoder" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>验证</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <%
        //统一设置字符为"utf-8",防止乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取登录页面值
        String name=request.getParameter("username");
        String pwd=request.getParameter("pwd");
        String isNo=request.getParameter("isno");
        //封装成user对象
        User user=new User();
        user.setName(name);
        user.setPwd(pwd);
        UserService us=new UserServiceImpl();
        user=us.login(user);
        if(user!=null){
            //如果yes,说明勾选了保存用户名和密码
            if("yes".equals(isNo)){
                //cookie不能保存中文
                Cookie cName=new Cookie("cName", URLEncoder.encode(name,"utf-8"));
                Cookie cPwd=new Cookie("cPwd",URLEncoder.encode(pwd,"utf-8"));
                //设置cookie生命周期,单位为s
                cName.setMaxAge(10*24*60*60);
                cPwd.setMaxAge(10*24*60*60);
                //设置cookie路径
                cName.setPath("/jspEmp");
                cPwd.setPath("/jspEmp");
                //通过response发送至客户端
                response.addCookie(cName);
                response.addCookie(cPwd);
            }
            //验证成功,跳转到主页面
            //重定向
//            response.sendRedirect("main.jsp");
            //请求转发
                session.setAttribute("loginUser",user);
                request.getRequestDispatcher("main.jsp").forward(request,response);
        }else{
            response.sendRedirect("login.jsp?error="+URLEncoder.encode("用户名或密码输入错误","utf-8"));
        }
    %>
</body>
</html>
