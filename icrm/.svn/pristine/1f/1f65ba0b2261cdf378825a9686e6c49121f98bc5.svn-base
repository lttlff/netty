$(document).ready(function() {

			var $perForm = $("#perForm");
			$perForm.ajaxForm({
						'dataType' : 'json',
						"clearForm" : false, // true的话
						// 提交完成之后会清空表单中的数据，默认不写和false就不清空
						"beforeSubmit" : function(formData, jqForm, options) { // 表单提交之前做一些操作
							//选择的权限
						},
						'success' : function(data) {
							$.msgBox.confirmOK(data.message,function(){
								window.location.href= basePath + "permission/index.do";
							})
						}
					});
			jQuery.validator.messages.required = "必填";
			$perForm.validate({
						rules : {
							perName : {
								required : true
							}
						}
					});
			$("#perSubmit").bind('click', function() {
						if ($perForm.valid()) {
							try{
								$perForm.submit();
							}catch(e){
								alert(e)
							}
						}
					});
		//菜单选择框
			$("input[name$=menuName]").live('focus',function(){
				var $now_input = $(this);
				$("#menu_iframe").attr("src",basePath+"menu/goMenuSelectWin.do");
				$("#menu_iframe").css('display', 'block');
				$("#menuDiv").dialog({
					autoOpen : true,
					title : '菜单选择',
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
							var $table = $(window.frames["menu_iframe"].document).find("#mainTable");
							var $tr = $table.find("td input:checked").parents("tr:first");
							if($tr.length==0){
								alert("请选择一条记录!");
								return;
							}else{
								var id = $tr.find('td:first').html();
								var groupName = $tr.find('td:eq(3)').html();
								$now_input.siblings("input:hidden[name='menuId']").val(id);
								$now_input.val(groupName);
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