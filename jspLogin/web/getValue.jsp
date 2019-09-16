<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="cn.qianfg.polo.User" %>
<%@ page import="cn.qianfg.dao.UserDao" %>
<%@ page import="cn.qianfg.dao.impl.UserDaoImpl" %>
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
<%--用来处理用户的登录请求--%>
<%
    //处理post方式中文乱码
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    //获取用户名和密码
    String name = request.getParameter("name");
    String pwd = request.getParameter("pwd");
    //接受用户是否想要保存用户名密码
    String isno = request.getParameter("isno");
    System.out.println(name + "    " + pwd);
//    String[] hobbys = request.getParameterValues("hobby");
    String str = "";
//    if (hobbys != null && hobbys.length > 0) {
//        for (String hobby : hobbys) {
//            str += hobby + ",";
//        }
//    }
    //调用JDBC完成对数据库的查询
    User u = new User();
    u.setName(name);
    u.setPwd(pwd);
    //调用jdbc
    UserDao ud = new UserDaoImpl();
    System.out.println("ud的值"+ud);
    u = ud.login(u);
    if (u != null) {
        //说明登录成功,判断用户是否保存cookie
        if ("yes".equals(isno)) {
            //说明用户需要保存
            Cookie cName = new Cookie("cName", URLEncoder.encode(name, "utf-8"));//保存用户名,cookie默认不能保存汉字
            System.out.println(URLEncoder.encode(name, "utf-8") + "....................");
            Cookie cPwd = new Cookie("cPwd", pwd);//保存密码
            //设置cookie有效时间
            cName.setMaxAge(10 * 24 * 60 * 60);
            cPwd.setMaxAge(10 * 24 * 60 * 60);
            //设置cookie跟路径
            cName.setPath("/jspLogin");
            cPwd.setPath("/jspLogin");
            //通过response发送给客户端
            response.addCookie(cName);
            response.addCookie(cPwd);
        }
        out.print("登录成功...." + str);
    } else {
        //用户名或者密码错误
        out.print("用户名或者密码错误...");
    }
%>
<%--登录成功以后跳转到首页,首页以表格的形式显示所有的员工信息--%>
</body>
</html>
