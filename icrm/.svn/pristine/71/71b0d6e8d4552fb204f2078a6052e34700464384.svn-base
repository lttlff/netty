$(document).ready(function() {

	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
					if (o[this.name]) {
						if (!o[this.name].push) {
							o[this.name] = [o[this.name]];
						}
						o[this.name].push(this.value || '');
					} else {
						o[this.name] = this.value || '';
					}
				});
		return o;
	};

	$("#dynamicFormSubmit").click(function() {
		var $dynamicForm = $('#dynamicForm');
		var dynamicForm = $dynamicForm[0];
		var quertJson = $dynamicForm.serializeObject();
		var queryString = JsonToString(quertJson);
		$.ajax({
					url : dynamicForm.action,
					data : {
						dynamicJson : queryString
					},
					success : function() {

						
					}
				});

		return false;
	});
	$('#dynamicForm').ajaxForm({
				dateType : 'json',
				success : function(data) {
					alert("success");
				}
			});

	var dynamicScript = $('.trigger').attr('_script');
	eval(dynamicScript);
});

function JsonToString(o) {
	var arr = [];
	var fmt = function(s) {
		if (typeof s == 'object' && s != null)
			return JsonToString(s);
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}
	for (var i in o)
		arr.push("'" + i + "':" + fmt(o[i]));
	return '{' + arr.join(',') + '}';
}