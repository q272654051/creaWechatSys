<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>资产管理系统登录</title>
        <base href="<%=basePath%>"></base>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="content/assets/css/reset.css">
        <link rel="stylesheet" href="content/assets/css/supersized.css">
        <link rel="stylesheet" href="content/assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="page-container">
            <h1>Login</h1>
            <form action="" method="post" id="userForm">
                <input type="text" name="userName" id="userName" class="username" placeholder="Username">
                <input type="password" name="password" id="password" class="password" placeholder="Password">
                <button type="button" onclick="login()">登录</button>
                <div class="error"><span>+</span></div>
            </form>
            <div class="connect">
                <!-- <p>Or connect with:</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p> -->
            </div>
        </div>
        <div align="center">版权所有</div>

        <!-- Javascript -->
        <script src="content/assets/js/jquery-1.8.2.min.js"></script>
        <script src="content/assets/js/supersized.3.2.7.min.js"></script>
        <script src="content/assets/js/supersized-init.js"></script>
        <script src="content/assets/js/scripts.js"></script>
        <script src="content/plugin/layer/layer.js" type="text/javascript"></script>
        <script src="content/js/login.js"></script>

    </body>

</html>

