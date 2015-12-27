function loadArticle(curPage){
    $.ajax({
    type: "post",
           url: "massageController/loadArticle",
           async:false,
           data: {
               "curPage":curPage
           },
           dataType: "json",
           success: function(msg){
                   var listTr = $("#articleList");
                   listTr.html("");
                   var data = eval(msg['data']);
                   var id = (curPage-1)*5+1;
                   if (data.list.length>0){
			            for (var i = 0; i < data.list.length; i++) {
			            		listTr.append("<tr><th scope='row'>" + id + "</th><td>" 
			            		+ "<img id='headpic' src='" + data.list[i].picUrl + "' alt='' width='50' height=''>"
			            		+ "</td><td>" + data.list[i].title
			            		+ "</td><td>" + data.list[i].description
			            		+ "</td><td>" + data.list[i].url
			            		+ "</td><td>" 
			            		+ '<a href="javascript:;" onclick="editarticle('+ "'" + data.list[i].id + "','" + data.list[i].picUrl +"','" + data.list[i].title +"','" +data.list[i].description +"','" + data.list[i].url +"'" + ')" class="btn btn-sm btn-info">修改</a>'
			            		+ '<a href="javascript:void(0)" onclick="deletearticle('+ "'" + data.list[i].id + "'" +')"class="btn btn-sm btn-warning">删除</a></td></tr>');
			            id = id + 1;
			            }
			            pagerUtil(data.curPage, data.end, data.pageSize,
			                    data.start, data.totalPage, data.totalRow);
			            }else{
			            	listTr.append("<tr><td style='padding:10px; font-size:14px;'>没有图文消息</td></tr>");
			            }
           },
           error: function(data){
               alert("系统错误，请联系管理员");
           }
    });
}

function pagerUtil(curPage, end, pageSize, start, totalPage, totalRow) {
    var pageUL = $(".pagination");
    pageUL.html("");
    pageUL.append("<li><a href='javascript:;' onclick='upLoadPage(" + curPage + ")' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
    //显示分页提示个数
    var Flag = 5;
    if (curPage <= 3) {
        if (totalPage < 5) {
            for (var j = 0; j < totalPage; j++) {
                pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadArticle(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
            }
        } else {
            for (var j = 0; j < Flag; j++) {
                pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadArticle(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
            }
        }
        colorFlag = curPage;
    } else if (curPage > totalPage - 3) {
        for (var j = totalPage - 5 >= 0 ? totalPage - 5 : totalPage - 4; j < totalPage; j++) {
            pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadArticle(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
        }
        colorFlag = curPage;
    } else {
        for (var j = curPage - 2; j < curPage + 3; j++) {
            pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadArticle(" + j + ")'>" + j + "</a></li>");
        }
        colorFlag = curPage + 1;
    }
    pageUL.append("<li><a href='javascript:;' onclick='DownLoadPage(" + curPage + ',' + totalPage + ")' aria-label='Next' ><span aria-hidden='true'>&raquo;</span></a></li>");
    $('#' + colorFlag).addClass('active');
}

function upLoadPage(curPage) {
    if (curPage > 1) {
        var startPage = curPage - 1;
        loadArticle(startPage);
    }
}

function DownLoadPage(curPage, totalPage) {
    if (curPage < totalPage) {
        var endPage = curPage + 1;
        loadArticle(endPage);
    }
}

function deletearticle(id){
	renda.tipMsg.config({width:300,type:'confirm',msg:'确定删除改信息？'});
	modal = renda.dom.getByClass(document,'modal')[0];
    renda.eventUtil.addEvent(modal,'click',function(e){
		$.ajax({
		    type: "post",
	        url: "massageController/deleteArticle",
	        async:false,
	        data: {
	            "id":id
	        },
	        dataType: "json",
	        success: function(data){
	        	if(data['success']){
	        		window.location.href = "loginController/toSysMain";  //加载主页面
	        	}else{
	        		renda.tipMsg.config({width:300,type:'alert',msg:'删除错误，请稍后再试'});
	        	}
	        },
	        error: function(data){
	        	alert("系统错误，请联系管理员");
	        }
	    });
	});
}

function editarticle(id,picUrl,title,description,url){
	$("#id").val(id);
	$("#picUrl").val(picUrl);
	$("#title").val(title);
	$("#description").val(description);
	$("#url").val(url);
}