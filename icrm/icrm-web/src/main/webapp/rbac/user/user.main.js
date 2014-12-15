$(document).ready(function() {
	var $userTable = $("#userTable");
	$userTable.find("tr").bind("mouseover", function() {
				$(this).find(".operate").css("visibility", "visible");
			}).bind("mouseout", function() {
				$(this).find(".operate").css("visibility", "hidden");
			}).bind("click", function() {
				var $checkbox = $(this).find("td input[type=checkbox]");
				$checkbox.prop("checked", !$checkbox.prop("checked"));
			});

	$userTable.find("td input[type=checkbox]").bind("click", function() {
				$(this).prop("checked", !$(this).prop("checked"));
			});

	// 删除
	$userTable.find("td .delete").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();

		$.msgBox.confirm("确定要删除选中的记录?", function(btn) {
					if (btn) {
						$.ajax({
									url : basePath + "userController/delete/"
											+ id + ".do",
									cache : false,
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
	$userTable.find("td .edit").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();

		$userQuery = $("#userQuery");
		$userQuery.attr("action", basePath + "userController/doEdit/" + id
						+ ".do");
		$("#userQuery").submit();
	});

	// 查询
	$("#userQuery #tt_query").bind('click', function() {
				$userQuery = $("#userQuery");
				$userQuery.attr("action", basePath + "userController/index.do")
				$("#userQuery").submit();
			});
	// 新增
	$("#userQuery #tt_add").bind('click', function() {
				$userQuery = $("#userQuery");
				$userQuery.attr("action", basePath
								+ "userController/doEdit/.do")
				$("#userQuery").submit();
			});
});