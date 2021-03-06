$(document).ready(function() {
	var $perTable = $("#perTable");
	$perTable.find("tr").bind("mouseover", function() {
				$(this).find(".operate").css("visibility", "visible");
			}).bind("mouseout", function() {
				$(this).find(".operate").css("visibility", "hidden");
			}).bind("click", function() {
				
			});
	// 删除
	$perTable.find("td .delete").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();
		
		$.msgBox.confirm("确定要删除选中的记录?", function(btn) {
					if (btn) {
						$.ajax({
									url : basePath + "permission/deleteList.do",
									cache : false,
									type: "POST",
									data :{
										ids : id	
									},
									dataType:"json",
									success : function(data) {
										$.msgBox.confirmOK(data.message,function(){
											if (data.state) {
												window.location.reload();
											}
										})
									}
								});
					}
				});
	});
	
	// 查询
	$("#perQuery #tt_query").bind('click', function() {
				$perQuery = $("#perQuery");
				$perQuery.attr("action", basePath + "permission/index.do")
				$("#perQuery").submit();
			});
	// 新增
	$("#perQuery #tt_add").bind('click', function() {
				$perQuery = $("#perQuery");
				$perQuery.attr("action", basePath	+ "permission/doEdit.do")
				$("#perQuery").submit();
			});
	// 多条删除
	$("#perQuery #tt_del").bind('click', function() {
				$perQuery = $("#perQuery");
				if(!hasChecked("rowId")){
					$.msgBox.alert("请选择要删除的记录!");
					return;
				}
				var idObjs  = getCheckedObj("rowId");
				var ids = new Array();
				for(var i=0;i< idObjs.length;i++){
					ids[i] = idObjs[i].value;
				}
				$.msgBox.confirm("确定要删除选中的记录?", function(btn) {
					if (btn) {
						$.ajax({
									url : basePath + "permission/deleteList.do",
									cache : false,
									type: "POST",
									data :{
										ids : ids.join(",")
									},
									dataType:"json",
									success : function(data) {
										$.msgBox.confirmOK(data.message,function(){
											if (data.state) {
												window.location.reload();
											}
										})
									}
								});
					}
				});
			});
	// 修改
	$perTable.find("td .edit").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();
		$perQuery = $("#perQuery");
		$perQuery.attr("action", basePath + "permission/doEdit.do?id=" + id);
		$("#perQuery").submit();
	});
})