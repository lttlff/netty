$(document).ready(function() {
	var $customerTable = $("#customerTable");
	$customerTable.find("tr").bind("mouseover", function() {
				$(this).find(".operate").css("visibility", "visible");
			}).bind("mouseout", function() {
				$(this).find(".operate").css("visibility", "hidden");
			}).bind("click", function() {
				var $checkbox = $(this).find("td input[type=checkbox]");
				$checkbox.prop("checked", !$checkbox.prop("checked"));
			});

	$customerTable.find("td input[type=checkbox]").bind("click", function() {
				$(this).prop("checked", !$(this).prop("checked"));
			});

	// 删除
	$customerTable.find("td .del").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();

		$.msgBox.confirm("确定要删除选中的记录?", function(isYes) {
					if (isYes) {
						$.ajax({
									url : basePath + "customerController/delete/"
											+ id + ".do",
									cache : false,
									success : function(data) {
										$.msgBox.info(data.message)
										if (data.success) {
											window.location.reload();
										}
									}
								});
					}
				});
	});

	// 修改
	$customerTable.find("td .edit").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();

		$customerQuery = $("#customerQuery");
		$customerQuery.attr("action", basePath + "customerController/doEdit/" + id
						+ ".do");
		$("#customerQuery").submit();
	});

	// 查询
	$("#customerQuery #tt_query").bind('click', function() {
				$customerQuery = $("#customerQuery");
				$customerQuery.attr("action", basePath + "customerController/index.do")
				$("#customerQuery").submit();
			});
	// 新增
	$("#customerQuery #tt_add").bind('click', function() {
				$customerQuery = $("#customerQuery");
				$customerQuery.attr("action", basePath
								+ "customerController/doEdit/.do")
				$("#customerQuery").submit();
			});
});
