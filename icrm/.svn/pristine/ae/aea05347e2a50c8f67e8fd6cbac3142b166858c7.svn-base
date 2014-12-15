<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jstree.jsp" %>
<title>菜单选择</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="<%=basePath%>rbac/menu/menu.win.js"></script>

</head>
<body>
<div class="prom_cont">
	<div class="title">
      <div class="fr tit_r">
      	<form id="userQuery" action="<c:url value='/menu/goMenuSelectWin.do'></c:url>" >
	        <input type="text" class="tt" placeholder="输入名称…" name="menuName" value="${menu.menuName }"/>
	        <input type="submit" class="btn btn-s03" id="tt_query"   value="查询" />
      	</form>
      </div>
    </div>	
	<table class="table table-bordered table-border-hd table-striped"  id="mainTable">
		<thead>
			<tr>
				<th width="0px;" style="display: none;"></th>
				<th width="3%"></th>
				<th width="4%">序号</th>
				<th width="10%">姓名</th>
				<th width="20%">路径</th>
				<th width="20%">类型</th>
				<th width="20%">描述</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="menu" items="${page.dataList}" varStatus="status">
				<tr>
					<td style="display: none;">${menu.rowId}</td>
					<td><input type="checkbox" /></td>
					<td>${status.index+1}</td>
					<td>${menu.menuName}</td>
					<td>${menu.menuUrl}</td>
					<td>${menu.menuType_text}</td>
					<td>${menu.menuDesc}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="page_cont">
		<jsp:include page="/resources/jsp/page.jsp">
			<jsp:param name="url" value="menu/goMenuSelectWin.do" />
			<jsp:param name="urlParams" value="&menuName=${menu.menuName }" />
		</jsp:include>
	</div>
</div>
</body>
<script type="text/javascript">
	
	$(function(){
		var $mainTable = $("#mainTable");
		$mainTable.find("tr").bind("click", function() {
					var $checkbox = $(this).find("td input[type=checkbox]");
					$checkbox.prop("checked", !$checkbox.prop("checked"));
					$(this).siblings().find("td input[type=checkbox]").prop("checked", false);//单选
				});

		$mainTable.find("td input[type=checkbox]").bind("click", function() {
					$(this).prop("checked", !$(this).prop("checked"));
				});
	});


</script>

</html>
