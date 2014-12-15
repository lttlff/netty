$(document).ready(function() {

			var $mainForm = $("#mainForm");
			$mainForm.ajaxForm({
						'dataType' : 'json',
						"clearForm" : false, // true的话
						// 提交完成之后会清空表单中的数据，默认不写和false就不清空
						"beforeSubmit" : function(formData, jqForm, options) { // 表单提交之前做一些操作
							
						},
						'success' : function(_json) {
							if(_json.success==false){
								$.msgBox.info(_json.message);
							}else{
								$.msgBox.question(_json.message+",是否继续修改?", function(isYes) {
									if (isYes) {
										window.location.href= basePath+'customerAcctController/'+_json.data+'/doEdit.do';
									}else{
										window.location.href= basePath+'customerAcctController/index.do';
									}
								});
							}
						}
					});
			jQuery.validator.messages.required = "";
			$mainForm.validate({
						rules : {
						
						}
					});
			$("#doSubmit").bind('click', function() {
				if ($mainForm.valid()) {
					try{
						$mainForm.submit();
					}catch(e){
						alert(e);
					}
				}
			});
			
			
			//客户选择
			$("input[name$=custName]").live('focus',function(){
				var $now_input = $(this);
				var $now_ul = $now_input.parents("ul:first");
				var $win_iframe = $("#win_iframe");
				$win_iframe.attr("src",basePath+"customerController/winList.do");
				$win_iframe.css('display', 'block');
				$("#windiv").dialog({
					autoOpen : true,
					title : '客户选择',
					width : 980,
					height : 500,
					show : {
						effect : "blind",
						duration : 200
					},
					close : function(event, ui) {
						//关闭dialog事件
					},
					buttons: {
						'选择':function(){
							var $table = $(window.frames["win_iframe"].document).find("#mainTable");
							var $tr = $table.find("td input:checked").parents("tr:first");
							if($tr.length==0){
								$.msgBox.info("请选择一条客户信息!");
								return;
							}else{
								var id = $tr.find('td:first').html();
								var custName = $tr.find('td:eq(3)').html();
								$now_ul.find("input:hidden[name$=custId]").val(id);
								$now_input.val(custName);
								$(this).dialog("close");
							}
						} ,
					    "关闭": function() {
					    	$(this).dialog("close");
					    }
					}
				})
			});
			
			
		});