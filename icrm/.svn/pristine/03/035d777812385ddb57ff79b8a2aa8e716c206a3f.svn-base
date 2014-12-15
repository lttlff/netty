(function($) {
	jQuery.msgBox = {
		alert : function(msg) {
			showDialog(msg,'alert');
		},
		info : function(msg) {
			showDialog(msg,'info');
		},
		error : function(msg) {
			showDialog(msg,'error');
		},
		question : function(msg,callback) {
			showQuestion(msg,callback);
		},
		confirm : function(msg,callback) {
			showConfirm(msg,callback);
		},
		confirmOK:function(msg,callback){
			showConfirmOK(msg,callback);
		}
	};
	var dialogDiv = function(msg){
		var $div = $("#_dialog_message");
		if($div.length==0){
			$div = $("<div id='_dialog_message' style='display: none;'  title='消息'><p></p></div>");
			$div.appendTo($('body'));
		}
		$div.find('p').html(msg);
		return $div;
	}
	
	var showDialog = function(msg,type){
		dialogDiv(msg).dialog({
			modal: true,
			width : 300,
			height:185,
			buttons: {
				'确定': function() {
					$( this ).dialog( "close" );
				}
			}
		});
	}
	
	var showConfirm = function(msg, callback) {
		dialogDiv(msg).dialog({
					width : 300,
					modal : true,
					buttons : {
						"取消" : function() {
							$(this).dialog('close');
							callback(false);
						},
						"确定" : function() {
							$(this).dialog('close');
							callback(true);
						}
					}
				});
	}
	var showQuestion = function(msg, callback) {
		dialogDiv(msg).dialog({
					width : 300,
					modal : true,
					buttons : {
						"否" : function() {
							$(this).dialog('close');
							callback(false);
						},
						"是" : function() {
							$(this).dialog('close');
							callback(true);
						}
					}
				});
	}
	var showConfirmOK = function(msg,callback){
		dialogDiv(msg).dialog({
				width : 300,
				modal : true,
				beforeClose:function(){
					callback();
				},
				buttons : {
					"确定" : function() {
						$(this).dialog('close');
					}
				}
			});
	}
	
})(jQuery);

