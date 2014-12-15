$(document).ready(function() {

			var $roleForm = $("#roleForm");
			$roleForm.ajaxForm({
						'dataType' : 'json',
						"clearForm" : false, // true的话
						// 提交完成之后会清空表单中的数据，默认不写和false就不清空
						"beforeSubmit" : function(formData, jqForm, options) { // 表单提交之前做一些操作
							//选择的权限
							var $perms =  $("#perms option");
							var perids = new Array();
							if($perms.length>0){
								for(var i=0;i<$perms.length;i++){
									perids[i] = $perms[i].value;
								}
							}
							formData.push({
											name:'perids',
											value:perids.join(",")
											})
						},
						'success' : function(data) {
							$.msgBox.confirmOK(data.message,function(){
								window.location.href= basePath + "role/index.do";
							})
						}
					});
			jQuery.validator.messages.required = "必填";
			$roleForm.validate({
						rules : {
							roleName : {
								required : true
							}
						}
					});
			$("#roleSubmit").bind('click', function() {
						if ($roleForm.valid()) {
							try{
								$roleForm.submit();
							}catch(e){
								alert(e)
							}
						}
					});

		});