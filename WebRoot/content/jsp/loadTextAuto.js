function loadTextAuto(curPage){
    $.ajax({
    	   type: "post",
           url: "massageController/loadText",
           async:false,
           data: {
               "curPage":curPage
           },
           dataType: "json",
           success: function(msg){
                   var listTr = $("#textListAuto");
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
					                    	"keyWordId":"morenhuifuid"
					                    },
					                    dataType: "json",
					                    success: function(msg){
					                            var data1 = eval(msg['data']);
					                            listTr.append("<tr><th scope='row'>" + id + "</th><td>" + data.list[i].content
					                            		+ "</td><td>" 
					                            		+ '<a href="javascript:void(0)" id="relatetextauto'+data.list[i].id+'" onclick="relateAuto('+ "'" + data.list[i].id + "'" +')" class="btn btn-sm btn-info">设置关联<span id="relatetextautoSpan'+data.list[i].id+'" class="glyphicon glyphicon-cog"></span></a>'
					                            		+ '<a href="javascript:void(0)" id="deletetextauto'+data.list[i].id+'" onclick="deleteAuto('+ "'" + data.list[i].id + "'" +')"class="btn btn-sm btn-default disabled">取消关联</a></td></tr>');
					         			            for (var j = 0; j < data1.length; j++) {
					         			            	if (data.list[i].id==data1[j]){
					         			            		$("#relatetextauto"+data.list[i].id).removeClass("btn-info").addClass("btn-success disabled").html('关联成功<span class="glyphicon glyphicon-ok"></span>');
					         			            		$("#deletetextauto"+data.list[i].id).removeClass("disabled");
					         			            	}
					         			            }
					                    },
					                    error: function(data){
					                        alert("系统错误，请联系管理员");
					                    }
					             });
			            	id = id + 1;
			            }
			            pagerUtilTextAuto(data.curPage, data.end, data.pageSize,
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

function pagerUtilTextAuto(curPage, end, pageSize, start, totalPage, totalRow) {
    var pageUL = $("#paginationListTextAuto");
    pageUL.html("");
    pageUL.append("<li><a href='javascript:;' onclick='upLoadTextAutoPage(" + curPage + ")' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
    //显示分页提示个数
    var Flag = 5;
    if (curPage <= 3) {
        if (totalPage < 5) {
            for (var j = 0; j < totalPage; j++) {
                pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadTextAuto(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
            }
        } else {
            for (var j = 0; j < Flag; j++) {
                pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadTextAuto(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
            }
        }
        colorFlag = curPage;
    } else if (curPage > totalPage - 3) {
        for (var j = totalPage - 5 >= 0 ? totalPage - 5 : totalPage - 4; j < totalPage; j++) {
            pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadTextAuto(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
        }
        colorFlag = curPage;
    } else {
        for (var j = curPage - 2; j < curPage + 3; j++) {
            pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadTextAuto(" + j + ")'>" + j + "</a></li>");
        }
        colorFlag = curPage + 1;
    }
    pageUL.append("<li><a href='javascript:;' onclick='DownLoadTextAutoPage(" + curPage + ',' + totalPage + ")' aria-label='Next' ><span aria-hidden='true'>&raquo;</span></a></li>");
    $('#' + colorFlag).addClass('active');
}

function upLoadTextAutoPage(curPage) {
    if (curPage > 1) {
        var startPage = curPage - 1;
        loadTextAuto(startPage);
    }
}

function DownLoadTextAutoPage(curPage, totalPage) {
    if (curPage < totalPage) {
        var endPage = curPage + 1;
        loadTextAuto(endPage);
    }
}

function deleteAuto(id){
	$.ajax({
	    type: "post",
        url: "relationController/deleteFirst",
        async:false,
        data: {
            "massageId":id,
            "keyWordId":"morenhuifuid"
        },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		loadTextAuto(1);
        		loadArticleAuto(1);
        	}else{
        		renda.tipMsg.config({width:300,type:'alert',msg:'取消关联失败，请稍后再试'});
        	}
        },
        error: function(data){
        	renda.tipMsg.config({width:300,type:'alert',msg:'取消关联失败，请稍后再试'});
        }
    });
}

function relateAuto(id){
	$.ajax({
	    type: "post",
        url: "relationController/relationFirst",
        async:false,
        data: {
            "massageId":id,
            "keyWordId":"morenhuifuid"
        },
        dataType: "json",
        success: function(data){
        	if(data['success']){
        		loadTextAuto(1);
        		loadArticleAuto(1);
        	}else{
        		renda.tipMsg.config({width:300,type:'alert',msg:'设置关联失败，请稍后再试'});
        	}
        },
        error: function(data){
        	renda.tipMsg.config({width:300,type:'alert',msg:'设置关联失败，请稍后再试'});
        }
    });
}