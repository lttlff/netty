<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp" %>

<title>组织管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="组织管理">

<script src="<%=basePath%>rbac/organise/organise.main.js" type="text/javascript"></script>
</head>
<body>
<div class="prom_cont">
	<div class="title">
      <div class="fr tit_r">
      	<form id="orgQuery" action="<c:url value='/organise/main.do'></c:url>" method="post">
	        <input type="text" class="tt" placeholder="输入编码…" name="orgCode" value="${organise.orgCode }">
	        <input type="text" class="tt" placeholder="输入名称…" name="orgName" value="${organise.orgName }">
	        <input type="submit" class="btn btn-s03" id="tt_query"   value="查询" />
	        <input type="submit" class="btn btn-s03" id="tt_add" value="新建" />
	        <input type="button" class="btn btn-s03" id="tt_del" value="删除" />
	        <input type="hidden" name="parOrgId" value="${organise.parOrgId }"/>
      	</form>
      </div>
    </div>	
	<table class="table table-bordered table-border-hd table-striped"  id="orgTable">
		<thead>
			<tr>
				<th width="0px;" style="display: none;"></th>
				<th width="3%"></th>
				<th width="4%">序号</th>
				<th width="10%">编码</th>
				<th width="20%">名称</th>
				<th width="20%">类型</th>
				<th width="20%">描述</th>
				<th width="20%"></th>
			</tr>
		</thead>
		<tbody id="orgTableBody">
			<c:forEach var="org" items="${page.dataList}" varStatus="status">
				<tr>
					<td style="display: none;">${org.rowId}</td>
					<td><input type="checkbox" name="rowId" value="${org.rowId}"/></td>
					<td>${status.index+1}</td>
					<td>${org.orgCode}</td>
					<td>${org.orgName}</td>
					<td>${org.orgType_text}</td>
					<td>${org.orgDesc}</td>
					<td>
						<a class="operate delete" style="visibility: hidden;" title="删除" href="javascript:;">删除&nbsp;</a>
						<a class="operate edit" style="visibility: hidden;" title="修改" href="javascript:;">修改&nbsp;</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>                       
	</table>
	<div class="page_cont">
		<jsp:include page="/resources/jsp/page.jsp">
			<jsp:param name="url" value="organise/main.do" />	
			<jsp:param name="urlParams" value="&orgCode=${organise.orgCode }&orgName=${organise.orgName }&parOrgId=${organise.parOrgId }" />					
		</jsp:include>
	</div>
</div>
</body>
</html>