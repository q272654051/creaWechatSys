<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="content/framework/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="content/css/sys.css"/>
    <style>
        body{ background-color: #eee; }
        .container{ max-width: 600px; }
        h2{ padding-bottom: 10px; }
    </style>
</head>
<body>

<div class="container panel login-form" id="rendaLogin">
    <h2 class="text-center text-success">微信后台管理系统</h2>
    <form class="form-horizontal">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="username" placeholder="字母、数字、下划线">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" placeholder="6-10位英文数字组合">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox">记住密码
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-success" id="submitBtn">登 录</button>
            </div>
        </div>
    </form>
</div>


<script type="text/javascript" src="content/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="content/js/sys.js"></script>

</body>
</html>