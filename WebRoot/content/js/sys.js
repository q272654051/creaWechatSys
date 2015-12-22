/**
 * Created by thj on 2015/12/14.
 */

;(function(w) {
    w.renda = {};
    (function(p) {
        p.stop = {
            stopPropagation: function(e) {
                if (e.stopPropagation) {
                    e.stopPropagation();
                } else {
                    e.cancelBubble = true;
                }
            }
        };
        p.has = {
            hasClass:function(element, className){
                var reg = new RegExp('(\\s|^)' + className + '(\\s|$)');
                return element.className.match(reg);
            }
        };
        p.dom = {
            find: function(elementId) {
                if (elementId === '')
                    return;
                return document.getElementById(elementId);
            },
            getByClass: function(obj, className) {
                if (obj.getElementsByClassName) {
                    return obj.getElementsByClassName(className);
                } else {
                    var arr = [],
                        collections = obj.getElementsByTagName('*'),
                        len = collections.length;
                    for (var i = 0; i < len; i++) {
                        if (collections[i].className.indexOf(className) > -1) {
                            if (collections[i].className == className) {
                                arr.push(collections[i]);
                            }
                        }
                    }
                    return arr;
                }
            },
            css: function(element, key) {
                if (!element) {
                    return;
                }
                var obj = element.currentStyle ? element.currentStyle : window.getComputedStyle(element, null);
                return obj[key];
            },
            addClass: function(element, className) {
                if (!p.has.hasClass(element, className)) {
                    if (!element.className) {
                        element.className += className;
                    } else {
                        element.className += " " + className;
                    }
                }
            },
            removeClass: function(element, className) {
                if (p.has.hasClass(element, className)) {
                    var reg = new RegExp('(\\s|^)' + className + '(\\s|$)');
                    element.className = element.className.replace(reg, ' ');
                }
            }
        };

        p.eventUtil = {
            addEvent: function(element, eventType, eventHandler) {
                eventType = eventType.replace(/^on/i, '').toLowerCase();
                if (element.addEventListener) {
                    element.addEventListener(eventType, eventHandler, false);
                } else {
                    element.attachEvent('on' + eventType, function() {
                        eventHandler(window.event);
                    });
                }
            },
            removeEvent: function(element, eventHandler) {
                eventType = eventType.replace(/^on/i, '').toLowerCase();
                if (element.removeEventListener) {
                    element.removeEventListener(eventType, eventHandler, false);
                } else {
                    element.detachEvent('on' + eventType, eventHandler);
                }
            },
            hover: function(element, hoverHandler, unhoverHandler) {
                this.addEvent(element, "mouseover", function(e) {
                    var target = getTarget(e);
                    while (target != element) {
                        target = target.parentNode;
                    }
                    hoverHandler(target);
                });
                this.addEvent(element, "mouseout", function(e) {
                    var target = getTarget(e);
                    while (target != element) {
                        target = target.parentNode;
                    }
                    unhoverHandler(target);
                });
            },
            enter: function(element, eventHandler) {
                this.addEvent(element, "keydown", function(e) {
                    if (e.keyCode === 13) {
                        eventHandler(e);
                        renda.stop.stopPropagation(e);
                    }
                });
            },
            click: function(element, eventHandler) {
                this.addEvent(element, "click", function(e) {
                    eventHandler(e);
                });
            }
        };

        p.tipMsg = {
            config:function(config){
                this.default = {
                    title:'确认消息框',
                    msg:'确定删除吗？',
                    type:'confirm' // alert/confirm
                };
                $.extend(this.default,config);

                var self = this;
                var btns = '';
                if( this.default.type == 'confirm' ){
                    btns = '<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">取 消</button>' +
                        '<button type="button" class="btn btn-success btn-sm" data-tip="okTip">确 定</button>';
                }else if( this.default.type == 'alert' ){
                    btns = '<button type="button" class="btn btn-success btn-sm" data-tip="okTip">确 定</button>';
                }

                var temp = '<div style="width:'+self.default.width+'px" class="modal-dialog" role="document">' +
                    '<div class="modal-content">' +
                        '<div class="modal-header">' +
                            '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
                            '<h5 class="modal-title">'+self.default.title+'</h5>' +
                        '</div>' +
                        '<div class="modal-body">' +
                            '<div class="container-fluid">' +
                                '<div class="row">' +
                                    '<div class="col-md-12">'+self.default.msg+'</div>' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="modal-footer">'+ btns +'</div>' +
                    '</div>' +
                '</div>';

                var model_bg = document.createElement('div');
                model_bg.className = 'modal-backdrop fade in';

                this.oDialog = document.createElement('div');
                this.oDialog.className = 'modal fade';
                this.oDialog.setAttribute('role','dialog');
                this.oDialog.setAttribute('aria-labelledby','gridSystemModalLabel');
                this.oDialog.innerHTML = temp;
                document.getElementsByTagName('body')[0].appendChild(this.oDialog);

                var win_hei = document.body.clientHeight || document.documentElement.clientHeight,
                    obj_hei = renda.dom.getByClass(this.oDialog,'modal-dialog')[0].offsetHeight;

                (function(o){
                    setTimeout(function(){
                        elmOffset(o.oDialog,win_hei);
                        o.oDialog.className = 'modal fade in';
                        o.oDialog.appendChild(model_bg);
                    },10);
                    renda.eventUtil.addEvent(window,'resize',function(){
                        win_hei = document.body.clientHeight || document.documentElement.clientHeight;
                        elmOffset(o.oDialog,win_hei);
                    });
                })(this);

                renda.eventUtil.addEvent(this.oDialog,'click',function(e){
                    var target = e.target || e.srcElement;
                    if( target.className == 'close' || target.className == 'btn btn-default btn-sm' ){
                        removeDialog(self.oDialog);
                    }else if( target.getAttribute('data-tip')=="okTip" ){
                        removeDialog(self.oDialog);
                    }
                });

                /*
                * esc关闭
                * 点击黑色区域关闭
                * */
                renda.eventUtil.addEvent(document,'click',function(e){
                    var target = e.target || e.srcElement;
                    if( target.className.indexOf('modal-backdrop')>=0 ){
                        removeDialog(self.oDialog);
                    }
                });

                renda.eventUtil.addEvent(document,'keydown',function(e){
                    if( e.keyCode==27 ){
                        removeDialog(self.oDialog);
                    }
                });



                function removeDialog(obj){
                    renda.dom.removeClass(obj,'in');
                    setTimeout(function(){
                        obj.remove();
                    },200);
                }

                function elmOffset(elm,win_hei){
                    renda.dom.getByClass(elm,'modal-dialog')[0].style.top = (win_hei-obj_hei)/2-80+'px';
                }

            }
        };


    })(w.renda);
})(window);

;(function() {
    /*
     * login
     * */
    var loginForm = {
        init: function() {
            this.changeOffset();
            this.eventType();
        },
        /*
         * default config
         * */
        config: {
            w_width: document.body.clientWidth || document.documentElement.clientWidth,
            w_height: document.body.clientHeight || document.documentElement.clientHeight
        },
        /*
         * get elements gather
         * */
        elems: {
            oLogin: renda.dom.find('rendaLogin'),
            oSubmit: renda.dom.find('submitBtn')
        },
        /*
         * change element offset
         * */
        changeOffset: function() {
            if (!this.elems.oLogin) return;
            var self = this,
                styles = self.elems.oLogin.style;
            self.elemOffset(self.config, styles);
            renda.eventUtil.addEvent(window, 'resize', function() {
                self.config = {
                    w_width: document.body.clientWidth || document.documentElement.clientWidth,
                    w_height: document.body.clientHeight || document.documentElement.clientHeight
                };
                self.elemOffset(self.config, styles);
            });
        },
        elemOffset: function(win, elem) {
            elem.left = (win.w_width - this.elems.oLogin.offsetWidth) / 2 + 'px';
            elem.top = (win.w_height - this.elems.oLogin.offsetHeight) / 2 + 'px';
        },
        eventType: function() {
            if (!this.elems.oLogin) return;
            var self = this;
            var oSubmit = self.elems.oSubmit;
            renda.eventUtil.enter(document, function() {
                // 登录回车提交
            	var uName = $("#username").val();
                var uPass = $("#password").val();
                if (uName == "" || uName == null) {
                    alert("用户名不能为空！");//提交验证
                    return;
                }
                if (uPass == "" || uPass == null) {
                	alert("密码不能为空！");//提交验证
                	return;
                }

                $.ajax({
            		type: "post",
                    url: "loginController/tologin",
                    async:false,
                    data: {
                    	"userName":uName,
                    	"pwd":uPass
                    },
                    dataType: "json",
                    success: function(data){
                    	if(data['success']){
                    		alert(data['msg']);
                            window.location.href = "loginController/toSysMain";  //加载主页面
                    	}else{
                    		alert(data['msg']);
                    	}
                    },
                    error: function(data){
                    	alert(data['msg']);
                    }
            	});

            });
            renda.eventUtil.addEvent(oSubmit,'click',function(){
                // 验证
                var uName = $("#username").val();
                var uPass = $("#password").val();
                if (uName == "" || uName == null) {
                    alert("用户名不能为空！");//提交验证
                    return;
                }
                if (uPass == "" || uPass == null) {
                	alert("密码不能为空！");//提交验证
                	return;
                }

                $.ajax({
            		type: "post",
                    url: "loginController/tologin",
                    async:false,
                    data: {
                    	"userName":uName,
                    	"pwd":uPass
                    },
                    dataType: "json",
                    success: function(data){
                    	if(data['success']){
                    		alert(data['msg']);
                            window.location.href = "loginController/toSysMain";  //加载主页面
                    	}else{
                    		alert(data['msg']);
                    	}
                    },
                    error: function(data){
                    	alert(data['msg']);
                    }
            	});

            });
        }

    };
    loginForm.init();

    /*
     * keyword response fn
     * */
    var keyResponse = {
        init: function() {
            this.elems();
            this.showEvent();
            this.config();
            this.bindEvent();
        },
        config: function() {
            this.tmpHtml = '<div class="input-group"><span class="key"></span> ' +
                '<span class="glyphicon glyphicon-remove" title="删除"></span> ' +
                '<span class="glyphicon glyphicon-edit" title="编辑"></span></div>';

            this.editHtml = '<div data-id="" class="input-group" title=""> ' +
                '<input class="form-control" style="" type="text" value=""/> ' +
                '<span class="input-group-btn"> ' +
                '<button class="btn btn-default">保存</button> ' +
                '</span> ' +
                '</div>';
            this.location = 'http://192.168.140.1:6330/';
        },
        elems: function() {
            if( renda.dom.find('keywordInput')==null ) return;
            this.oEngine = renda.dom.find('keywordInput');
            this.oEngineBtn = renda.dom.find('addKeywordBtn');
            this.oKeyListBox = renda.dom.find('keywordsList');
            this.aEditTip = renda.dom.getByClass(document, 'js_editTip')[0];
        },
        showEvent: function() {
            if( this.oEngine==undefined ) return;
            var self = this;
            renda.eventUtil.addEvent(this.oEngine, 'keyup', function() {
                // 查询数据列表
                self.selDataList(this);
            });
            renda.eventUtil.addEvent(this.oEngineBtn, 'click', function() {
                // 添加关键字数据到后台
                self.addKeyword();
                self.selDataList(self.oEngine);
            });
        },
        addKeyword: function() {
            var self = this,
                oDiv = null,
                key = null,
                attrs = null,
                refChild = '';

            /*
             * 添加关键字
             * */
            if( this.oEngine.value == '' ){
                renda.tipMsg.config({width:300,type:'alert',msg:'关键字不能为空'});
                return;
            }
            $.ajax({
                // 请求匹配关键字
                type: 'post',
                url: self.location+'RendaWxSys/autoResponse.html?value=',
                dataType: 'html',
                data: {},
                async: false,
                success: function(data) {
                    var data = {
                        id:1,
                        name:'关联信息关联信息关联信息关联信息'
                    };
                    // bind html attr
                    oDiv = document.createElement('div');
                    oDiv.setAttribute('class', 'btn btn-default key-item');
                    oDiv.innerHTML = self.tmpHtml;

                    key = renda.dom.getByClass(oDiv, 'key')[0];
                    key.innerHTML = self.oEngine.value;

                    attrs = renda.dom.getByClass(oDiv,'input-group')[0];
                    attrs.setAttribute('data-id',data.id);
                    attrs.setAttribute('title',data.name);

                    refChild = self.oKeyListBox.getElementsByTagName('*')[0];
                    self.oKeyListBox.insertBefore(oDiv, refChild);

                    /*
                     * 更新数据显示并绑定编辑、删除功能
                     * */
                    self.bindEvent();

                },
                error: function() {}
            });

        },
        bindEvent: function() {
            if(this.oKeyListBox==undefined) return;
            var self = this,
                aItem = null,
                target = null,
                modal = null;

            aItem = renda.dom.getByClass(this.oKeyListBox, 'key-item');
            self.oEngine.value = '';
            self.oEngine.focus();
            for (var i = 0; i < aItem.length; i++) {
                aItem[i].onclick = function(e){
                    renda.stop.stopPropagation(e);
                    bindClick(this, e);
                }
            }
            function bindClick(obj, e) {
                renda.stop.stopPropagation(e);
                target = e.target || e.srcElement;
                switch (target.className) {
                    case 'glyphicon glyphicon-remove':
                        //alert('删除关键字');
                        /*
                        * 1.弹窗
                        * 2.获取modal对象
                        * 3.绑定click事件
                        * */
                        renda.tipMsg.config({width:300,type:'confirm',msg:'确定删除关键字吗？'});
                        modal = renda.dom.getByClass(document,'modal')[0];
                        renda.eventUtil.addEvent(modal,'click',function(e){
                            var target = e.target || e.srcElement;
                            if(target.getAttribute('data-tip')==='okTip'){
                                $.ajax({
                                    // 请求匹配关键字
                                    type: 'post',
                                    url: self.location+'RendaWxSys/autoResponse.html',
                                    dataType: 'html',
                                    async: false,
                                    success: function(data) {
                                        if (obj.parentNode == null) return;
                                        obj.parentNode.removeChild(obj);
                                        setTimeout(function(){
                                            renda.tipMsg.config({width:200,type:'alert',msg:'删除成功'});
                                        },500);
                                        //alert('删除数据并更新数据信息');
                                    },
                                    error: function() {}
                                });
                            }
                        });
                        break;
                    case 'glyphicon glyphicon-edit':
                        //alert('编辑');
                        var oSpan = renda.dom.getByClass(target.parentNode, 'key')[0];
                        var oBtn, sInput ,temTitle, temDataId,attrs;

                        /*
                        * 临时保存关键字的ID属性和名称
                        * */
                        temDataId = renda.dom.getByClass(obj, 'input-group')[0].getAttribute('data-id');
                        temTitle = renda.dom.getByClass(obj, 'input-group')[0].title;

                        obj.innerHTML = self.editHtml;
                        oBtn = renda.dom.getByClass(obj, 'btn-default')[0];
                        sInput = renda.dom.getByClass(obj, 'form-control')[0];
                        sInput.style.width ='450px';
                        sInput.focus();
                        sInput.value = '';
                        sInput.value = oSpan.innerHTML;

                        attrs = renda.dom.getByClass(obj,'input-group')[0];
                        attrs.setAttribute('data-id',temDataId);
                        attrs.setAttribute('title',temTitle);

                        /*
                        * 修改字段限制字符
                        * */
                        renda.eventUtil.addEvent(sInput,'keyup',function(){
                            if( this.value.length>=30 ){
                                this.value = this.value.substring(0,30);
                                return;
                            }
                        });
                        /*
                        * 保存修改的关键字
                        * */
                        renda.eventUtil.addEvent(oBtn, 'click', function() {
                            obj.innerHTML = self.tmpHtml; // 替换删除、编辑模板
                            renda.dom.getByClass(obj, 'input-group')[0].title = renda.dom.getByClass(obj, 'key')[0].innerHTML = sInput.value;
                            renda.dom.getByClass(obj, 'input-group')[0].setAttribute('data-id',temDataId);

                            /*console.log( oSpan.innerHTML+'改变之前的' );
                            console.log( sInput.value+'改变之后的');*/
                            /*
                            * 数据无变化不提交
                            * */
                            if( sInput.value == oSpan.innerHTML ) return;
                            $.ajax({
                                type: 'post',
                                url: '',
                                dataType: 'json',
                                async: false,
                                success: function(data) {
                                    // update data
                                },
                                error: function() {}
                            });
                        });
                        break;
                    case 'key':
                        //alert('选中查询数据');
                        if( renda.has.hasClass(obj,'active') ){
                            obj.className = 'btn btn-default key-item';
                        }else{
                            for (var i = 0; i < aItem.length; i++) {
                                renda.dom.removeClass(aItem[i],'btn-success active');
                            }
                            renda.dom.addClass(obj,'btn-success active');
                            $.ajax({
                                // 请求匹配数据列表
                                type: 'get',
                                url: '',
                                dataType: 'json',
                                async: false,
                                success: function(data) {
                                    // bind html
                                },
                                error: function() {}
                            });
                        }
                        break;
                    default:
                    break;
                }
            }
        },
        selDataList: function(inputElm) {
            /*
             * 检测字符个数
             * */
            var len = 30,
                oEm = this.aEditTip.getElementsByTagName('em')[0],
                sLen = parseInt(len - inputElm.value.length);
            if (sLen <= 0) {
                this.oEngine.value = this.oEngine.value.substring(0, len);
                oEm.innerHTML = '0';
                return;
            }
            oEm.innerHTML = sLen;
            /*$.ajax({
                // 请求匹配关键字
                type: 'get',
                url: '',
                dataType: 'json',
                async: false,
                success: function(data) {
                    // bind html
                },
                error: function() {}
            });*/
        }
    };
    keyResponse.init();

    /*
     * upload headpic
     * */
    var uploadPic = {
        init: function() {
            var me = this;
            $('#exampleInputFile').bind('change', function() {
                me.previewImage(this);
            });
        },
        previewImage: function(file) {
            var me = this;
            var MAXWIDTH = 250;
            var MAXHEIGHT = 240;
            var div = document.getElementById('preview');
            if (file.files && file.files[0]) {
                div.innerHTML = '<img id="headpic">';
                var img = document.getElementById("headpic");
                img.onload = function() {
                    var rect = me.clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                    //img.width  =  rect.width;
                    img.height = rect.height;
                };
                var reader = new FileReader();
                reader.onload = function(evt) {
                    img.src = evt.target.result;
                }
                reader.readAsDataURL(file.files[0]);
            } else { //兼容IE
                var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
                file.select();
                var src = document.selection.createRange().text;
                div.innerHTML = '<img id="headpic">';
                var img = document.getElementById('headpic');
                img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
                var rect = me.clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
                div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
            }
        },
        clacImgZoomParam: function(maxWidth, maxHeight, width, height) {
            var param = {
                top: 0,
                left: 0,
                width: width,
                height: height
            };
            if (width > maxWidth || height > maxHeight) {
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                if (rateWidth > rateHeight) {
                    param.width = maxWidth;
                    param.height = Math.round(height / rateWidth);
                } else {
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }
    }
    uploadPic.init();

    

})();
