<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<%@ include file="/resources/jsp/jquery.form.jsp" %>
	
	<title>联系人信息编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="description" content="联系人信息编辑">
	<script type="text/javascript" src="<%=basePath%>resources/js/My97DatePicker/WdatePicker.js" ></script>
</head>

<body>
<div class="container">
    <form action="<%=basePath%>contactsController/submitEdit.do"  id="mainForm" method="post">
	<div class="prom_cont bdr">
	     <div class="title">
		     <h2 id="top_title">联系人信息-</h2>
		     <div class="fr tit_r">
		     	  <a  class="btn btn-s03" id="tt_goback" href="<%=basePath%>contactsController/index.do">返回</a>
			      <input type="button" class="btn btn-s03" id="doSubmit" value="保存" />
		     </div>
		</div>
			<ul class="list_4rd">
				<li><div class="txt"><font color="red">*</font>联系人名称：</div>
					<input type="hidden" name="rowId" value="${contacts.rowId}"  />
					<input type="text" class="tt" name="contactsName" value="${contacts.contactsName}"/>
			    </li>
			 	<li><div class="txt"><font color="red">*</font>部门：</div>
					<input type="text" class="tt"  name="department" value="${contacts.department}" />
			    </li>
				<li><div class="txt"><font color="red">*</font>职务：</div>
					<input type="text" class="tt"  name="position" value="${contacts.position}" />
			    </li>
			    <li><div class="txt">生日：</div>
					<input type="text" class="tt Wdate" onClick="WdatePicker()" name="birthday" value="${contacts.birthdayStr}" />
			    </li>
			    <li><div class="txt">兴趣爱好：</div>
					<input type="text" class="tt"  name="hobby" value="${contacts.hobby}" />
			    </li>
			    <li><div class="txt">工作内容：</div>
					<input type="text" class="tt"  name="workContent" value="${contacts.workContent}" />
			    </li>
			    <li><div class="txt">地址：</div>
					<input type="text" class="tt trigger" name="addrFull" value="${contacts.addrFull}" />
					<input type="hidden" class="tt"  name="contactsAddrId" value="${contacts.contactsAddrId}" />
			    </li>
			    <li>
			    	<div class="txt">状态：</div>
					<select class="st"  name="sts" value="${contacts.sts}">
						<c:forEach var="dic" items="${stsDicList }" >
					          <option value="${dic.value }"   <c:if test="${dic.value ==contacts.sts}">selected="selected"</c:if>  >${dic.text }</option>
						</c:forEach>
			        </select>
			    </li>
			    <li>
			    	<div class="txt">分配状态：</div>
					<select class="st"  name="assignSts" value="${contacts.assignSts}">
						<c:forEach var="dic" items="${assignStsDicList }" >
					          <option value="${dic.value }"   <c:if test="${dic.value ==contacts.assignSts}">selected="selected"</c:if> >${dic.text }</option>
						</c:forEach>
			        </select>
			    </li>
				<li><div class="txt">分配时间：</div>
					<input type="text" class="tt Wdate" onClick="WdatePicker()" name="assignDate" value="${contacts.assignDateStr}" />
			    </li>
			    <li><div class="txt">分组名称：</div>
					<input type="text" class="tt trigger" name="contactsGroup.groupName" value="${contacts.contactsGroup.groupName }" />
					<input type="hidden" name="contactsGroup.rowId"  value="${contacts.contactsGroup.rowId }" />
			    </li>
			    <li><div class="txt">销售人员：</div>
					<input type="text" class="tt trigger"  value="${contacts.user.realName }" />
					<input type="hidden" name="user.rowId"  value="${contacts.user.rowId }" />
			    </li>
			</ul>
			</div>
			<div class="prom_cont bdr">
				<ul class="list_4rd"  id="customerList">
					<p><font>关联客户信息</font>
					</p>
					<c:forEach var="item" items="${contacts.customerList}" varStatus="status" >
						<ul>
						    <li>
						    	<div class="txt"><font color="red">*</font>客户名称：</div>
								<input type="text" class="tt"  value="${item.custName }" />
						    </li>
						    <li>
						    	<div class="operate">
						    	<%-- 
									<a class="delete" href="javascript:void(0);" data="${item.rowId}" title="删除"  >&nbsp;</a>
									 --%>
								</div>
						    </li>
						    <li>
						    <%-- 
						    	<input type="hidden" value="${item.rowId}" name="customerList[${status.index}].rowId"/> 
						    	<input type="hidden" value="${item.contactsId}" name="customerList[${status.index}].rowId"/>
						    	 --%>
						    </li>
					    </ul>
					</c:forEach>
				</ul>
			</div>
			<div class="prom_cont bdr">
				<ul class="list_4rd"  id="contactWayList">
					<p><font>联系方式</font>
						<a href="javascript:void(0);" class="arrow_up" >&nbsp;</a>
						<a class="add_more" href="javascript:void(0);" title="添加" >&nbsp;</a>
					</p>
					<c:forEach var="item" items="${contacts.contactWayList}" varStatus="status" >
						<ul>
						    <li>
						    	<div class="txt"><font color="red">*</font>联系方式：</div>
								<select class="st" name="contactWayList[${status.index}].contactType" value="${item.contactType}">
									<c:forEach var="dic" items="${contactTypeDicList }" >
								          <option value="${dic.value }"  <c:if test="${dic.value ==item.contactType}">selected="selected"</c:if> >${dic.text }</option>
									</c:forEach>
						        </select>
						    </li>
							<li><div class="txt"><font color="red">*</font>联系信息：</div>
								<input type="text" class="tt"  name="contactWayList[${status.index}].contactInfo" value="${item.contactInfo}" />
						    </li>
						    <li>
						    	<div class="operate">
							    	<a class="delete" href="javascript:void(0);" data="${item.rowId}" title="删除"  >&nbsp;</a>
						    	</div>
						    </li>
						    <li>
						    	&nbsp;
						    	<input type="hidden" value="${item.rowId}" name="contactWayList[${status.index}].rowId"/> 
						    	<input type="hidden" value="${item.contactsId}" name="contactWayList[${status.index}].contactsId"/> 
						    </li>
					    </ul>
					</c:forEach>
				</ul>
			</div>
 </form>
</div>
<div id="windiv">
	<iframe src="" width="99%"  height="435px" style="display: none;" id="win_iframe" name="win_iframe"></iframe>
</div>
</body>
<script type="text/javascript" >
	if("a"=="${type}"){
		$("#top_title").html($("#top_title").html()+"新增");
	}else{
		$("#top_title").html($("#top_title").html()+"修改");
	}
	//联系方式options
	var contactTypeOptions = "";
	<c:forEach var="dic" items="${contactTypeDicList }" >
		contactTypeOptions = contactTypeOptions+'<option value="${dic.value }">${dic.text }</option>';
	</c:forEach>
</script>
<script type="text/javascript" src="<%=basePath%>icrm/contacts/contacts.form.js"></script>
</html>
