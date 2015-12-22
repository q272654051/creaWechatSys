<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <base href="<%=basePath%>"></base>
    <title>仁达微信后台</title>
    <link rel="stylesheet" href="content/framework/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="content/css/sys.css"/>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">仁达微信后台</a>
        </div>
        <div class="navbar-collapse collapse" id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a>欢迎你：${ sessionScope.user_info.userName}</a></li>
                <li><a href="loginController/tologout">退出！</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="list-group">
                <a class="list-group-item">
                    <span class="glyphicon glyphicon-th-large"></span> 新建回复
                </a>
                <a href="loginController/toSysMain" class="list-group-item list-group-item-info">新建图文消息</a>
                <a href="loginController/toCreateTextInfo" class="list-group-item">新建文字消息</a>
            </div>
            <div class="list-group">
                <a class="list-group-item">
                    <span class="glyphicon glyphicon-th-large"></span> 自动回复设置
                </a>
                <a href="loginController/toAutoResponse" class="list-group-item">自动回复</a>
            </div>
        </div><!-- /.col-sm-3 end -->

        <div class="col-sm-9 col-md-10 col-sm-offset-3 col-md-offset-2 sys-con">
            <form action="" method="post" name="form">
                <div class="row">
                    <div class="col-md-2 pic-upload">
                        <a class="thumbnail" id="preview">
                            <img id="headpic" src="images/default.jpg" alt="" width="250" height="150">
                        </a>
                        <div class="form-group file-upload">
                            <input type="file" id="exampleInputFile">
                        </div>
                        <p class="help-block">上传消息头图</p>
                    </div>
                    <div class="col-md-10">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="请输入标题文字信息">
                        </div>
                        <div class="form-group">
                            <textarea class="form-control no-resize" name="" cols="30" rows="10" placeholder="请输入描述文字信息"></textarea>
                        </div>
                        <div class="form-group">
                            <div class="checkbox">
                                <label><input type="checkbox"/>源文链接</label>
                            </div>
                            <input type="text" class="form-control" placeholder="http://"/>
                        </div>
                        <button id="saveArticle" type="button" class="btn btn-info">保 存</button>
                    </div>
                </div>
            </form>

            <div class="page-header">
                <h3>图文信息列表</h3>
            </div>

            <table class="table table-striped table-info">
                <thead>
                <tr>
                    <th>id</th>
                    <th>消息头图</th>
                    <th>标题</th>
                    <th>描述内容</th>
                    <th>原文链接</th>
                    <th width="120">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">5</th>
                    <td><img id="headpic" src="images/default.jpg" alt="" width="50" height=""></td>
                    <td>♫房估估专题 |国内30个城市“住宅投资回报率”大PK!</td>
                    <td>♫房估估专题 |国内30个城市“住宅投资回报率”大PK!♫房估估专题 |国内30个城市“住宅投资回报率”大PK!</td>
                    <td>http://weixin.fungugu.com/</td>
                    <td>
                        <a href="javascript:;" class="btn btn-sm btn-info">修改</a>
                        <a href="javascript:;" class="btn btn-sm btn-warning">删除</a>
                    </td>
                </tr>
                <tr>
                    <th scope="row">4</th>
                    <td><img id="headpic" src="images/default.jpg" alt="" width="50" height=""></td>
                    <td>♫房估估专题 |国内30个城市“住宅投资回报率”大PK!</td>
                    <td>♫房估估专题 |国内30个城市“住宅投资回报率”大PK!♫房估估专题 |国内30个城市“住宅投资回报率”大PK!</td>
                    <td>http://weixin.fungugu.com/</td>
                    <td>
                        <a href="javascript:;" class="btn btn-sm btn-info">修改</a>
                        <a href="javascript:;" class="btn btn-sm btn-warning">删除</a>
                    </td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td><img id="headpic" src="images/default.jpg" alt="" width="50" height=""></td>
                    <td>♫房估估专题 |国内30个城市“住宅投资回报率”大PK!</td>
                    <td>♫房估估专题 |国内30个城市“住宅投资回报率”大PK!♫房估估专题 |国内30个城市“住宅投资回报率”大PK!</td>
                    <td>http://weixin.fungugu.com/</td>
                    <td>
                        <a href="javascript:;" class="btn btn-sm btn-info">修改</a>
                        <a href="javascript:;" class="btn btn-sm btn-warning">删除</a>
                    </td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td><img id="headpic" src="images/default.jpg" alt="" width="50" height=""></td>
                    <td>♫房估估专题 |国内30个城市“住宅投资回报率”大PK!</td>
                    <td>♫房估估专题 |国内30个城市“住宅投资回报率”大PK!♫房估估专题 |国内30个城市“住宅投资回报率”大PK!</td>
                    <td>http://weixin.fungugu.com/</td>
                    <td>
                        <a href="javascript:;" class="btn btn-sm btn-info">修改</a>
                        <a href="javascript:;" class="btn btn-sm btn-warning">删除</a>
                    </td>
                </tr>
                <tr>
                    <th scope="row">1</th>
                    <td><img id="headpic" src="images/default.jpg" alt="" width="50" height=""></td>
                    <td>♫房估估专题 |国内30个城市“住宅投资回报率”大PK!</td>
                    <td>♫房估估专题 |国内30个城市“住宅投资回报率”大PK!♫房估估专题 |国内30个城市“住宅投资回报率”大PK!</td>
                    <td>http://weixin.fungugu.com/</td>
                    <td>
                        <a href="javascript:;" class="btn btn-sm btn-info">修改</a>
                        <a href="javascript:;" class="btn btn-sm btn-warning">删除</a>
                    </td>
                </tr>
                </tbody>
            </table>
            <nav class="text-center">
                <ul class="pagination">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div><!-- /.col-sm-9 end-->

    </div>

</div>
<script type="text/javascript" src="content/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="content/framework/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="content/js/sys.js"></script>
<script type="text/javascript"></script>
<script type="text/javascript">

$("#saveArticle").click(function() {
	var filePath = $("#headpic").attr("src");
	console.log(filePath);

})
</script>
</body>
</html>