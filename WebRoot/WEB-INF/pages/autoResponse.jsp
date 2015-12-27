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
                <a href="loginController/toCreateTextInfo" class="list-group-item">新建文字消息</a>
            </div>
            <div class="list-group">
                <a class="list-group-item">
                    <span class="glyphicon glyphicon-th-large"></span> 自动回复设置
                </a>
                <a href="loginController/toAutoResponse" class="list-group-item list-group-item-info">自动回复</a>
            </div>
        </div><!-- /.col-sm-3 end -->

        <div class="col-sm-9 col-md-10 col-sm-offset-3 col-md-offset-2 sys-con">

            <div class="tab-content">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">被添加自动回复</a></li>
                    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">消息自动回复</a></li>
                    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">关键词自动回复</a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active" id="home">
                        <div class="auto-resp">
                            <div class="panel-heading tab-head">
                                <ul class="nav nav-pills" role="tablist">
                                    <li class="active">
                                        <a class="tab-nav" href="#sText" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-pencil"></span> 文字</a>
                                    </li>
                                    <li>
                                        <a class="tab-nav" href="#sPic" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-picture"></span> 图文</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="panel panel-default panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="sText">
                                        <table class="table table-striped table-info">
                                            <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>描述内容</th>
                                                <th width="200">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="textListFirst">
                                            </tbody>
                                        </table>
                                        <!-- /.table end -->
                                        <nav class="text-center">
                                            <ul class="pagination" id="paginationListTextFirst">
                                            </ul>
                                        </nav><!-- /.page end -->
                                    </div><!-- /.tab-pane end -->

                                    <div class="tab-pane fade" id="sPic">
                                        <table class="table table-striped table-info">
                                            <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>消息头图</th>
                                                <th>标题</th>
                                                <th>描述内容</th>
                                                <th>原文链接</th>
                                                <th width="200">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="articleListFirst">
                                            </tbody>
                                        </table>
                                        <nav class="text-center">
							                <ul class="pagination" id="paginationListArticleFirst">
							                </ul>
							            </nav>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="profile">
                        <div class="auto-resp">
                            <div class="panel-heading tab-head">
                                <ul class="nav nav-pills" role="tablist">
                                    <li class="active">
                                        <a class="tab-nav" href="#sTextTwo" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-pencil"></span> 文字</a>
                                    </li>
                                    <li>
                                        <a class="tab-nav" href="#sPicTwo" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-picture"></span> 图文</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="panel panel-default panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="sTextTwo">
                                        <table class="table table-striped table-info">
                                            <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>描述内容</th>
                                                <th width="200">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="textListAuto">
                                            </tbody>
                                        </table>
                                        <!-- /.table end -->
                                        <nav class="text-center">
                                            <ul class="pagination" id="paginationListTextAuto">
                                            </ul>
                                        </nav><!-- /.page end -->
                                    </div><!-- /.tab-pane end -->

                                    <div class="tab-pane fade" id="sPicTwo">
                                        <table class="table table-striped table-info">
                                            <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>消息头图</th>
                                                <th>标题</th>
                                                <th>描述内容</th>
                                                <th>原文链接</th>
                                                <th width="200">操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="articleListAuto">
                                            </tbody>
                                        </table>
                                        <nav class="text-center">
							                <ul class="pagination" id="paginationListArticleAuto">
							                </ul>
							            </nav>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="messages">
                        <div class="auto-resp" id="keywordOpt">
                            <div class="form-group">
                                <div class="input-group">
                                    <input class="form-control" type="text" id="keywordInput"/>
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" id="addKeywordBtn">添加关键字</button>
                                    </span>
                                </div>
                                <p class="text-muted js_editTip">还可以输入<em>30</em>个关键字</p>
                            </div>
                            <div class="form-group">
                                <div class="text-info edit-key" id="keywordsList">

                                </div>

                            </div>

                        </div><!-- /.auto-resp end -->

                        <br/>
                        <div class="tab-head panel">
                            <h4 class="text-info padding"><span class="glyphicon glyphicon-signal"></span> 您将通过点击上面的关键字自动匹配对应的关联信息</h4>
                        </div>

                        <ul class="nav nav-tabs" role="tablist">
                            <li class="active">
                                <a class="tab-nav" href="#keyTextInfo" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-pencil"></span> 文字</a>
                            </li>
                            <li>
                                <a class="tab-nav" href="#keyPicInfo" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-picture"></span> 图文</a>
                            </li>
                        </ul>

                        <div class="tab-content keypanel">
                            <div class="tab-pane fade in active" id="keyTextInfo">
                                <!-- <div class="tab-head btn-block">
                                    <button class="btn btn-info btn-sm"><span class="glyphicon glyphicon-cog"></span> 设置关联</button>
                                    <button class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th"></span> 取消关联</button>
                                </div> -->
                                <table class="table table-striped table-info">
                                    <thead>
                                    <tr>
                                        <!-- <th>
                                            <label><input type="checkbox"/>全选</label>
                                        </th> -->
                                        <th>id</th>
                                        <th>描述内容</th>
                                        <th>状态/操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="textListKey">
                                    </tbody>
                                </table>
                                <nav class="text-center">
                                            <ul class="pagination" id="paginationListTextKey">
                                            </ul>
                                        </nav><!-- /.page end -->

                            </div>
                            <div class="tab-pane fade" id="keyPicInfo">
                                <!-- <div class="tab-head btn-block">
                                    <button class="btn btn-info btn-sm"><span class="glyphicon glyphicon-cog"></span> 设置关联</button>
                                    <button class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th"></span> 取消关联</button>
                                </div> -->
                                <table class="table table-striped table-info">
                                    <thead>
                                    <tr>
                                        <!-- <th>
                                            <label><input type="checkbox"/>全选</label>
                                        </th> -->
                                        <th>id</th>
                                        <th>消息头图</th>
                                        <th>标题</th>
                                        <th>描述内容</th>
                                        <th>原文链接</th>
                                        <th>状态/操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="articleListKey">
                                    </tbody>
                                </table>
                                <nav class="text-center">
                                            <ul class="pagination" id="paginationListArticleKey">
                                            </ul>
                                        </nav><!-- /.page end -->

                            </div>

                        </div>

                    </div>
                </div>
            </div>



        </div><!-- /.col-sm-9 end-->

    </div>

</div>
<script type="text/javascript" src="content/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="content/framework/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="content/js/sys.js"></script>
<script type="text/javascript" src="content/jsp/loadTextFirst.js"></script>
<script type="text/javascript" src="content/jsp/loadArticleFirst.js"></script>
<script type="text/javascript" src="content/jsp/loadTextAuto.js"></script>
<script type="text/javascript" src="content/jsp/loadArticleAuto.js"></script>
<script type="text/javascript" src="content/jsp/loadTextKey.js"></script>
<script type="text/javascript" src="content/jsp/loadArticleKey.js"></script>
<script type="text/javascript">
$(function(){

     loadTextFirst(1);
    loadArticleFirst(1);
    loadTextAuto(1);
    loadArticleAuto(1);
    loadTextKey(1,"");
    loadArticleKey(1,"");
    
});

</script>

</body>
</html>