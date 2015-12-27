function loadTextFirst(curPage){
    $.ajax({
    	   type: "post",
           url: "massageController/loadText",
           async:false,
           data: {
               "curPage":curPage
           },
           dataType: "json",
           success: function(msg){
                   var listTr = $("#textListFirst");
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
					                    	"keyWordId":"shouciguanzhuid"
					                    },
					                    dataType: "json",
					                    success: function(msg){
					                            var data1 = eval(msg['data']);
					                            listTr.append("<tr><th scope='row'>" + id + "</th><td>" + data.list[i].content
					                            		+ "</td><td>" 
					                            		+ '<a href="javascript:void(0)" id="relatetextfirst'+data.list[i].id+'" onclick="relateFirst('+ "'" + data.list[i].id + "'" +')" class="btn btn-sm btn-info">设置关联<span id="relatetextfirstSpan'+data.list[i].id+'" class="glyphicon glyphicon-cog"></span></a>'
					                            		+ '<a href="javascript:void(0)" id="deletetextfirst'+data.list[i].id+'" onclick="deleteFirst('+ "'" + data.list[i].id + "'" +')"class="btn btn-sm btn-default disabled">取消关联</a></td></tr>');
					         			            for (var j = 0; j < data1.length; j++) {
					         			            	if (data.list[i].id==data1[j]){
					         			            		$("#relatetextfirst"+data.list[i].id).removeClass("btn-info").addClass("btn-success disabled").html('关联成功<span class="glyphicon glyphicon-ok"></span>');
					         			            		$("#deletetextfirst"+data.list[i].id).removeClass("disabled");
					         			            	}
					         			            }
					                    },
					                    error: function(data){
					                        alert("系统错误，请联系管理员");
					                    }
					             });
			            	id = id + 1;
			            }
			            pagerUtilTextFirst(data.curPage, data.end, data.pageSize,
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

function pagerUtilTextFirst(curPage, end, pageSize, start, totalPage, totalRow) {
    var pageUL = $("#paginationListTextFirst");
    pageUL.html("");
    pageUL.append("<li><a href='javascript:;' onclick='upLoadTextFirstPage(" + curPage + ")' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
    //显示分页提示个数
    var Flag = 5;
    if (curPage <= 3) {
        if (totalPage < 5) {
            for (var j = 0; j < totalPage; j++) {
                pageUL.append("<li id='TextFirst" + (j + 1) + "'><a href='javascript:;' onclick='loadTextFirst(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
            }
        } else {
            for (var j = 0; j < Flag; j++) {
                pageUL.append("<li id='TextFirst" + (j + 1) + "'><a href='javascript:;' onclick='loadTextFirst(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
            }
        }
        colorFlagTextFirst = "TextFirst"+curPage;
    } else if (curPage > totalPage - 3) {
        for (var j = totalPage - 5 >= 0 ? totalPage - 5 : totalPage - 4; j < totalPage; j++) {
            pageUL.append("<li id='TextFirst" + (j + 1) + "'><a href='javascript:;' onclick='loadTextFirst(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
        }
        colorFlagTextFirst = "TextFirst"+curPage;
    } else {
        for (var j = curPage - 2; j < curPage + 3; j++) {
            pageUL.append("<li id='TextFirst" + (j + 1) + "'><a href='javascript:;' onclick='loadTextFirst(" + j + ")'>" + j + "</a></li>");
        }
        colorFlagTextFirst = "TextFirst"+curPage + 1;
    }
    pageUL.append("<li><a href='javascript:;' onclick='DownLoadTextFirstPage(" + curPage + ',' + totalPage + ")' aria-label='Next' ><span aria-hidden='true'>&raquo;</span></a></li>");
    $('#' + colorFlagTextFirst).addClass('active');
}

function upLoadTextFirstPage(curPage) {
    if (curPage > 1) {
        var startPage = curPage - 1;
        loadTextFirst(startPage);
    }
}

function DownLoadTextFirstPage(curPage, totalPage) {
    if (curPage < totalPage) {
        var endPage = curPage + 1;
        loadTextFirst(endPage);
    }
}

function deleteFirst(id){
		$.ajax({
		    type: "post",
	        url: "relationController/deleteFirst",
	        async:false,
	        data: {
	            "massageId":id,
	            "keyWordId":"shouciguanzhuid"
	        },
	        dataType: "json",
	        success: function(data){
	        	if(data['success']){
	        		loadTextFirst(1);
	        		loadArticleFirst(1);
	        	}else{
	        		renda.tipMsg.config({width:300,type:'alert',msg:'取消关联失败，请稍后再试'});
	        	}
	        },
	        error: function(data){
	        	renda.tipMsg.config({width:300,type:'alert',msg:'取消关联失败，请稍后再试'});
	        }
	    });
}

function relateFirst(id){
		$.ajax({
		    type: "post",
	        url: "relationController/relationFirst",
	        async:false,
	        data: {
	            "massageId":id,
	            "keyWordId":"shouciguanzhuid"
	        },
	        dataType: "json",
	        success: function(data){
	        	if(data['success']){
	        		loadTextFirst(1);
	        		loadArticleFirst(1);
	        	}else{
	        		renda.tipMsg.config({width:300,type:'alert',msg:'设置关联失败，请稍后再试'});
	        	}
	        },
	        error: function(data){
	        	renda.tipMsg.config({width:300,type:'alert',msg:'设置关联失败，请稍后再试'});
	        }
	    });
}
