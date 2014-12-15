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
										window.location.href= basePath+'commRecordController/'+_json.data+'/doEdit.do';
									}else{
										window.location.href= basePath+'commRecordController/index.do';
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
		});