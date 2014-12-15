<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp" %>
<title>客户管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="<%=basePath%>icrm/customer/customer.main.js"></script>

</head>
<body>
	<div class="prom_cont">
		<div class="title">
	      <div class="fr tit_r">
	      	<form id="customerQuery" action="<c:url value='/customerController/index.do'></c:url>" >
		        <input type="text" class="tt" placeholder="输入客户名称…" name="custName" value="${customer.custName }">
		        <input type="text" class="tt" placeholder="输入客户编码…" name="custCode" value="${customer.custCode }">
		        <input type="submit" class="btn btn-s03" id="tt_query"   value="查询" />
		        <input type="submit" class="btn btn-s03" id="tt_add" value="新建" />
		        <input type="submit" class="btn btn-s03" id="tt_dispatch" value="分配" />
	      	</form>
	      </div>
	    </div>
		<table class="table table-bordered table-border-hd table-striped"  id="customerTable">
			<thead>
				<tr>
					<th width="0px;" style="display: none;"></th>
					<th width="3%"></th>
					<th width="4%">序号</th>
					<th width="6%">客户编码</th>
					<th width="20%">客户名称</th>
					<th width="10%">客户分类</th>
					<th width="10%">客户证件类型</th>
					<th width="20%">证件号码</th>
					<th width="20%"></th>
				</tr>
			</thead>
			<tbody id="customerTableBody">
				<c:forEach var="customer" items="${page.dataList}" varStatus="status" >
					<tr>
						<td style="display: none;">${customer.rowId}</td>
						<td><input type="checkbox" /></td>
						<td>${status.index+1}</td>
						<td>${customer.custCode}</td>
						<td>${customer.custName}</td>
						<td>${customer.custType}</td>
						<td>${customer.regType}</td>
						<td>${customer.regNbr}</td>
						<td>
							<a class="operate del" style="visibility: hidden;" title="删除" href="javascript:;"><font color="blue">删除</font></a>
							<i class="operate" style="visibility: hidden;">|</i>
							<a class="operate edit" style="visibility: hidden;" title="修改" href="javascript:;"><font color="blue">修改</font></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page_cont">
			<jsp:include page="/resources/jsp/page.jsp">
				<jsp:param name="url" value="customerController/index.do" />	
				<jsp:param name="urlParams" value="" />					
			</jsp:include>
		</div>
	</div>
</body>
</html>
