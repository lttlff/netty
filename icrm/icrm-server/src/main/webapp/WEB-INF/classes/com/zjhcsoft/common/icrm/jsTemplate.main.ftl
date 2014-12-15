$(document).ready(function() {
	var $${alias}Table = $("#${alias}Table");
	$${alias}Table.find("tr").bind("mouseover", function() {
				$(this).find(".operate").css("visibility", "visible");
			}).bind("mouseout", function() {
				$(this).find(".operate").css("visibility", "hidden");
			}).bind("click", function() {
				var $checkbox = $(this).find("td input[type=checkbox]");
				$checkbox.prop("checked", !$checkbox.prop("checked"));
			});

	$${alias}Table.find("td input[type=checkbox]").bind("click", function() {
				$(this).prop("checked", !$(this).prop("checked"));
			});

	// 删除
	$${alias}Table.find("td .delete").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();

		$.msgBox.confirm("确定要删除选中的记录?", function(btn) {
					if (btn == "ok") {
						$.ajax({
									url : basePath + "${alias}Controller/delete/"
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
	$${alias}Table.find("td .edit").bind("click", function() {
		var $tr = $(this).parent().parent();
		var id = $tr.find("td:first").html();

		$${alias}Query = $("#${alias}Query");
		$${alias}Query.attr("action", basePath + "${alias}Controller/doEdit/" + id
						+ ".do");
		$("#${alias}Query").submit();
	});

	// 查询
	$("#${alias}Query #tt_query").bind('click', function() {
				$${alias}Query = $("#${alias}Query");
				$${alias}Query.attr("action", basePath + "${alias}Controller/index.do")
				$("#${alias}Query").submit();
			});
	// 新增
	$("#${alias}Query #tt_add").bind('click', function() {
				$${alias}Query = $("#${alias}Query");
				$${alias}Query.attr("action", basePath
								+ "${alias}Controller/doEdit/.do")
				$("#${alias}Query").submit();
			});
});
