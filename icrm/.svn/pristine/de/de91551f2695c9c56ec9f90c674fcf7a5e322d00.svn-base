<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<%@ include file="/resources/jsp/jquery.form.jsp" %>
	
	<title>权限管理-编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="description" content="权限管理-编辑">
	<script type="text/javascript" src="<%=basePath%>resources/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=basePath%>rbac/permission/permission.form.js"></script>
</head>

<body>
<div class="container">
	<div class="prom_cont bdr">
		<div class="title">
	      <h2 id="perTitle">权限信息-</h2>
	      <div class="fr tit_r">
	      		  <a class="btn btn-s03"  href="<%=basePath%>permission/index.do">返回</a>
			      <input type="submit" class="btn btn-s03" id="perSubmit" value="保存" />
		     </div>
	    </div>
	    <form action='<c:url value="/permission/submitEdit.do"></c:url>'  id="perForm" method="post">
			<ul class="list_4rd">
				<li><div class="txt">权限编码：</div>
					<input type="hidden" id="rowId" name="rowId" value="${permission.rowId}"  />
					<input type="text" class="tt" id="perCode" readonly="true" name="perCode" value="${permission.perCode}"/>
			    </li>
				<li><div class="txt"><font color="red">*</font>权限名称：</div>
					<input type="text" class="tt" id="perName" name="perName" value="${permission.perName}" />
			    </li>
			    <li><div class="txt">权限类型:</div>
					<select class="st" id="perType" name="perType">
				          <c:forEach var="dic" items="${perTypeDicList}" varStatus="status">
							<c:choose>
							    <c:when test="${permission.perType == dic.value}">
							    	<option value="${dic.value}" selected="selected">${dic.text}</option>
							    </c:when>
							    <c:otherwise>
									<option value="${dic.value}" >${dic.text}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
			        </select>
				</li>
			</ul>
			<ul class="list_4rd">
				<li><div class="txt">菜单：</div>
					<input type='text' class='tt trigger' id="menuName" name='menuName' value="${permission.menuName}"/>
					<input type="hidden" id="menuId" name="menuId" value="${permission.menuId}"   />
			    </li>
				<li><div class="txt">描述：</div>
							<input type="text" class="tt" id="perDesc" name="perDesc"
								value="${permission.perDesc}"  />
						</li>
			</ul>
	    </form>
	</div>
</div>
<div id="menuDiv">
	<iframe src="" width="99%"  height="435px" style="display: none;" id="menu_iframe" name="menu_iframe"></iframe>
</div>
</body>
<script type="text/javascript" >
	if("add"=="${type}"){
		$("#perTitle").html($("#perTitle").html()+"新增");
		$("#perForm").attr("action", basePath + "permission/addPermission.do")
	}else{
		$("#perTitle").html($("#perTitle").html()+"修改");
		$("#perForm").attr("action", basePath + "permission/updatePermission.do")
	}
</script>
</html>
