<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp"%>

<title>未分配联系人</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="客户分配">


<c:set var="indexUrl" value="contactsController/disAllot.do" ></c:set>
</head>
<body>
	<div class="prom_cont">
		<div class="title">
	      <div class="fr tit_r">
	      	<form id="query" action="<c:url value='/${indexUrl }'></c:url>" >
		        <input type="text" class="tt" placeholder="联系人名称..." name="contactsName" value="${contacts.contactsName }">
		        <input type="submit" class="btn btn-s03" id="tt_query"  value="查询" />
		        <input type="button" class="btn btn-s03" id="tt_allot" value="分配" />
	      	</form>
	      </div>
	    </div>
		<table class="table table-bordered table-border-hd table-striped"  id="mainTable">
			<thead>
				<tr>
					<th width="0px;" style="display: none;"></th>
					<th width="3%"></th>
					<th width="4%">序号</th>
					<th width="6%">联系人</th>
					<th width="20%">部门</th>
					<th width="10%">地址</th>
					<th width="10%">客户名称</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="contacts" items="${page.dataList}" varStatus="status" >
					<tr>
						<td style="display: none;">${contacts.rowId}</td>
						<td><input type="checkbox" /></td>
						<td>${status.index+1}</td>
						<td>${contacts.contactsName}</td>
						<td>${contacts.department}</td>
						<td>${contacts.addrFull}</td>
						<td>TODO客户名称</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page_cont">
			<jsp:include page="/resources/jsp/page.jsp">
				<jsp:param name="url" value="${indexUrl }" />	
				<jsp:param name="urlParams" value="" />					
			</jsp:include>
		</div>
	</div>
<div id="windiv">
	<iframe src="" width="99%"  height="435px" style="display: none;" id="win_iframe" name="win_iframe"></iframe>
</div>
</body>
 <script>
	$(function() {
		var $mainTable = $("#mainTable");
		$mainTable.find("tr").bind("mouseover", function() {
			
			}).bind("mouseout", function() {
				
			}).bind("click", function() {
				var $checkbox = $(this).find("td input[type=checkbox]");
				$checkbox.prop("checked", !$checkbox.prop("checked"));
			}).find("td input[type=checkbox]").bind("click", function() {
				$(this).prop("checked", !$(this).prop("checked"));
			});
		$("#tt_allot").bind('click',function(){
			var $tr = $mainTable.find("td input:checked").parents("tr");
			if($tr.length==0){
				$.msgBox.info("请先选择要分配的联系人记录!");
				return;
			}else{
				var contactsIdArr = new Array();
				for(var i=0;i<$tr.length;i++){
					contactsIdArr[contactsIdArr.length] = $($tr[i]).find('td:first').html();
				}
				var $winIframe = $("#win_iframe");
				$winIframe.attr("src",basePath+"userController/userWin.do");
				$winIframe.css('display', 'block');
				$("#windiv").dialog({
					autoOpen : true,
					title : '销售人员选择',
					width : 809,
					height : 500,
					modal : true,
					show : {
						effect : "blind",
						duration : 200
					},
					close : function(event, ui) {
						//关闭dialog事件
					},
					buttons: {
						'选择':function(){
							var dialog = $(this);
							var $table = $(window.frames["win_iframe"].document).find("#mainTable");
							var $tr = $table.find("td input:checked").parents("tr:first");
							if($tr.length==0){
								$.msgBox.info("请选择一条记录!");
								return;
							}else{
								var id = $tr.find('td:first').html();
								$.ajax({
									  url: basePath + "contactsPoolController/insertByContactsId.do",
									  data: {
										  userId:id,
										  contactsIdArr:contactsIdArr+""
									  },
									  success: function(_resp){
										  	if(_resp.success){
										    	$.msgBox.confirmOK(_resp.message,function(){
										    		dialog.dialog("close");//确定后关闭弹出框
										    		window.location.reload();
										    	});
										  	}else{
										  		$.msgBox.info(_resp.message);
										  	}
									  }
									});
							}
						} ,
					    "关闭": function() {
					    	$(this).dialog("close");
					    }
					}
				})
				
			}
		});
	});
</script>
</html>
