$(document).ready(function() {
	var $posTable = $("#posTable");
	$posTable.find("tr").bind("mouseover", function() {
				$(this).find(".operate").css("visibility", "visible");
			}).bind("mouseout", function() {
				$(this).find(".operate").css("visibility", "hidden");
			}).bind("click", function() {
				
			});
	// 删除
	$posTable.find("td .delete").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();
		
		$.msgBox.confirm("确定要删除选中的记录?", function(btn) {
					if (btn) {
						$.ajax({
									url : basePath + "position/deleteList.do",
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
												//调用父窗口刷新树
												window.parent.reloadTree();
											}
										})
									}
								});
					}
				});
	});
	
	// 查询
	$("#posQuery #tt_query").bind('click', function() {
				$posQuery = $("#posQuery");
				$posQuery.attr("action", basePath + "position/main.do")
				$("#posQuery").submit();
			});
	// 新增
	$("#posQuery #tt_add").bind('click', function() {
				$posQuery = $("#posQuery");
				$posQuery.attr("action", basePath	+ "position/doEdit.do")
				$("#posQuery").submit();
			});
	// 多条删除
	$("#posQuery #tt_del").bind('click', function() {
				$posQuery = $("#posQuery");
				if(!hasChecked("rowId")){
					$.msgBox.alert("请选择要删除的记录!");
					return;
				}
				var idObjs  = getCheckedObj("rowId");
				var idArray = new Array();
				for(var i=0;i< idObjs.length;i++){
					idArray[i] = idObjs[i].value;
				}
				$.msgBox.confirm("确定要删除选中的记录?", function(btn) {
					if (btn) {
						$.ajax({
									url : basePath + "position/deleteList.do",
									cache : false,
									type: "POST",
									data :{
										'ids' : idArray.join(",")
									},
									dataType:"json",
									success : function(data) {
										$.msgBox.confirmOK(data.message,function(){
											if (data.state) {
												window.location.reload();
												//调用父窗口刷新树
												window.parent.reloadTree();
											}
										})
									}
								});
					}
				});
			});
	// 修改
	$posTable.find("td .edit").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();
		$posQuery = $("#posQuery");
		$posQuery.attr("action", basePath + "position/doEdit.do?id=" + id);
		$("#posQuery").submit();
	});
	
})