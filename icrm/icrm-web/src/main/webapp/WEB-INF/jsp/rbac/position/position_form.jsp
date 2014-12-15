<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<%@ include file="/resources/jsp/jquery.form.jsp" %>
	
	<title>岗位管理-编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="description" content="岗位管理-编辑">
	<script type="text/javascript" src="<%=basePath%>resources/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=basePath%>rbac/position/position.form.js"></script>
</head>

<body>
<div class="container">
	<div class="prom_cont bdr">
		<div class="title">
	      <h2 id="posTitle">岗位信息-</h2>
	      <div class="fr tit_r">
	      		  <a class="btn btn-s03"  href="<%=basePath%>position/main.do?parPosId=${position.parPosId}">返回</a>
			      <input type="submit" class="btn btn-s03" id="posSubmit" value="保存" />
		     </div>
	    </div>
	    <form action='<c:url value="/position/submitEdit.do"></c:url>'  id="posForm" method="post">
			<ul class="list_4rd">
				<li><div class="txt">所属组织：</div>
					<input type="hidden" id="rowId" name="rowId" value="${position.rowId}"  />
					<input type="hidden" id="parPosId" name="parPosId" value="${position.parPosId}"  />
					<input type='text' class='tt trigger' id="orgName" readOnly="true" name='orgName' value="${position.orgName}"/>
					<input type="hidden" id="orgId" name="orgId" value="${position.orgId}"   />
			    </li>
				<li><div class="txt"><font color="red">*</font>岗位名称：</div>
					<input type="text" class="tt" id="posName" name="posName" value="${position.posName}" />
			    </li>
			    <li><div class="txt">岗位类型:</div>
					<select class="st" id="posType" name="posType">
				          <c:forEach var="dic" items="${posTypeDicList}" varStatus="status">
							<c:choose>
							    <c:when test="${position.posType == dic.value}">
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
							<input type="text" class="ta" id="posDesc" name="posDesc" value="${position.posDesc}" />
						</li>
			</ul>
	    </form>
	</div>
	<table width="95%" align="center" border="0" cellspacing="1" cellpadding="1" id="posRolesTable"> 
	   <tr>
	        <td>可选角色 </td>
	        <td>&nbsp;</td>
			<td>已选角色</td>
       </tr>
	   <tr>
        <td> 
        	<select name="possible" size="10" MULTIPLE width=300 style="width:300px">
		      <c:forEach var="role" items="${unRoleList}" varStatus="status">
				  <option value='${role.rowId}'>
				      ${role.roleName}
				  </option>
			  </c:forEach>
		    </select>
		</td>
        <td>
		        <input type="button" name="to" value=">>>"
		          onClick="copySelect('possible','roles')" class="button2"/>
		      <br/><br/>
		      <input type="button" name="from" value="<<<"
		          onClick="copySelect('roles','possible')" class="button2"/>
       	</td>
        <td>
        	<select id="roles" name="roles" size="10" MULTIPLE width=300 style="width:300px"> 
        		<c:forEach var="role" items="${roleList}" varStatus="status">
					  <option value='${role.rowId}'>
					      ${role.roleName}
					  </option>
				  </c:forEach>
        	</select>
        </td>
      </tr>
    </table>
</div>
<div id="deptDiv">
	<iframe src="" width="99%"  height="435px" style="display: none;" id="dept_iframe" name="dept_iframe"></iframe>
</div>
</body>
<script type="text/javascript" >
	if("add"=="${type}"){
		$("#posTitle").html($("#posTitle").html()+"新增");
		$("#posForm").attr("action", basePath + "position/addPosition.do")
	}else{
		$("#posTitle").html($("#posTitle").html()+"修改");
		$("#posForm").attr("action", basePath + "position/modifyPosition.do")
	}
</script>
</html>