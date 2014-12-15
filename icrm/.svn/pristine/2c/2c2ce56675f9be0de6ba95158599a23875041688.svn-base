<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp" %>
<title>客户选择</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="<%=basePath%>icrm/customer/customer.main.js"></script>

<c:set var="indexUrl" value="/customerController/index.do/index.do" ></c:set>
</head>
<body>
	<div class="prom_cont">
		<div class="title">
	      <div class="fr tit_r">
	      	<form id="mainForm" action="<c:url value='/${indexUrl }'></c:url>" >
		        <input type="text" class="tt" placeholder="输入客户名称…" name="custName" value="${customer.custName }">
		        <input type="text" class="tt" placeholder="输入客户编码…" name="custCode" value="${customer.custCode }">
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
					<th width="20%">客户名称</th>
					<th width="6%">客户编码</th>
					<th width="10%">客户分类</th>
					<th width="10%">客户证件类型</th>
					<th width="20%">证件号码</th>
				</tr>
			</thead>
			<tbody id="customerTableBody">
				<c:forEach var="item" items="${page.dataList}" varStatus="status" >
					<tr>
						<td style="display: none;">${item.rowId}</td>
						<td><input type="checkbox" /></td>
						<td>${status.index+1}</td>
						<td>${item.custName}</td>
						<td>${item.custCode}</td>
						<td>${item.custType}</td>
						<td>${item.regType}</td>
						<td>${item.regNbr}</td>
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
</body>
<script type="text/javascript">
$(document).ready(function(){
	var $mainTable = $("#mainTable");
	$mainTable.find("tr").bind("mouseover", function() {
			}).bind("mouseout", function() {
			}).bind("click", function() {
				var $checkbox = $(this).find("td input[type=checkbox]");
				$checkbox.prop("checked", !$checkbox.prop("checked"));
				$(this).siblings().find("td input[type=checkbox]").prop("checked", false);
			});

	$mainTable.find("td input[type=checkbox]").bind("click", function() {
				$(this).prop("checked", !$(this).prop("checked"));
			});
});
</script>
</html>
