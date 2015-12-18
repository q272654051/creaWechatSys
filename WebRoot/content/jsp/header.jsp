<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<base href="<%=basePath%>"></base>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>资产管理系统</title>

<link href="content/index/css/bootstrap.min.css" rel="stylesheet">
<link href="content/index/css/datepicker3.css" rel="stylesheet">
<link href="content/index/css/styles.css" rel="stylesheet">
<link rel="stylesheet" href="content/plugin/layer/skin/layer.css"/>

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
<script src="content/assets/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">

	$(function(){
		 var title = $(".row .page-header").html() + "";
		var obj_lis = $("#title_name li");
		for(var i=0;i<obj_lis.length;i++){
			var str = obj_lis[i];
			var str2 = str.innerText;
			    str2 += "";
			if (str2.indexOf(title) > -1){
				obj_lis[i].className = "active";
			}
		}
	});
</script>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>XXX</span>资产管理系统</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> ${ sessionScope.user_info.userName} <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="profileController/toprofile"><span class="glyphicon glyphicon-user"></span> 个人中心</a></li>
							<li><a href="profileController/tosetting"><span class="glyphicon glyphicon-cog"></span> 设置</a></li>
							<li><a href="profileController/tologout"><span class="glyphicon glyphicon-log-out"></span> 注销</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul id="title_name"class="nav menu">
			<li ><a href="loginController/loginSuccess"><span class="glyphicon glyphicon-dashboard"></span> 主页</a></li>
			<li class="parent ">
				<a href="#">
					<span class="glyphicon glyphicon-list"></span> 固定资产信息 <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a class="" href="#">
							<span class="glyphicon glyphicon-share-alt"></span> 固定资产类别
						</a>
					</li>
					<li>
						<a class="" href="#">
							<span class="glyphicon glyphicon-share-alt"></span> 固定资产信息
						</a>
					</li>
				</ul>
			</li>
			<li><a href="employeeController/toemployee"><span class="glyphicon glyphicon-th"></span> 人员信息</a></li>
			<li><a href="charts.html"><span class="glyphicon glyphicon-stats"></span> 资产的领用</a></li>
			<li><a href="tables.html"><span class="glyphicon glyphicon-list-alt"></span> 资产的归还</a></li>
			<li class="parent ">
				<a href="#">
					<span class="glyphicon glyphicon-list"></span> 资产信息浏览 <span data-toggle="collapse" href="#sub-item-2" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-2">
					<li>
						<a class="" href="#">
							<span class="glyphicon glyphicon-share-alt"></span> 资产类别
						</a>
					</li>
					<li>
						<a class="" href="#">
							<span class="glyphicon glyphicon-share-alt"></span> 资产查询
						</a>
					</li>
				</ul>
			</li>
			<li role="presentation" class="divider"></li>
			<!-- <li><a href="login.html"><span class="glyphicon glyphicon-user"></span> Login Page</a></li> -->
		</ul>
		<div class="attribution"> <a href="http://139.129.38.56/" title="" target="_blank">版权所有</a></div>
	</div><!--/.sidebar-->
