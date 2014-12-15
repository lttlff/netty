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
								$.msgBox.question(_json.message+",是否继续修改?", function(isYES) {
									if (isYES) {
										window.location.href= basePath+'contactsController/doEdit/'+_json.data+'.do';
									}else{
										window.location.href= basePath+'contactsController/index.do';
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
					
					
			$(".arrow_up").bind('click',function(){
				if($(this).attr('class')=="arrow_up"){
					$(this).attr('class',"arrow_down");
					$("#contactWayList").children("ul").css('display','none');
				}else{
					$(this).attr('class',"arrow_up");
					$("#contactWayList").children("ul").css('display','block');
				}
			});
			
			//联系方式删除
			$("#contactWayList a.delete").live('click',function(){
				var $a = $(this);
				$.msgBox.confirm("确定要删除选中的记录?", function(isYes) {
					if (isYes) {
						if($a.attr("data")==undefined||$a.attr("data")==""){//页面新增的记录删除
							$a.parents("ul:first").remove();
							return;
						}
						$.ajax({
									url : basePath + "contactWayController/delete/"
											+ $a.attr("data") + ".do",
									cache : false,
									success : function(data) {
										$.msgBox.info(data.message)
										if (data.success) {
											//1.删除当前UL
											$a.parents("ul:first").remove();
											//3.处理name符合0、1、2...
											var $ulSiblings = $a.parents("ul:first").siblings();
											$a.parents("ul:first").remove();
											for(var i=0;i<$ulSiblings.length;i++){
												$($ulSiblings[i]).html(($($ulSiblings[i]).html()).replace(/\[\d\]/g,"["+i+"]"));					
											}
										}
									}
								});
					}
				});
			});
			
			//联系方式信息添加
			$("#contactWayList a.add_more").bind('click',function(){
				var $contactWayList = $("#contactWayList");
				var i = $contactWayList.children("ul").length;
				var ul = "<ul><li>" +
						"<div class='txt'><font color='red'>*</font>联系方式：</div>" +
						"<select class='st' name='contactWayList["+i+"].contactType' >" +
						contactTypeOptions+
						"</select></li>" +
						"<li><div class='txt'><font color='red'>*</font>联系信息：</div>" +
						"<input type='text' class='tt'  name='contactWayList["+i+"].contactInfo' /></li>" +
						"<li><div class='operate'><a class='delete' href='javascript:void(0);' data=''  title='删除' >&nbsp;</a></div></li>" +
						"<li><input type='hidden' name='contactWayList["+i+"].rowId'/>" +
						"<input type='hidden' name='contactWayList["+i+"].contactsId'/></li></ul>";
				$(ul).appendTo($contactWayList);
			});
			
			
		/*	//联系关联客户信息			
			$("#customerList a.add_more").bind('click',function(){
				var $customerList = $("#customerList");
				var i = $customerList.children("ul").length;
				var ul = "<ul><li><div class='txt'>" +
						"<font color='red'>*</font>客户名称：</div>" +
						"<input type='text' class='tt trigger' name='customerList["+i+"].custName' value='' /></li>" +
						"<li><div class='operate'>" +
						"<a class='delete' href='javascript:void(0);' data='' title='删除'  >&nbsp;</a></div></li>" +
						"<li><input type='hidden'  name='customerList["+i+"].rowId'/>" +
						"<input type='hidden'  name='customerList["+i+"].rowId'/></li><li></li></ul>";
				$(ul).appendTo($customerList);
			});*/
			
			
			//联系人分组弹出框			
			$("#mainForm input[name$='contactsGroup.groupName']").live('focus',function(){
				var $now_input = $(this);
				var $winIframe = $("#win_iframe");
				$winIframe.attr("src",basePath+"contactsGroupController/listWin.do");
				$winIframe.css('display', 'block');
				$("#windiv").dialog({
					autoOpen : true,
					title : '联系人分组选择',
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
								alert("请选择一条记录!");
								return;
							}else{
								var id = $tr.find('td:first').html();
								var groupName = $tr.find('td:eq(3)').html();
								$now_input.siblings("input:hidden[name='contactsGroup.rowId']").val(id);
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
			//地址选择弹出框			
			$("#mainForm input[name$='addrFull']").live('focus',function(){
				return;
				var $now_input = $(this);
				var $winIframe = $("#win_iframe");
				$winIframe.attr("src",basePath+"contactsGroupController/listWin.do");
				$winIframe.css('display', 'block');
				$("#windiv").dialog({
					autoOpen : true,
					title : '联系人分组选择',
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
								alert("请选择一条记录!");
								return;
							}else{
								var id = $tr.find('td:first').html();
								var groupName = $tr.find('td:eq(3)').html();
								$now_input.siblings("input:hidden[name='contactsGroup.rowId']").val(id);
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