<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp" %>

<title>权限管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="权限管理">

<script src="<%=basePath%>rbac/permission/permission.main.js" type="text/javascript"></script>
</head>
<body>
<div class="prom_cont">
	<div class="title">
      <div class="fr tit_r">
      	<form id="perQuery" action="<c:url value='/permission/index.do'></c:url>" method="post">
	        <input type="text" class="tt" placeholder="输入编码…" name="perCode" value="${permission.perCode }">
	        <input type="text" class="tt" placeholder="输入名称…" name="perName" value="${permission.perName }">
	        <input type="submit" class="btn btn-s03" id="tt_query"   value="查询" />
	        <input type="submit" class="btn btn-s03" id="tt_add" value="新建" />
	        <input type="submit" class="btn btn-s03" id="tt_del" value="删除" />
      	</form>
      </div>
    </div>	
	<table class="table table-bordered table-border-hd table-striped"  id="perTable">
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
		<tbody id="perTableBody">
			<c:forEach var="per" items="${page.dataList}" varStatus="status">
				<tr>
					<td style="display: none;">${per.rowId}</td>
					<td><input type="checkbox" name="rowId" value="${per.rowId}"/></td>
					<td>${status.index+1}</td>
					<td>${per.perCode}</td>
					<td>${per.perName}</td>
					<td>${per.perType_text}</td>
					<td>${per.perDesc}</td>
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
			<jsp:param name="url" value="permission/index.do" />	
			<jsp:param name="urlParams" value="&perCode=${permission.perCode }&perName=${permission.perName }" />					
		</jsp:include>
	</div>
</div>
</body>
</html>
