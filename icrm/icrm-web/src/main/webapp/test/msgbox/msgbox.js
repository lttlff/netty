(function($) {
	jQuery.msgBox = {
		alert : function(msg) {
			build('alert',msg);
		},
		info : function(msg) {
			build('info',msg);
		},
		error : function(msg) {
			build('error',msg);
		},
		question : function(msg) {
			build('question',msg);
		},
		confirm : function(msg,callback) {
			build('confirm',msg,callback);
		}
	};
	var build = function(type,msg,callback){
		var msgbox ='<div class="jquery-msgbox" id="jqueryMsgBox" style="display: block; position: absolute;top: 129.5px; left: 421.5px; width: 300px; height: auto; z-index: 10000; word-wrap: break-word; box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.5); border-radius: 6px; background-color: rgb(255, 255, 255);"></div>';
//		var msgbox ='<div class="jquery-msgbox" id="jqueryMsgBox" style="display: block; margin:0px auto;width: 300px; height: auto; z-index: 10000; word-wrap: break-word; box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.5); border-radius: 6px; background-color: rgb(255, 255, 255);"></div>';
//		var innerHtml = '<a id="dialogBoxClose" onclick="" title="关闭" style="position: absolute;right:2px;font-weight:bold;color:#888;cursor:pointer;font-size:18px;font-family:Airal;">×</a>';
//		var innerHtml = '<div class="drop-title"><a class="ico_close" href="javascript:;" title="关闭"></a></div>';
		var innerHtml = '<a class="ico_close" href="javascript:;" title="关闭"></a>';
		switch(type){
			case 'alert':
				innerHtml +=  '<div class="jquery-msgbox-wrapper jquery-msgbox-alert" style="height: auto; min-height: 60px;">';
				innerHtml +=  msg||"";
				innerHtml += '<div class="jquery-msgbox-buttons"><button id="dialogOk">确定</button></div></div>';
				break;
			case 'info':
				innerHtml +=  '<div class="jquery-msgbox-wrapper jquery-msgbox-info" style="height: auto; min-height: 60px;">';
				innerHtml +=  msg||"";
				innerHtml += '<div class="jquery-msgbox-buttons"><button id="dialogOk">确定</button></div></div>';
				break;
			case 'error':
				innerHtml +=  '<div class="jquery-msgbox-wrapper jquery-msgbox-error" style="height: auto; min-height: 60px;">';
				innerHtml +=  msg||"";
				innerHtml += '<div class="jquery-msgbox-buttons"><button id="dialogOk">确定</button></div></div>';
				break;
			case 'question':
				innerHtml +=  '<div class="jquery-msgbox-wrapper jquery-msgbox-prompt" style="height: auto; min-height: 60px;">';
				innerHtml +=  msg||"";
				innerHtml +=  '<div class="jquery-msgbox-inputs"><input type="text" autocomplete="off" value="" name="jquery-msgbox-label-0"></div>';
				innerHtml +=  '<div class="jquery-msgbox-buttons"><button id="dialogYES">确定</button><button id="dialogNO">取消</button></div>';
				break;
			case 'confirm':
				innerHtml +=  '<div class="jquery-msgbox-wrapper jquery-msgbox-confirm" style="height: auto; min-height: 60px;">';
				innerHtml +=  msg||""||"?";
				innerHtml +=  '<div class="jquery-msgbox-buttons"><button id="dialogYES">确定</button><button id="dialogNO">取消</button></div>';
				break;
		}
		if($("#jqueryMsgBox").length==0){
			$("body").append(msgbox);
		}
		$("#jqueryMsgBox").html(innerHtml).find("#dialogOk").bind('click',function(){
			$("#jqueryMsgBox").fadeOut("fast");
		});
		
		$("#dialogNO").bind('click',function(){
			$("#jqueryMsgBox").fadeOut();
			callback("no");
		});
		$("#dialogYES").bind('click',function(){
			$("#jqueryMsgBox").fadeOut();
			callback("ok");
		});
		$("#jqueryMsgBox").fadeIn();
	}
})(jQuery);

