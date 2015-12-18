var url = "loginController/tologin";

function login(){
	
	var userName = $("#userName").val();
	var pwd = $("#password").val();
	
	if (userName == "" || userName == null) {
        layer.tips("用户名不能为空！", "#userName", {tips: [2, '#e84c3d'],time:1200});//提交验证
        return;
    }
	
	if (pwd == "" || pwd == null) {
        layer.tips("密码不能为空！", "#password", {tips: [2, '#e84c3d'],time:1200});//提交验证
        return;
    }
	
	$.ajax({
		type: "post",
        url: url,
        async:false,
        data: {
        	"userName":userName,
        	"pwd":pwd
        },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		layer.msg(data['msg'], {icon: 1, time: 1000}, function () {
                    window.location.href = "loginController/loginSuccess";  //加载主页面
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

