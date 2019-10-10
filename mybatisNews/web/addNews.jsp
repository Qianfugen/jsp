<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>添加页面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <form action="add.jsp" method="post" enctype="multipart/form-data">
        新闻编号:<input type="text" name="newsId"><p>
        新闻标题:<input type="text" name="title"><p>
        新闻内容:<input type="text" name="context"><p>
        新闻记者:<input type="text" name="newsMan"><p>
        新闻日期:<input type="date" name="newsDate"><p>
        新闻类型:<select name="typeId">
                    <option value="1">社会</option>
                    <option value="2">娱乐</option>
                    <option value="3">生活</option>
                    <option value="4">体育</option>
                </select><p>
        新闻图片:<input type="file" name="pic"><p>
        <input type="submit" value="添加" onclick="return confirm('确认添加?!!')">
    </form>
</body>
</html>
