function loadArticleFirst(curPage){
    $.ajax({
    	   type: "post",
           url: "massageController/loadArticle",
           async:false,
           data: {
               "curPage":curPage
           },
           dataType: "json",
           success: function(msg){
                   var listTr = $("#articleListFirst");
                   listTr.html("");
                   var data = eval(msg['data']);
                   var id = (curPage-1)*5+1;
                   if (data.list.length>0){
			            for (var i = 0; i < data.list.length; i++) {
					            	$.ajax({
					             	   type: "post",
					                    url: "relationController/loadArticleByKeyWord",
					                    async:false,
					                    data: {
					                    	"keyWordId":"shouciguanzhuid"
					                    },
					                    dataType: "json",
					                    success: function(msg){
					                            var data1 = eval(msg['data']);
					                            listTr.append("<tr><th scope='row'>" + id + "</th><td>" 
					                            		+ "<img id='headpic' src='" + data.list[i].picUrl + "' alt='' width='50' height=''>"
					                            		+ "</td><td>" + data.list[i].title
					                            		+ "</td><td>" + data.list[i].description
					                            		+ "</td><td>" + data.list[i].url
					                            		+ "</td><td>" 
					                            		+ '<a href="javascript:void(0)" id="relatearticlefirst'+data.list[i].id+'" onclick="relateFirst('+ "'" + data.list[i].id + "'" +')" class="btn btn-sm btn-info">设置关联<span class="glyphicon glyphicon-cog"></span></a>'
					                            		+ '<a href="javascript:void(0)" id="deletearticlefirst'+data.list[i].id+'" onclick="deleteFirst('+ "'" + data.list[i].id + "'" +')"class="btn btn-sm btn-default disabled">取消关联</a></td></tr>');
					         			            for (var j = 0; j < data1.length; j++) {
					         			            	if (data.list[i].id==data1[j]){
					         			            		$("#relatearticlefirst"+data.list[i].id).removeClass("btn-info").addClass("btn-success disabled").html('关联成功<span class="glyphicon glyphicon-ok"></span>');
					         			            		$("#deletearticlefirst"+data.list[i].id).removeClass("disabled");
					         			            	}
					         			            }
					                    },
					                    error: function(data){
					                        alert("系统错误，请联系管理员");
					                    }
					             });
			            	id = id + 1;
			            }
			            pagerUtilArticleFirst(data.curPage, data.end, data.pageSize,
			                    data.start, data.totalPage, data.totalRow);
			            }else{
			            	listTr.append("<tr><td colspan='6' class='text-center'>没有图文消息</td></tr>");
			            }
           },
           error: function(data){
               alert("系统错误，请联系管理员");
           }
    });
}

function pagerUtilArticleFirst(curPage, end, pageSize, start, totalPage, totalRow) {
    var pageUL = $("#paginationListArticleFirst");
    pageUL.html("");
    pageUL.append("<li><a href='javascript:;' onclick='upLoadArticleFirstPage(" + curPage + ")' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
    //显示分页提示个数
    var Flag = 5;
    if (curPage <= 3) {
        if (totalPage < 5) {
            for (var j = 0; j < totalPage; j++) {
                pageUL.append("<li id='ArticleFirst" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleFirst(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
            }
        } else {
            for (var j = 0; j < Flag; j++) {
                pageUL.append("<li id='ArticleFirst" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleFirst(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
            }
        }
        colorFlagArticleFirst = "ArticleFirst"+curPage;
    } else if (curPage > totalPage - 3) {
        for (var j = totalPage - 5 >= 0 ? totalPage - 5 : totalPage - 4; j < totalPage; j++) {
            pageUL.append("<li id='ArticleFirst" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleFirst(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
        }
        colorFlagArticleFirst = "ArticleFirst"+curPage;
    } else {
        for (var j = curPage - 2; j < curPage + 3; j++) {
            pageUL.append("<li id='ArticleFirst" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleFirst(" + j + ")'>" + j + "</a></li>");
        }
        colorFlagArticleFirst = "ArticleFirst"+curPage + 1;
    }
    pageUL.append("<li><a href='javascript:;' onclick='DownLoadArticleFirstPage(" + curPage + ',' + totalPage + ")' aria-label='Next' ><span aria-hidden='true'>&raquo;</span></a></li>");
    $('#' + colorFlagArticleFirst).addClass('active');
}

function upLoadArticleFirstPage(curPage) {
    if (curPage > 1) {
        var startPage = curPage - 1;
        loadArticleFirst(startPage);
    }
}

function DownLoadArticleFirstPage(curPage, totalPage) {
    if (curPage < totalPage) {
        var endPage = curPage + 1;
        loadArticleFirst(endPage);
    }
}

