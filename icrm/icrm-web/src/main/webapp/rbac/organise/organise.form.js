$(document).ready(function() {

			var $orgForm = $("#orgForm");
			$orgForm.ajaxForm({
						'dataType' : 'json',
						"clearForm" : false, // true的话
						// 提交完成之后会清空表单中的数据，默认不写和false就不清空
						"beforeSubmit" : function(formData, jqForm, options) { // 表单提交之前做一些操作
							//选择的权限
						},
						'success' : function(data) {
							$.msgBox.confirmOK(data.message,function(){
								window.location.href= basePath + "organise/main.do?parOrgId="+$("#parOrgId").val();
								//调用父窗口刷新树
								window.parent.reloadTree();
							})
						}
					});
			jQuery.validator.messages.required = "必填";
			$orgForm.validate({
						rules : {
							orgName : {
								required : true
							}
						}
					});
			$("#orgSubmit").bind('click', function() {
						if ($orgForm.valid()) {
							try{
								$orgForm.submit();
							}catch(e){
								alert(e)
							}
						}
					});
})