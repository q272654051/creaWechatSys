<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<jsp:include page="/content/jsp/header.jsp"/>
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">人员信息</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">人员信息</h1>
			</div>
		</div><!--/.row-->
				
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">员工信息表<button type="button" onclick="addEmployeeForm()">添加</button></div>
					<div class="panel-body">
						<table data-toggle="table" data-url="content/index/tables/data1.json"  data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
						    <thead>
						    <tr>
						        <th data-field="number" data-sortable="true">员工编号</th>
						        <th data-field="name" data-sortable="true">员工姓名</th>
						        <th data-field="gender"  data-sortable="true">员工性别</th>
						        <th data-field="job" data-sortable="true">员工职务</th>
						        <th data-field="entryTime"  data-sortable="true">入职时间</th>
						        <th data-field="status"  data-sortable="true">工作状态</th>
						        <th data-field="remarks"  data-sortable="true">备注</th>
						        <th data-field="edit"  data-sortable="false">编辑</th>
						    </tr>
						    </thead>
						</table>
					</div>
				</div>
			</div>
		</div><!--/.row-->	
		
	</div><!--/.main-->

	<script src="content/index/js/jquery-1.11.1.min.js"></script>
	<script src="content/index/js/bootstrap.min.js"></script>
	<script src="content/index/js/chart.min.js"></script>
	<script src="content/index/js/chart-data.js"></script>
	<script src="content/index/js/easypiechart.js"></script>
	<script src="content/index/js/easypiechart-data.js"></script>
	<script src="content/index/js/bootstrap-datepicker.js"></script>
	<script src="content/index/js/bootstrap-table.js"></script>
	<script src="content/plugin/layer/layer.js" type="text/javascript"></script>
	<script src="content/js/employee.js"></script>
	<script>
	

		!function ($) {
			$(document).on("click","ul.nav li.parent > a > span.icon", function(){		  
				$(this).find('em:first').toggleClass("glyphicon-minus");	  
			}); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>	
</body>

</html>
