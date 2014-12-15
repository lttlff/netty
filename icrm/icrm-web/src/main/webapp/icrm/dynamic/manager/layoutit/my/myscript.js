/*function supportstorage() {
	if (typeof window.localStorage == 'object')
		return true;
	else
		return false;
}

function handleSaveLayout() {
	var e = $(".demo").html();
	if (!stopsave && e != window.demoHtml) {
		stopsave++;
		window.demoHtml = e;
		saveLayout();
		stopsave--;
	}
}

var layouthistory;
function saveLayout() {
	var data = layouthistory;
	if (!data) {
		data = {};
		data.count = 0;
		data.list = [];
	}
	if (data.list.length > data.count) {
		for (i = data.count; i < data.list.length; i++)
			data.list[i] = null;
	}
	data.list[data.count] = window.demoHtml;
	data.count++;
	if (supportstorage()) {
		localStorage.setItem("layoutdata", JSON.stringify(data));
	}
	layouthistory = data;
	// console.log(data);
	
	 * $.ajax({ type: "POST", url: "/build/saveLayout", data: { layout:
	 * $('.demo').html() }, success: function(data) {
	 * //updateButtonsVisibility(); } });
	 
}




function cleanHtml(e) {
	$(e).parent().append($(e).children().html())
}
function downloadLayoutSrc() {
	var e = "";
	$("#download-layout").children().html($(".demo").html());
	var t = $("#download-layout").children();
	t.find(".preview, .configuration, .drag, .remove").remove();//操作元素
	t.find(".lyrow").addClass("removeClean");
	t.find(".box-element").addClass("removeClean");
	t.find(".lyrow .lyrow .lyrow .lyrow .lyrow .removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".lyrow .lyrow .lyrow .lyrow .removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".lyrow .lyrow .lyrow .removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".lyrow .lyrow .removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".lyrow .removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".removeClean").remove();
	$("#download-layout .column").removeClass("ui-sortable");
	$("#download-layout .row-fluid").removeClass("clearfix").children().removeClass("column");
	if ($("#download-layout .container").length > 0) {
		changeStructure("row-fluid", "row")
	}
	formatSrc = $.htmlClean($("#download-layout").html(), {
		format: true,
		allowedAttributes: [
			["id"],
			["class"],
			["data-toggle"],
			["data-target"],
			["data-parent"],
			["role"],
			["data-dismiss"],
			["aria-labelledby"],
			["aria-hidden"],
			["data-slide-to"],
			["data-slide"]
		]
	});
	$("#download-layout").html(formatSrc);
	$("#downloadModal textarea").empty();
	$("#downloadModal textarea").val(formatSrc)
}


$(document).ready(function() {
			// 保存按钮事件
			$("[data-target=#shareModal]").click(function(e) {
						e.preventDefault();
						handleSaveLayout();
					});

			//下载按钮事件		
			$("[data-target=#downloadModal]").click(function(e) {
						e.preventDefault();
						downloadLayoutSrc();
					});
		});*/