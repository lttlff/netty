$(document).ready(function() {

			var $customerForm = $("#customerForm");
			$customerForm.ajaxForm({
						'dataType' : 'json',
						"clearForm" : false, // true的话
						// 提交完成之后会清空表单中的数据，默认不写和false就不清空
						"beforeSubmit" : function(formData, jqForm, options) { // 表单提交之前做一些操作
						},
						'success' : function(data) {
							$.msgBox.info(data.message)
						}
					});
			jQuery.validator.messages.required = "";
			$customerForm.validate({
						rules : {
						
						}
					});
			$("#customerSubmit").bind('click', function() {
						if ($customerForm.valid()) {
							try{
								$customerForm.submit();
							}catch(e){
								alert(e)
							}
						}
					});
					
					
			$("#custSubArrow").bind('click',function(){
				if($(this).attr('class')=="arrow_up"){
					$(this).attr('class',"arrow_down");
					$("#custSub").children("ul").css('display','none');
				}else{
					$(this).attr('class',"arrow_up");
					$("#custSub").children("ul").css('display','block');
				}
			});
			$("#contactsArrow").bind('click',function(){
				if($(this).attr('class')=="arrow_up"){
					$(this).attr('class',"arrow_down");
					$("#contacts").children("ul").css('display','none');
				}else{
					$(this).attr('class',"arrow_up");
					$("#contacts").children("ul").css('display','block');
				}
			});
				
			
			//客户附属证件信息删除
			$("#custSub a.delete").live('click',function(){
				var $a = $(this);
				$.msgBox.confirm("确定要删除选中的记录?", function(isYes) {
					if (isYes) {
						if($a.attr("data")==""){//页面新增的记录删除
							$a.parents("ul:first").remove();
							return;
						}
						$.ajax({
									url : basePath + "custSubInfoController/delete/"
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
			//客户附属证件信息添加
			$("#custSub a.add_more").bind('click',function(){
				var $custSub = $("#custSub");
				var i = $custSub.children("ul").length;
				var ul = "<ul><li>" +
						"<div class='txt'><font color='red'>*</font>证件类型：</div>" +
						"<select class='st' name='custSubInfo["+i+"].regType' >" +
						$custSub.find('select:first').clone().html()+
						"</select></li>" +
						"<li><div class='txt'><font color='red'>*</font>证件号码：</div>" +
						"<input type='text' class='tt'  name='custSubInfo["+i+"].regNbr' /></li>" +
						"<li><div class='operate'><a class='delete' href='javascript:void(0);'  title='删除'  >&nbsp;</a></div></li>" +
						"<li><input type='hidden' name='custSubInfo["+i+"].rowId'/>" +
						"<input type='hidden' name='custSubInfo["+i+"].custId'/></li></ul>";
				$(ul).appendTo($custSub);
			});
			
			//联系人删除
			$("#contacts a.delete").live('click',function(){
				var $a = $(this);
				$.msgBox.confirm("确定要删除选中的记录?", function(isYes) {
					if (isYes) {
						if($a.attr("data")==""){//页面新增的记录删除
							$a.parents("ul:first").remove();
							return;
						}
						$.ajax({
									url : basePath + "custContactsRelaController/delete/"
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
			//联系人新增
			$("#contacts a.add_more").bind('click',function(){
				var $contacts = $("#contacts");
				var i = $contacts.children("ul").length;
				var ul = "<ul><li><div class='txt'><font color='red'>*</font>联系人类型：</div>" +
						"<select class='st'  name='custContactsRela["+i+"].contactsType' >" +
						$contacts.find('select[name$=contactsType]').clone().html()+
						"</select>" +
						"</li><li><div class='txt'><font color='red'>*</font>联系人姓名：</div>" +
						"<input type='text' class='tt trigger' name='custContactsRela["+i+"].contactsName' /></li>" +
						"<li><div class='operate'><a class='detail' href='javascript:void(0);' title='查看联系人详细'>&nbsp;</a>" +
						"<a class='delete' href='javascript:void(0);' title='删除'>&nbsp;</a></div></li>" +
						"<li><input type='text' name='custContactsRela["+i+"].rowId' >" +
						"<input type='hidden' name='custContactsRela["+i+"].custId'>" +
						"<input type='hidden' name='custContactsRela["+i+"].contactsId'>" +
						"</li></ul>";
				$(ul).appendTo($contacts);
			});

			
						
			$("input[name$=contactsName]").live('focus',function(){
				var $now_input = $(this);
				var $now_ul = $now_input.parents("ul:first");
				
				$("#contacts_iframe").attr("src",basePath+"contactsController/listWin.do");
				$("#contacts_iframe").css('display', 'block');
				$("#contactsDiv").dialog({
					autoOpen : true,
					title : '联系人选择',
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
							var $table = $(window.frames["contacts_iframe"].document).find("#mainTable");
							var $tr = $table.find("td input:checked").parents("tr:first");
							if($tr.length==0){
								$.msgBox.alert("请先选择一条联系人记录!");
								return;
							}else{
								var id = $tr.find('td:first').html();
								var contactsName = $tr.find('td:eq(3)').html();
								$now_ul.find("input:hidden[name$=contactsId]").val(id);
								$now_input.val(contactsName);
								$(this).dialog("close");
							}
						} ,
					    "关闭": function() {
					    	$(this).dialog("close");
					    }
					}
				})
			});
			$("input[name$=custAddress]").live('focus',function(){
				var $now_input = $(this);
				var $now_ul = $now_input.parents("ul:first");
				
				$("#contacts_iframe").attr("src",basePath+"addrController/addressWin.do");
				$("#contacts_iframe").css('display', 'block');
				$("#contactsDiv").dialog({
					autoOpen : true,
					title : '地址选择',
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
						'确定':function(){
							//列表选择直接回填
							//通过新增则提交后回填
							var $table = $(window.frames["contacts_iframe"].window.frames["list_frame"].document).find("#mainTable");
							var $tr = $table.find("td input:checked").parents("tr:first");
							var $in_J_Street = $(window.frames["contacts_iframe"].document).find("#J_Street");
							if($tr.length==0&&$in_J_Street.val()==""){
								$.msgBox.alert("请先选择一条地址记录<br/>或手动输入地址!");
								return false;
							}else if($tr.length!=0&&$in_J_Street.val()!=""){
								$.msgBox.alert("同时选择了一条地址记录<br/>并且手动输入了地址,删除一条!");
								return false;
							}else if($tr.length!=0){
								var id = $tr.find('td:first').html();
								var address = $tr.find('td:eq(4)').html();
								$now_ul.find("input:hidden[name$=custAddrId]").val(id);
								$now_input.val(address);
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