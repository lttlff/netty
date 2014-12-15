$(document).ready(function() {
	var $roleTable = $("#roleTable");
	$roleTable.find("tr").bind("mouseover", function() {
				$(this).find(".operate").css("visibility", "visible");
			}).bind("mouseout", function() {
				$(this).find(".operate").css("visibility", "hidden");
			}).bind("click", function() {
				
			});
	// 删除
	$roleTable.find("td .delete").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();
		
		$.msgBox.confirm("确定要删除选中的记录?", function(btn) {
					if (btn) {
						$.ajax({
									url : basePath + "role/delete.do",
									cache : false,
									type: "POST",
									data :{
										id : id	
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
	$("#roleQuery #tt_query").bind('click', function() {
				$roleQuery = $("#roleQuery");
				$roleQuery.attr("action", basePath + "role/index.do")
				$("#roleQuery").submit();
			});
	// 新增
	$("#roleQuery #tt_add").bind('click', function() {
				$roleQuery = $("#roleQuery");
				$roleQuery.attr("action", basePath	+ "role/doEdit.do")
				$("#roleQuery").submit();
			});
	// 修改
	$roleTable.find("td .edit").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();
		$roleQuery = $("#roleQuery");
		$roleQuery.attr("action", basePath + "role/doEdit.do?id=" + id);
		$("#roleQuery").submit();
//		window.location.href = basePath + "role/doEdit.do?id=" + id;
	});
})