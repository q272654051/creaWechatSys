function loadTextKey(curPage,keyWordid){
    $.ajax({
    	   type: "post",
           url: "massageController/loadText",
           async:false,
           data: {
               "curPage":curPage
           },
           dataType: "json",
           success: function(msg){
                   var listTr = $("#textListKey");
                   listTr.html("");
                   var data = eval(msg['data']);
                   var id = (curPage-1)*5+1;
                   if (data.list.length>0){
			            for (var i = 0; i < data.list.length; i++) {
					            	$.ajax({
					             	   type: "post",
					                    url: "relationController/loadTextByKeyWord",
					                    async:false,
					                    data: {
					                    	"keyWordId":keyWordid
					                    },
					                    dataType: "json",
					                    success: function(msg){
					                            var data1 = eval(msg['data']);
					                            listTr.append("<tr><td><input type='checkbox'/></td><th scope='row'>" + id + "</th><td>" + data.list[i].content
					                            		+ "</td><td>" 
					                            		+ '<a href="javascript:void(0)" id="relatetextkey'+data.list[i].id+'" onclick="relateTextKey('+ "'" + data.list[i].id + "'" +')" class="btn btn-sm btn-info">设置关联<span id="relatetextkeySpan'+data.list[i].id+'" class="glyphicon glyphicon-cog"></span></a>'
					                            		+ '<a href="javascript:void(0)" id="deletetextkey'+data.list[i].id+'" onclick="deleteTextKey('+ "'" + data.list[i].id + "'" +')"class="btn btn-sm btn-default disabled">取消关联</a></td></tr>');
					         			            for (var j = 0; j < data1.length; j++) {
					         			            	if (data.list[i].id==data1[j]){
					         			            		$("#relatetextkey"+data.list[i].id).removeClass("btn-info").addClass("btn-success disabled").html('关联成功<span class="glyphicon glyphicon-ok"></span>');
					         			            		$("#deletetextkey"+data.list[i].id).removeClass("disabled");
					         			            	}
					         			            }
					                    },
					                    error: function(data){
					                        alert("系统错误，请联系管理员");
					                    }
					             });
			            	id = id + 1;
			            }
			            pagerUtilTextKey(keyWordid,data.curPage, data.end, data.pageSize,
			                    data.start, data.totalPage, data.totalRow);
			            }else{
			            	listTr.append("<tr><td colspan='3' class='text-center'>没有文本消息</td></tr>");
			            }
           },
           error: function(data){
               alert("系统错误，请联系管理员");
           }
    });
}

function pagerUtilTextKey(keyWordid,curPage, end, pageSize, start, totalPage, totalRow) {
    var pageUL = $("#paginationListTextKey");
    pageUL.html("");
    pageUL.append("<li><a href='javascript:;' onclick='upLoadTextKeyPage(" + curPage + ','+keyWordid+")' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
    //显示分页提示个数
    var Flag = 5;
    if (curPage <= 3) {
        if (totalPage < 5) {
            for (var j = 0; j < totalPage; j++) {
                pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadTextKey(" + (j + 1) +','+keyWordid+ ")'>" + (j + 1) + "</a></li>");
            }
        } else {
            for (var j = 0; j < Flag; j++) {
                pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadTextKey(" + (j + 1) +','+keyWordid+ ")'>" + (j + 1) + "</a></li>");
            }
        }
        colorFlag = curPage;
    } else if (curPage > totalPage - 3) {
        for (var j = totalPage - 5 >= 0 ? totalPage - 5 : totalPage - 4; j < totalPage; j++) {
            pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadTextKey(" + (j + 1) +','+keyWordid+ ")'>" + (j + 1) + "</a></li>");
        }
        colorFlag = curPage;
    } else {
        for (var j = curPage - 2; j < curPage + 3; j++) {
            pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadTextKey(" + j +','+keyWordid+ ")'>" + j + "</a></li>");
        }
        colorFlag = curPage + 1;
    }
    pageUL.append("<li><a href='javascript:;' onclick='DownLoadTextKeyPage(" + curPage + ',' + totalPage + ','+keyWordid+")' aria-label='Next' ><span aria-hidden='true'>&raquo;</span></a></li>");
    $('#' + colorFlag).addClass('active');
}

function upLoadTextKeyPage(curPage,keyWordid) {
    if (curPage > 1) {
        var startPage = curPage - 1;
        loadTextKey(startPage,keyWordid);
    }
}

function DownLoadTextKeyPage(curPage, totalPage,keyWordid) {
    if (curPage < totalPage) {
        var endPage = curPage + 1;
        loadTextKey(endPage,keyWordid);
    }
}

function deleteText(id){
	flag = window.confirm("确定要删除该信息？");
	if (flag){
		$.ajax({
		    type: "post",
	        url: "massageController/deleteText",
	        async:false,
	        data: {
	            "id":id
	        },
	        dataType: "json",
	        success: function(data){
	        	if(data['success']){
	        		window.location.href = "loginController/toCreateTextInfo";  //加载主页面
	        	}else{
	        		alert("删除错误，请稍后再试");
	        	}
	        },
	        error: function(data){
	        	alert("系统错误，请联系管理员");
	        }
	    });
	}else{
		return false;
	}
}

function editText(id){
	var content = prompt("请输入新的消息:","");
	if (content != null){
		$.ajax({
		    type: "post",
	        url: "massageController/saveOrupdateText",
	        async:false,
	        data: {
	            "id":id,
	            "content":content
	        },
	        dataType: "json",
	        success: function(data){
	        	if(data['success']=="添加成功"){
	        		alert("修改成功");
	        		window.location.href = "loginController/toCreateTextInfo";  //加载主页面
	        	}else{
	        		alert("修改错误，请稍后再试");
	        	}
	        },
	        error: function(data){
	        	alert("系统错误，请联系管理员");
	        }
	    });
	}else {
		return false;
	}
}