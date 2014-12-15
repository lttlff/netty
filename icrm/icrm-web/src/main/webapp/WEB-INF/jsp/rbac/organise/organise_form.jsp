<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<%@ include file="/resources/jsp/jquery.form.jsp" %>
	
	<title>组织管理-编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="description" content="组织管理-编辑">
	<script type="text/javascript" src="<%=basePath%>resources/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=basePath%>rbac/organise/organise.form.js"></script>
</head>

<body>
<div class="container">
	<div class="prom_cont bdr">
		<div class="title">
	      <h2 id="orgTitle">组织信息-</h2>
	      <div class="fr tit_r">
	      		  <a class="btn btn-s03"  href="<%=basePath%>organise/main.do?parOrgId = ${organise.parOrgId}">返回</a>
			      <input type="submit" class="btn btn-s03" id="orgSubmit" value="保存" />
		     </div>
	    </div>
	    <form action='<c:url value="/organise/submitEdit.do"></c:url>'  id="orgForm" method="post">
			<ul class="list_4rd">
				<li><div class="txt">组织编码：</div>
					<input type="hidden" id="rowId" name="rowId" value="${organise.rowId}"  />
					<input type="hidden" id="parOrgId" name="parOrgId" value="${organise.parOrgId}"  />
					<input type="hidden" id="orgPath" name="orgPath" value="${organise.orgPath}"  />
					<input type="text" class="tt" id="orgCode" readonly="true" name="orgCode" value="${organise.orgCode}"/>
			    </li>
				<li><div class="txt"><font color="red">*</font>组织名称：</div>
					<input type="text" class="tt" id="orgName" name="orgName" value="${organise.orgName}" />
			    </li>
			    <li><div class="txt">组织类型:</div>
					<select class="st" id="orgType" name="orgType">
				          <c:forEach var="dic" items="${orgTypeDicList}" varStatus="status">
							<c:choose>
							    <c:when test="${organise.orgType == dic.value}">
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
			<ul class="list_3rd">
				<li><div class="txt">描述：</div>
							<input type="text" class="ta" id="orgDesc" name="orgDesc" value="${organise.orgDesc}" />
						</li>
			</ul>
	    </form>
	</div>
</div>
</body>
<script type="text/javascript" >
	if("add"=="${type}"){
		$("#orgTitle").html($("#orgTitle").html()+"新增");
		$("#orgForm").attr("action", basePath + "organise/addOrganise.do")
	}else{
		$("#orgTitle").html($("#orgTitle").html()+"修改");
		$("#orgForm").attr("action", basePath + "organise/modifyOrganise.do")
	}
</script>
</html>