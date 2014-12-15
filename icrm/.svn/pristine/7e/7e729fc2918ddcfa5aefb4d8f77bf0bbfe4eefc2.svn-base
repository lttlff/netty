function submitLayout(){

	var $demo = $(".demo");
	
	var arr = new Array();
	
	var fieldJson,formJson,fieldArr;
	var formArr = new Array();
	$demo.find("form").each(function(i,item){//遍历表单
		formJson = {};
		formJson["name"]="新建的表单",
		formJson["group"] = 0;
		$(item).children("div").each(function(j,item2){//遍历表单中的所有列
			formJson["group"] = formJson["group"]+1;// form中列数
			fieldArr = new Array();
			$(item2).find("*[field_attr]").each(function(k,item3){//遍历当前列下的所有表单元素
				fieldJson =  $.extend({}, JSON.parse((item3.attributes["field_data"]||"{}").value||"{}"));
				fieldJson["fieldId"] = item3.attributes["field_attr"].value;
				fieldArr.push(fieldJson);
			});
			formJson["group"+j] = fieldArr;
		});
		formArr.push(formJson);
	});
	console.info(formArr);
	return;
	$.ajax({  
		type: "POST",  
		url: basePath +"dynamicManagerController/saveFormDefined.do",  
		data: {
			formStr:JSON.stringify(formArr)
		},  
		success: function(data) {
			console.info(data);
		}
	});
	
}

//$(document).ready(function(){
//	 $("div .date").datetimepicker({
//	        format: 'yyyy-dd-MM hh:mm',
//	        language: 'en',
//	        pickDate: true,
//	        pickTime: true,
//	        hourStep: 1,
//	        minuteStep: 15,
//	        secondStep: 30,
//	        inputMask: true
//	      });
//	
//})