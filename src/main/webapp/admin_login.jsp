<%--
  Created by IntelliJ IDEA.
  User: Gabriel
  Date: 2018/5/23
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>博客后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="asset/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="asset/css/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="asset/css/component.css" />
    <script>
        function formonClick() {
            document.getElementById('login_form').submit();
        }
    </script>
</head>
<body>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>博客后台管理系统</h3>
                <form action="Admin_login.action" name="login_form" id="login_form" method="post">
                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input name="user.userName" class="text" style="color: #FFFFFF !important" type="text"
                               placeholder="请输入账户">
                    </div>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input name="user.password" class="text"
                               style="color: #FFFFFF !important; position:absolute; z-index:100;" value=""
                               type="password" placeholder="请输入密码">
                    </div>
                    <div class="mb2"><a class="act-but submit" onclick="formonClick()" style="color: #FFFFFF">登录</a></div>
                    <font color="#ff8c00">${error}</font>
                </form>
            </div>
        </div>
    </div>
</div><!-- /container -->
<script src="asset/js/TweenLite.min.js"></script>
<script src="asset/js/EasePack.min.js"></script>
<script src="asset/js/rAF.js"></script>
<script src="asset/js/demo-1.js"></script>
</body>
</html>
