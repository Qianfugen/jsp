<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.zl.pojo.User" %>
<%@ page import="com.zl.dao.impl.UserDaoImpl" %>
<%@ page import="com.zl.dao.UserDao" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.zl.service.UserService" %>
<%@ page import="com.zl.service.impl.UserServiceImpl" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <%--用来处理用户的登录请求--%>
    <%
        //处理post方式中文乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取用户名和密码
        String name=request.getParameter("name");
        String pwd=request.getParameter("pwd");
        //接受用户是否想要保存用户名密码
        String isno=request.getParameter("isno");
        //调用JDBC完成对数据库的查询
        User u=new User();
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
                cName.setPath("/jspEmp");
                cPwd.setPath("/jspEmp");
                //通过response发送给客户端
                response.addCookie(cName);
                response.addCookie(cPwd);
            }
            /**
             * 开启一次会话:把登陆用户信息保存在session作用域中
             *      注意:session对象已经创建好了,在我们访问login.jsp的时候就已经创建了
             */
            session.setAttribute("loginUser",u);
            //请求转发
            response.sendRedirect("main.jsp");
        }else{
            //用户名或者密码错误
            response.sendRedirect("login.jsp?error="+URLEncoder.encode("用户名或者密码错误","utf-8"));
        }
    %>
</body>
</html>