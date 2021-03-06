function loadArticleKey(curPage,keyWordid){
    $.ajax({
    	   type: "post",
           url: "massageController/loadArticle",
           async:false,
           data: {
               "curPage":curPage
           },
           dataType: "json",
           success: function(msg){
                   var listTr = $("#articleListKey");
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
					                    	"keyWordId":keyWordid
					                    },
					                    dataType: "json",
					                    success: function(msg){
					                            var data1 = eval(msg['data']);
					                            if(keyWordid=="" || keyWordid==undefined){
					                            	listTr.append("<tr><th scope='row'>" + id + "</th><td>"
					                            			+ "<img id='headpic' src='" + data.list[i].picUrl + "' alt='' width='50' height=''>"
					                            			+ "</td><td>" + data.list[i].title
					                            			+ "</td><td>" + data.list[i].description
					                            			+ "</td><td>" + data.list[i].url
					                            			+ "</td><td>" 
					                            			+ '<a href="javascript:void(0)" id="relatearticlekey'+data.list[i].id+'" onclick="relateKey('+ "'" + data.list[i].id + "'" +')" class="btn btn-sm btn-info disabled">设置关联<span id="relatearticlekeySpan'+data.list[i].id+'" class="glyphicon glyphicon-cog"></span></a>'
					                            			+ '<a href="javascript:void(0)" id="deletearticlekey'+data.list[i].id+'" onclick="deleteKey('+ "'" + data.list[i].id + "'" +')"class="btn btn-sm btn-default disabled">取消关联</a></td></tr>');
					                            }else{
					                            	listTr.append("<tr><th scope='row'>" + id + "</th><td>"
					                            			+ "<img id='headpic' src='" + data.list[i].picUrl + "' alt='' width='50' height=''>"
					                            			+ "</td><td>" + data.list[i].title
					                            			+ "</td><td>" + data.list[i].description
					                            			+ "</td><td>" + data.list[i].url
					                            			+ "</td><td>" 
					                            			+ '<a href="javascript:void(0)" id="relatearticlekey'+data.list[i].id+'" onclick="relateKey('+ "'" + data.list[i].id + "','"+keyWordid+"'" +')" class="btn btn-sm btn-info">设置关联<span id="relatearticlekeySpan'+data.list[i].id+'" class="glyphicon glyphicon-cog"></span></a>'
					                            			+ '<a href="javascript:void(0)" id="deletearticlekey'+data.list[i].id+'" onclick="deleteKey('+ "'" + data.list[i].id + "','"+keyWordid+"'" +')"class="btn btn-sm btn-default disabled">取消关联</a></td></tr>');
					                            }
					         			            for (var j = 0; j < data1.length; j++) {
					         			            	if (data.list[i].id==data1[j]){
					         			            		$("#relatearticlekey"+data.list[i].id).removeClass("btn-info").addClass("btn-success disabled").html('关联成功<span class="glyphicon glyphicon-ok"></span>');
					         			            		$("#deletearticlekey"+data.list[i].id).removeClass("disabled");
					         			            	}
					         			            }
					                    },
					                    error: function(data){
					                        alert("系统错误，请联系管理员");
					                    }
					             });
			            	id = id + 1;
			            }
			            pagerUtilArticleKey(keyWordid,data.curPage, data.end, data.pageSize,
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

function pagerUtilArticleKey(keyWordid,curPage, end, pageSize, start, totalPage, totalRow) {
    var pageUL = $("#paginationListArticleKey");
    pageUL.html("");
    if(keyWordid=="" || keyWordid==undefined){
	    pageUL.append("<li><a href='javascript:;' onclick='upLoadArticleKeyPage(" + curPage + ")' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
	    //显示分页提示个数
	    var Flag = 5;
	    if (curPage <= 3) {
	        if (totalPage < 5) {
	            for (var j = 0; j < totalPage; j++) {
	                pageUL.append("<li id='ArticleKey" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleKey(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
	            }
	        } else {
	            for (var j = 0; j < Flag; j++) {
	                pageUL.append("<li id='ArticleKey" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleKey(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
	            }
	        }
	        colorFlagArticleKey = "ArticleKey"+curPage;
	    } else if (curPage > totalPage - 3) {
	        for (var j = totalPage - 5 >= 0 ? totalPage - 5 : totalPage - 4; j < totalPage; j++) {
	            pageUL.append("<li id='ArticleKey" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleKey(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
	        }
	        colorFlagArticleKey = "ArticleKey"+curPage;
	    } else {
	        for (var j = curPage - 2; j < curPage + 3; j++) {
	            pageUL.append("<li id='ArticleKey" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleKey(" + j + ")'>" + j + "</a></li>");
	        }
	        colorFlagArticleKey = "ArticleKey"+curPage + 1;
	    }
	    pageUL.append("<li><a href='javascript:;' onclick='DownLoadArticleKeyPage(" + curPage + ',' + totalPage + ")' aria-label='Next' ><span aria-hidden='true'>&raquo;</span></a></li>");
	    $('#' + colorFlagArticleKey).addClass('active');
    }else{
    	pageUL.append("<li><a href='javascript:;' onclick='upLoadArticleKeyPage(" + curPage + ',"'+ keyWordid+'"' +")' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
        //显示分页提示个数
        var Flag = 5;
        if (curPage <= 3) {
            if (totalPage < 5) {
                for (var j = 0; j < totalPage; j++) {
                    pageUL.append("<li id='ArticleKey" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleKey(" + (j + 1) + ',"'+keyWordid+'"'+ ")'>" + (j + 1) + "</a></li>");
                }
            } else {
                for (var j = 0; j < Flag; j++) {
                    pageUL.append("<li id='ArticleKey" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleKey(" + (j + 1) + ',"'+keyWordid+'"'+ ")'>" + (j + 1) + "</a></li>");
                }
            }
            colorFlagArticleKey = "ArticleKey"+curPage;
        } else if (curPage > totalPage - 3) {
            for (var j = totalPage - 5 >= 0 ? totalPage - 5 : totalPage - 4; j < totalPage; j++) {
                pageUL.append("<li id='ArticleKey" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleKey(" + (j + 1) + ',"'+keyWordid+'"'+ ")'>" + (j + 1) + "</a></li>");
            }
            colorFlagArticleKey = "ArticleKey"+curPage;
        } else {
            for (var j = curPage - 2; j < curPage + 3; j++) {
                pageUL.append("<li id='ArticleKey" + (j + 1) + "'><a href='javascript:;' onclick='loadArticleKey(" + j + ',"'+keyWordid+'"'+ ")'>" + j + "</a></li>");
            }
            colorFlagArticleKey = "ArticleKey"+curPage + 1;
        }
        pageUL.append("<li><a href='javascript:;' onclick='DownLoadArticleKeyPage(" + curPage + ',' + totalPage + ',"'+keyWordid+'"'+ ")' aria-label='Next' ><span aria-hidden='true'>&raquo;</span></a></li>");
        $('#' + colorFlagArticleKey).addClass('active');
    }
}

function upLoadArticleKeyPage(curPage,keyWordid) {
    if (curPage > 1) {
        var startPage = curPage - 1;
        loadArticleKey(startPage,keyWordid);
    }
}

function DownLoadArticleKeyPage(curPage, totalPage,keyWordid) {
    if (curPage < totalPage) {
        var endPage = curPage + 1;
        loadArticleKey(endPage,keyWordid);
    }
}
