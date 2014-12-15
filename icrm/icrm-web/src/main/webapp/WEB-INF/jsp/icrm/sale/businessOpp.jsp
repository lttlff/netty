<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp" %>
<title>商机管理</title>
<meta http-equiv="pragma" content="no-cache">

</head>
<c:set var="indexUrl" value="contactsController/index.do" ></c:set>
<body>
	<div class="prom_cont">
		<div class="title">
	      <div class="fr tit_r">
	      	<form id="domainQuery" action="<c:url value='/domainController/index.do'></c:url>" >
		        <input type="text" class="tt" placeholder="输入客户名称…" name="custName" value="${domain.custName }">
		        <input type="text" class="tt" placeholder="输入产品名称…" name="custCode" value="${domain.custCode }">
		        <input type="text" class="tt" placeholder="输入所处阶段…" name="custCode" value="${domain.custCode }">
		        <input type="submit" class="btn btn-s03" id="tt_query"   value="查询" />
		        <input type="submit" class="btn btn-s03" id="tt_add" value="新建" />
	      	</form>
	      </div>
	    </div>
		<table class="table table-bordered table-border-hd table-striped"  id="domainTable">
			<thead>
				<tr>
					<th width="0px;" style="display: none;"></th>
					<th width="3%"></th>
					<th width="4%">序号</th>
					<th width="6%">机会名称</th>
					<th width="20%">客户名称</th>
					<th width="10%">所处阶段</th>
					<th width="10%">意向购买产品</th>
					<th width="20%"></th>
				</tr>
			</thead>
			<tbody id="domainTableBody">
				<c:forEach var="domain" items="${page.dataList}" varStatus="status" >
					<tr>
						<td style="display: none;">${domain.rowId}</td>
						<td><input type="checkbox" /></td>
						<td>${status.index+1}</td>
						<td>${domain.oppName}</td>
						<td>${domain.custName}</td>
						<td>${domain.salePeriod.salePeriodName}</td>
						<td>${domain.prodName}</td>
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
				<jsp:param name="url" value="domainController/index.do" />	
				<jsp:param name="urlParams" value="" />					
			</jsp:include>
		</div>
	</div>
</body>
</html>
