<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp" %>

<title>用户管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="用户管理">

<script src="<%=basePath%>rbac/user/user.main.js" type="text/javascript"></script>

</head>
<body>
<div class="prom_cont">
	<div class="title">
      <div class="fr tit_r">
      	<form id="userQuery" action="<c:url value='/userController/index.do'></c:url>" >
	        <input type="text" class="tt" placeholder="输入姓名…" name="realName" value="${user.realName }">
	        <input type="text" class="tt" placeholder="输入帐号…" name="userName" value="${user.userName }">
	        <input type="submit" class="btn btn-s03" id="tt_query"   value="查询" />
	        <input type="submit" class="btn btn-s03" id="tt_add" value="新建" />
      	</form>
      </div>
    </div>	
	<table class="table table-bordered table-border-hd table-striped"  id="userTable">
		<thead>
			<tr>
				<th width="0px;" style="display: none;"></th>
				<th width="3%"></th>
				<th width="4%">序号</th>
				<th width="10%">姓名</th>
				<th width="20%">帐号</th>
				<th width="10%">类型</th>
				<th width="20%">移动电话</th>
				<th width="20%"></th>
			</tr>
		</thead>
		<tbody id="userTableBody">
			<c:forEach var="user" items="${page.dataList}" varStatus="status">
				<tr>
					<td style="display: none;">${user.rowId}</td>
					<td><input type="checkbox" /></td>
					<td>${status.index+1}</td>
					<td>${user.realName}</td>
					<td>${user.userName}</td>
					<td>${user.userType_text}</td>
					<td>${user.mobilePhone}</td>
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
			<jsp:param name="url" value="userController/index.do" />	
			<jsp:param name="urlParams" value="&realName=${user.realName }&userName=${user.userName }" />					
		</jsp:include>
	</div>
	</div>


<!-- 	用户信息编辑框	 -->
<!-- 
<div class="modal fade" id="userEditForm" tabindex="-1" role="dialog"  aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" >用户编辑</h4>
      </div>
      <div class="modal-body">
        <form>
        	<input type="hidden" name="id" >
        	<div>
				<label class="control-label" for="realName"><font color="red">*</font>姓名</label>
				<div class="controls">
					<input type="text" id="realName" name="realName"/>
				</div>
			</div>
        	<div>
				<label class="control-label" for="userName"><font color="red">*</font>帐号</label>
				<div class="controls">
					<input type="text" id="userName" name="userName"/>
				</div>
			</div>
        	<div>
				<label class="control-label" for="mobilePhone">移动电话</label>
				<div class="controls">
					<input type="text" id="mobilePhone" name="mobilePhone"/>
				</div>
			</div>
        	<div>
				<label class="control-label" for="userType"><font color="red">*</font>类型</label>
				<p>
					<select id="userType" name="userType" >
						<option>不同用户</option>
						<option>客户</option>
						<option>管理员</option>
					</select>
				</p>
			</div>
        	<div>
				<label class="control-label" for="delFlg"><font color="red">*</font>帐号状态</label>
				<p>
					<select id="delFlg" name="delFlg" >
						<option>在用</option>
						<option>禁用</option>
					</select>
				</p>
			</div>
        
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>
 -->
</body>
</html>
