<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp" %>
<title>联系人信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<c:set var="indexUrl" value="contactsController/index.do" ></c:set>
</head>
<body>
	<div class="prom_cont">
		<div class="title">
	      <div class="fr tit_r">
	      	<form id="query" action="<c:url value='/${indexUrl }'></c:url>" >
		        <input type="text" class="tt" placeholder="联系人名称..." name="contactsName" value="${contacts.contactsName }">
		        <input type="submit" class="btn btn-s03" id="tt_query"  value="查询" />
		        <input type="button" class="btn btn-s03" id="tt_add" value="新建" />
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
					<th width="20%"></th>
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
						<td>
							<a class="operate del" style="visibility: hidden;" title="删除" href="javascript:;">删除</a>
							<i class="operate" style="visibility: hidden;">|</i>
							<a class="operate edit" style="visibility: hidden;" title="修改" href="javascript:;">修改</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page_cont">
			<jsp:include page="/resources/jsp/page.jsp">
				<jsp:param name="url" value="contactsController/index.do" />	
				<jsp:param name="urlParams" value="" />					
			</jsp:include>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var $mainTable = $("#mainTable");
		$mainTable.find("tr").bind("mouseover", function() {
					$(this).find(".operate").css("visibility", "visible");
				}).bind("mouseout", function() {
					$(this).find(".operate").css("visibility", "hidden");
				}).bind("click", function() {
					var $checkbox = $(this).find("td input[type=checkbox]");
					$checkbox.prop("checked", !$checkbox.prop("checked"));
				});

		$mainTable.find("td input[type=checkbox]").bind("click", function() {
					$(this).prop("checked", !$(this).prop("checked"));
				});
		//修改
		$mainTable.find("td .edit").bind("click", function() {
			var $tr = $(this).parent().parent();
			var id = $tr.find("td:first").html();

			$query = $("#query");
			$query.attr("action", basePath + "contactsController/doEdit/" + id
							+ ".do");
			$query.submit();
		});
		
		//删除
		$mainTable.find("td .del").bind("click", function() {
			var $tr = $(this).parent().parent();
			var id = $tr.find("td:first").html();
			$.msgBox.confirm("确定要删除选中的记录?", function(btn) {
						if (btn == "ok") {
							$.ajax({
								url : basePath + "contactsController/delete/"
										+ id + ".do",
								cache : false,
								success : function(data) {
									$.msgBox.info(data.message);
									if (data.success) {
										window.location.href=basePath+"contactsController/index.do";
									}
								}
							});
						}
					});
		});
	
		
		
		// 查询
		$("#query #tt_query").bind('click', function() {
					$query = $("#query");
					$query.attr("action", basePath + "${indexUrl }")
					$query.submit();
				});
		// 新增
		$("#query #tt_add").bind('click', function() {
					$query = $("#query");
					$query.attr("action", basePath +"contactsController/doEdit/.do")
					$query.submit();
				});
	});
</script>
</html>
