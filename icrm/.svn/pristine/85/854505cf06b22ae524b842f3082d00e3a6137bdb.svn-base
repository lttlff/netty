<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp"%>

<title>已分配客户</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="客户分配">

</head>
<body>
	<div class="prom_cont">
		<div class="title">
			<div class="fr tit_r">
				<form id="customerQuery" action="<c:url value='/customerAllotController/alloted.do'></c:url>">
					<input type="text" class="tt" placeholder="输入客户名称…" name="custName" value="${customer.custName }">
					<input type="text" class="tt" placeholder="输入客户编码…" name="custCode" value="${customer.custCode }"> 
					<input type="submit" class="btn btn-s03" id="tt_query" value="查询" />
					<input type="submit" class="btn btn-s03" id="tt_dispatch" value="分配" />
				</form>
			</div>
		</div>
		<table class="table table-bordered table-border-hd table-striped"
			id="mainTable">
			<thead>
				<tr>
					<th width="0px;" style="display: none;"></th>
					<th width="15px;"></th>
					<th width="35px;">序号</th>
					<th width="90px;">客户名称</th>
					<th width="80px;">客户类型</th>
					<th width="80px;">客户分类</th>
					<th width="90px;">客户证件类型</th>
					<th width="100px;">证件号码</th>
					<th width="80px;">客户标志</th>
					<th width="80px;">销售区域</th>
					<th width="150px;">地址</th>
					<th >销售人员</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="customer" items="${page.dataList}" varStatus="status">
					<tr>
						<td style="display: none;">${customer.rowId}</td>
						<td><input type="checkbox" /></td>
						<td>${status.index+1}</td>
						<td>${customer.custName}</td>
						<td>${customer.custType_text}</td>
						<td>${customer.custCat_text}</td>
						<td>${customer.regType_text}</td>
						<td>${customer.regNbr}</td>
						<td>${customer.custFlag_text}</td>
						<td>${customer.saleAreaId}</td>
						<td>TODO地址</td>
						<td title="aofaposifdpoaifdpaoifdp">销售人员nimeafafdadsfads</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page_cont">
			<jsp:include page="/resources/jsp/page.jsp">
				<jsp:param name="url" value="customerAllotController/alloted.do" />
				<jsp:param name="urlParams" value="" />
			</jsp:include>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	var tooltips = $( "[title]" ).tooltip();
	
	
	
})
</script>
</html>
