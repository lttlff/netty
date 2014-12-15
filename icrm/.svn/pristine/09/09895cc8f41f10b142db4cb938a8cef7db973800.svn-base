<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp" %>

<link href="<%=basePath%>resources/css/module.css" rel="stylesheet" type="text/css" />

<title>客户管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="<%=basePath%>icrm/${alias}/${alias}.main.js"></script>

</head>
<body>
	<div class="prom_cont">
		<div class="title">
	      <div class="fr tit_r">
	      	<form id="${alias}Query" action="<c:url value='/${alias}Controller/index.do'></c:url>" >
		        <input type="submit" class="btn btn-s03" id="${alias}_query"  value="查询" />
		        <input type="submit" class="btn btn-s03" id="${alias}_create" value="新建" />
	      	</form>
	      </div>
	    </div>
		<table class="table table-bordered table-border-hd table-striped"  id="${alias}Table">
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
			<tbody id="${alias}TableBody">
				<c:forEach var="${alias}" items="${page.dataList}" varStatus="status" >
					<tr>
						<td style="display: none;">${${alias}.rowId}</td>
						<td><input type="checkbox" /></td>
						<td>${status.index+1}</td>
						<td>${${alias}.custCode}</td>
						<td>${${alias}.custName}</td>
						<td>${${alias}.custType}</td>
						<td>${${alias}.regType}</td>
						<td>${${alias}.regNbr}</td>
						<td>
							<a class="operate delete" style="visibility: hidden;" title="删除" href="javascript:;">&nbsp;</a>
							<a class="operate update" style="visibility: hidden;" title="修改" href="javascript:;">&nbsp;</a>
							<a class="operate detail" style="visibility: hidden;" title="详细" href="javascript:;">&nbsp;</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page_cont">
			<jsp:include page="/resources/jsp/page.jsp">
				<jsp:param name="url" value="${alias}Controller/index.do" />	
				<jsp:param name="urlParams" value="" />					
			</jsp:include>
		</div>
	</div>
</body>
</html>
