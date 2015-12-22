function loadText(curPage){
    $.ajax({
    type: "post",
           url: "massageController/loadText",
           async:false,
           data: {
               "curPage":curPage
           },
           dataType: "json",
           success: function(msg){
                   var listTr = $("#textList");
                   listTr.html("");
                   var data = eval(msg['data']);
                   console.log(data.list);
                   var id = (curPage-1)*5+1;
                   if (data.list.length>0){
			            for (var i = 0; i < data.list.length; i++) {
			            		listTr.append("<tr><th scope='row'>" + id + "</th><td>" + data.list[i].content
			            		+ "</td><td><a href='javascript:;' onclick='editText("
			            		+ data.list[i].id +")' class='btn btn-sm btn-info'>修改</a>"
			            		+ "<a href='javascript:;' onclick='deleteText("
			            		+ data.list[i].id +")'class='btn btn-sm btn-warning'>删除</a></td></tr>");
			            id = id + 1;
			            }
			            pagerUtil(data.curPage, data.end, data.pageSize,
			                    data.start, data.totalPage, data.totalRow);
			            	new _().bgcolorOdd();
			            }else{
			            	listTr.append("<tr><td style='padding:10px; font-size:14px;'>没有文本消息</td></tr>");
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
                pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadText(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
            }
        } else {
            for (var j = 0; j < Flag; j++) {
                pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadText(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
            }
        }
        colorFlag = curPage;
    } else if (curPage > totalPage - 3) {
        for (var j = totalPage - 5 >= 0 ? totalPage - 5 : totalPage - 4; j < totalPage; j++) {
            pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadText(" + (j + 1) + ")'>" + (j + 1) + "</a></li>");
        }
        colorFlag = curPage;
    } else {
        for (var j = curPage - 2; j < curPage + 3; j++) {
            pageUL.append("<li id=" + (j + 1) + "><a href='javascript:;' onclick='loadText(" + j + ")'>" + j + "</a></li>");
        }
        colorFlag = curPage + 1;
    }
    pageUL.append("<li><a href='javascript:;' onclick='DownLoadPage(" + curPage + ',' + totalPage + ")' aria-label='Next' ><span aria-hidden='true'>&raquo;</span></a></li>");
    $('#' + colorFlag).addClass('active');
}

function upLoadPage(curPage) {
    if (curPage > 1) {
        var startPage = curPage - 1;
        loadText(startPage);
    }
}

function DownLoadPage(curPage, totalPage) {
    if (curPage < totalPage) {
        var endPage = curPage + 1;
        loadText(endPage);
    }
}