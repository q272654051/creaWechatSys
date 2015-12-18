
$(function(){

});


function editEmployee(){
	alert("qwe");
}

function addEmployeeForm(){
	var index = layer.open({
		area: ['350px', '400px'],
		title :'添加员工',
	    content: "<form id='addEmployeeForm'>" +
	    		"员工编号：<input type='text' id='number'><br>" +
	    		"员工姓名：<input type='text' id='name'><br>" +
	    		"员工性别：<input type='radio' id='gender' name='gender' value='男'/>男<input type='radio' id='gender' name='gender' value='女'/>女<br>" +
	    		"员工职务：<input type='text' id='job'><br>" +
	    		"入职时间：<input type='date' id='entryTime'><br>" +
	    		"员工状态：<input type='radio' id='status' name='status' value='试用'/>试用<input type='radio' id='status' name='status' value='在职'/>在职" +
	    		"<input type='radio' id='status' name='status' value='离职'/>离职<input type='radio' id='status' name='status' value='实习'/>实习<br>" +
	    		"备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：<input type='text' id='remarks'><br>" +
	    		"</form>",
	    btn: ['确定', '取消'],
	    btn1: function(index, layero){
	    	$.ajax({
	    		type: "post",
	            url: "employeeController/addEmployee",
	            async:false,
	            data: {
	            	"number" : $("#number").val(),
	            	"name" : $("#name").val(),
	            	"gender" : $("#gender").val(),
	            	"job" : $("#job").val(),
	            	"entryTime" : $("#entryTime").val(),
	            	"status" : $("#status").val(),
	            	"remarks" : $("#remarks").val()
	            },
	            dataType: "json",
	            success: function(data){
	            	if(data['success']){
	            		layer.msg(data['msg'], {icon: 1, time: 1000}, function () {
	                        window.location.href = "employeeController/toemployee";  //加载员工信息页面
	                    });
	            	}else{
	            		layer.msg(data['msg'], {icon: 2, time:1000});
	            	}
	            },
	            error: function(data){
	            	layer.msg(data['msg'], {icon: 2, time: 1000});
	            }
	    	});
	    },
	    btn2: function(index){
	    	layer.close(index);
	    },
	});
}

function addEmployee(){
	$.ajax({
		type: "post",
        url: "employeeController/addEmployee",
        async:false,
        data: $("#addEmployeeForm").serialize(),
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		layer.msg(data['msg'], {icon: 1, time: 1000}, function () {
                    window.location.href = "employeeController/toemployee";  //加载员工信息页面
                });
        	}else{
        		layer.msg(data['msg'], {icon: 2, time:1000});
        	}
        },
        error: function(data){
        	layer.msg(data['msg'], {icon: 2, time: 1000});
        }
	});
}