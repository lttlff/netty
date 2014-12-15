$(document).ready(function() {

			var $posForm = $("#posForm");
			$posForm.ajaxForm({
						'dataType' : 'json',
						"clearForm" : false, // true的话
						// 提交完成之后会清空表单中的数据，默认不写和false就不清空
						"beforeSubmit" : function(formData, jqForm, options) { // 表单提交之前做一些操作
							//选择的角色
							var $roles =  $("#roles option");
							var roleids = new Array();
							if($roles.length>0){
								for(var i=0;i<$roles.length;i++){
									roleids[i] = $roles[i].value;
								}
							}
							formData.push({
											name:'roleids',
											value:roleids.join(",")
											})
						},
						'success' : function(data) {
							$.msgBox.confirmOK(data.message,function(){
								window.location.href= basePath + "position/main.do?parPosId="+$("#parPosId").val();
								//调用父窗口刷新树
								window.parent.reloadTree();
							})
						}
					});
			jQuery.validator.messages.required = "必填";
			$posForm.validate({
						rules : {
							posName : {
								required : true
							}
						}
					});
			$("#posSubmit").bind('click', function() {
						if ($posForm.valid()) {
							try{
								$posForm.submit();
							}catch(e){
								alert(e)
							}
						}
					});
					
		//部门选择框
		$("input[name$=orgName]").live('focus',function(){
			var $now_input = $(this);
			
			$("#dept_iframe").attr("src",basePath+"organise/orgSelectWin.do");
			$("#dept_iframe").css('display', 'block');
			$("#deptDiv").dialog({
				autoOpen : true,
				title : '部门选择',
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
						var deptId = $(window.frames["dept_iframe"].document).find("#deptId").val();
						var deptName = $(window.frames["dept_iframe"].document).find("#deptName").val();
						if(deptId){
							$("input:hidden[name$=orgId]").val(deptId);
							$now_input.val(deptName);
							$(this).dialog("close");
						}else{
							$.msgBox.alert("请先选择一条记录!");
							return;
						}
					} ,
				    "关闭": function() {
				    	$(this).dialog("close");
				    }
				}
			})
		});
})