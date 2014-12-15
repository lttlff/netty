<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<%@ include file="/resources/jsp/jquery.form.jsp" %>
	
	<title>角色管理-编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="description" content="角色管理-编辑">
	<script type="text/javascript" src="<%=basePath%>resources/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=basePath%>rbac/role/role.form.js"></script>
</head>

<body>
<div class="container">
	<div class="prom_cont bdr">
		<div class="title">
	      <h2 id="roleTitle">角色信息-</h2>
	      <div class="fr tit_r">
	      		  <a class="btn btn-s03"  href="<%=basePath%>role/index.do">返回</a>
			      <input type="submit" class="btn btn-s03" id="roleSubmit" value="保存" />
		     </div>
	    </div>
	    <form action='<c:url value="/role/submitEdit.do"></c:url>'  id="roleForm" method="post">
			<ul class="list_3rd">
				<li><div class="txt">角色编码：</div>
					<input type="hidden" id="rowId" name="rowId" value="${role.rowId}"  />
					<input type="text" class="tt" id="roleCode" readonly="true" name="roleCode" value="${role.roleCode}"/>
			    </li>
				<li><div class="txt"><font color="red">*</font>角色名称：</div>
					<input type="text" class="tt" id="roleName" name="roleName" value="${role.roleName}" />
			    </li>
			    <li><div class="txt">角色类型:</div>
					<select class="st" id="roleType" name="roleType">
				          <c:forEach var="dic" items="${roleTypeDicList}" varStatus="status">
							<c:choose>
							    <c:when test="${role.roleType == dic.value}">
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
			<ul class="list_2rd">
				<li><div class="txt">描述：</div>
							<input type="text" class="tt" id="roleDesc" name="roleDesc"
								value="${role.roleDesc}" size="100" style="width:300px; height:50px;" />
						</li>
			</ul>
	    </form>
	</div>
	<table width="95%" align="center" border="0" cellspacing="1" cellpadding="1" id="rolePermsTable"> 
	   <tr>
	        <td>可选权限 </td>
	        <td>&nbsp;</td>
			<td>已选权限</td>
       </tr>
	   <tr>
        <td> 
        	<select name="possible" size="10" MULTIPLE width=300 style="width:300px">
		      <c:forEach var="permission" items="${unPermList}" varStatus="status">
				  <option value='${permission.rowId}'>
				      ${permission.perName}
				  </option>
			  </c:forEach>
		    </select>
		</td>
        <td>
		        <input type="button" name="to" value=">>>"
		          onClick="copySelect('possible','perms')" class="button2"/>
		      <br/><br/>
		      <input type="button" name="from" value="<<<"
		          onClick="copySelect('perms','possible')" class="button2"/>
       	</td>
        <td>
        	<select id="perms" name="perms" size="10" MULTIPLE width=300 style="width:300px"> 
        		<c:forEach var="perm" items="${permList}" varStatus="status">
					  <option value='${perm.rowId}'>
					      ${perm.perName}
					  </option>
				  </c:forEach>
        	</select>
        </td>
      </tr>
    </table>
</div>
</body>
<script type="text/javascript" >
	if("add"=="${type}"){
		$("#roleTitle").html($("#roleTitle").html()+"新增");
		$("#roleForm").attr("action", basePath + "role/insertRole.do")
	}else{
		$("#roleTitle").html($("#roleTitle").html()+"修改");
		$("#roleForm").attr("action", basePath + "role/updateRole.do")
	}
</script>
</html>
