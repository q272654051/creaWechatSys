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
    <link rel="stylesheet" href="content/editor/themes/default/default.css" />
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
                <a href="loginController/toSysMain" class="list-group-item">新建图文消息</a>
                <a href="loginController/toCreateTextInfo" class="list-group-item list-group-item-info">新建文字消息</a>
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
                    <div class="col-md-12">
                        <div class="form-group">
                            <textarea class="form-control no-resize" id="content" name="content" cols="30" rows="10" placeholder="请输入描述文字信息"></textarea>
                        </div>
                        <button id="saveBtn" type="button" class="btn btn-info">保 存</button>
                    </div>
                </div>
            </form>

            <div class="page-header">
                <h3>信息列表</h3>
            </div>

            <table class="table table-striped table-info">
                <thead>
                <tr>
                    <th>id</th>
                    <th>描述内容</th>
                    <th width="120">操作</th>
                </tr>
                </thead>
                <tbody id="textList">
                </tbody>
            </table>
            <nav class="text-center">
                <ul class="pagination">
                </ul>
            </nav>
        </div><!-- /.col-sm-9 end-->

    </div>

</div>
<script type="text/javascript" src="content/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="content/framework/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="content/js/sys.js"></script>
<script charset="utf-8" src="content/editor/kindeditor-min.js"></script>
<script charset="utf-8" src="content/editor/lang/zh_CN.js"></script>
<script type="text/javascript" src="content/jsp/textInfo.js"></script>

<script type="text/javascript">
    var editor;
    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="content"]', {
            resizeType : 1,
            allowPreviewEmoticons : false,
            allowImageUpload : false,
            items : ['link']
        });
        K('#saveBtn').click(function(e) {
            //alert(editor.html());
            var content = editor.html();
            var textId = $("#textId").val();
            $.ajax({
                    type: "post",
                    url: "massageController/saveOrupdateText",
                    async:false,
                    data: {
                        "id":textId,
                        "content":content
                    },
                    dataType: "json",
                    success: function(data){
                        if(data['success']){
                            renda.tipMsg.config({width:300,type:'alert',msg:data['success']});
                            window.location.href = "loginController/toCreateTextInfo";  //加载主页面
                        }else{
                            renda.tipMsg.config({width:300,type:'alert',msg:data['success']});
                        }
                    },
                    error: function(data){
                        renda.tipMsg.config({width:300,type:'alert',msg:'系统异常，请稍后再试'});
                    }
                });
        });
    });
    
$(function(){
    loadText(1);
})

</script>
</body>
</html>