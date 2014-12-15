$(function(){

	var $J_Province = $("#J_Province");
	var $J_City = $("#J_City");
	var $J_Area = $("#J_Area");
	var $J_Town = $("#J_Town");
	var $list_frame = $("#list_frame");
	
	
	$J_Province.bind('change',function(){
		var parId = $(this).val();
		$J_City.html();
		$J_Area.html();
		$J_Town.html();
		$.ajax({
				url : basePath + "addrController/addressWinCombo.do?type=province&pid="+parId,
				cache : false,
				success : function(data) {
					updateOptions(data["city"],$J_City);
					updateOptions(data["area"],$J_Area);
					updateOptions(data["town"],$J_Town);
				}
			});
	});
	$J_City.bind('change',function(){
		var parId = $(this).val();
		$J_Area.html();
		$J_Town.html();
		$.ajax({
				url : basePath + "addrController/addressWinCombo.do?type=city&pid="+parId,
				cache : false,
				success : function(data) {
					updateOptions(data["area"],$J_Area);
					updateOptions(data["town"],$J_Town);
					$list_frame.attr("src","addressWinList.do?parentAddrId="+parId)
				}
			});
	});
	$J_Area.bind('change',function(){
		var parId = $(this).val();
		$J_Town.html();
		$.ajax({
				url : basePath + "addrController/addressWinCombo.do?type=area&pid="+parId,
				cache : false,
				success : function(data) {
					updateOptions(data["town"],$J_Town);
					$list_frame.attr("src","addressWinList.do?parentAddrId="+parId)
				}
			});
	});
	$J_Town.bind('change',function(){
		var parId = $(this).val();
		$list_frame.attr("src",basePath+"addrController/addressWinList.do?parentAddrId="+parId);
	});

	/**
	 * 更新值列表选项
	 */
	var updateOptions = function(_array,$obj){
		var options = "";
		$(_array).each(function(index){
			options =options+ "<option value='"+_array[index].rowId+"'>"+_array[index].address+"</option>"
			if(index+1==_array.length){
				$obj.html(options);
			}
		});
	}
	
});