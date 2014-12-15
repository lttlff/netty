<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<%@ include file="/resources/jsp/jquery.form.jsp" %>
	
	<title>客户管理-编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="description" content="用户管理-编辑">
	<script type="text/javascript" src="<%=basePath%>resources/js/My97DatePicker/WdatePicker.js" ></script>
	<script type="text/javascript" src="<%=basePath%>icrm/customer/customer.form.js"></script>
</head>

<body>
<div class="container">
    <form action='<c:url value="/customerController/submitEdit.do"></c:url>'  id="customerForm" method="post">
	<div class="prom_cont bdr">
	     <div class="title">
		     <h2 id="customerTitle">客户信息-</h2>
		     <div class="fr tit_r">
		     	  <a  class="btn btn-s03" id="tt_goback" href="<%=basePath%>customerController/index.do">返回</a>
			      <input type="button" class="btn btn-s03" id="customerSubmit" value="保存" />
		     </div>
		</div>
			<ul class="list_4rd">
				<li><div class="txt"><font color="red">*</font>客户编码：</div>
					<input type="hidden" id="rowId" name="rowId" value="${customer.rowId}"  />
					<input type="text" class="tt" id="custCode" name="custCode" value="${customer.custCode}"/>
			    </li>
				<li><div class="txt"><font color="red">*</font>客户名称：</div>
					<input type="text" class="tt" id="custName" name="custName" value="${customer.custName}" />
			    </li>
				<li><div class="txt"><font color="red">*</font>客户简拼：</div>
					<input type="text" class="tt" id="custSimple" name="custSimple" value="${customer.custSimple}" />
			    </li>
			    <li>
			    	<div class="txt">客户类型：</div>
					<select class="st" id="custType" name="custType" value="${customer.custType}">
						<c:forEach var="dic" items="${custTypeDicList }" >
					          <option value="${dic.value }"  <c:if test="${dic.value ==customer.custType}">selected="selected"</c:if> >${dic.text }</option>
						</c:forEach>
			        </select>
			    </li>
			    <li>
			    	<div class="txt">证件类型：</div>
					<select class="st" id="regType" name="regType" value="${customer.regType}">
						<c:forEach var="dic" items="${regTypeDicList }" >
					          <option value="${dic.value }" <c:if test="${dic.value ==customer.regType}">selected="selected"</c:if> >${dic.text }</option>
						</c:forEach>
			        </select>
			    </li>
				<li><div class="txt"><font color="red">*</font>证件号码：</div>
					<input type="text" class="tt" id="regNbr" name="regNbr" value="${customer.regNbr}" />
			    </li>
			    <li>
			    	<div class="txt">客户分类：</div>
					<select class="st" id="custCat" name="custCat" value="${customer.custCat}">
						<c:forEach var="dic" items="${custCatDicList }" >
					          <option value="${dic.value }"  <c:if test="${dic.value ==customer.custCat}">selected="selected"</c:if>  >${dic.text }</option>
						</c:forEach>
			        </select>
			    </li>
				<li><div class="txt"><font color="red">*</font>销售区域：</div>
					<input type="text" class="tt" id="saleAreaId" name="saleAreaId" value="${customer.saleAreaId}" />
			    </li>
			    <li>
			        <div class="txt">&nbsp;</div>
			        <input class="ck" type="checkbox" checked="checked"  id="blackFlag" name="blackFlag">黑名单标识
			    </li>
			    <li>
			    	<div class="txt">客户标志：</div>
					<select class="st" id="custFlag" name="custFlag" value="${customer.custFlag}">
						<c:forEach var="dic" items="${custFlagDicList }" >
					          <option value="${dic.value }">${dic.text }</option>
						</c:forEach>
			        </select>
			    </li>
			    <li>
			    	<div class="txt">客户状态：</div>
					<select class="st" id="sts" name="sts" value="${customer.sts}">
						<c:forEach var="dic" items="${stsDicList }" >
					          <option value="${dic.value }"   <c:if test="${dic.value ==customer.sts}">selected="selected"</c:if>  >${dic.text }</option>
						</c:forEach>
			        </select>
			    </li>
				<li><div class="txt">状态变更时间：</div>
					<input type="text" class="tt Wdate" onClick="WdatePicker()" id="stsDate" name="stsDate" value="${customer.stsDateStr}" />
			    </li>
			    <li>
			    	<div class="txt">分配状态：</div>
					<select class="st" id="assignSts" name="assignSts" value="${customer.assignSts}">
						<c:forEach var="dic" items="${assignStsDicList }" >
					          <option value="${dic.value }"   <c:if test="${dic.value ==customer.assignSts}">selected="selected"</c:if> >${dic.text }</option>
						</c:forEach>
			        </select>
			    </li>
				<li><div class="txt">分配时间：</div>
					<input type="text" class="tt Wdate" onClick="WdatePicker()" id="assignDate" name="assignDate" value="${customer.assignDateStr}" />
			    </li>
				<li><div class="txt">客户地址：</div>
					<input type="text" class="tt" id="custAddrId" name="custAddrId" value="${customer.custAddrId}" />
			    </li>
			</ul>
			</div>
			<div class="prom_cont bdr">
			<ul class="list_4rd"  id="custSub">
				<p><font>客户附属证件信息</font>
					<a href="javascript:void(0);" class="arrow_up" id="custSubArrow">&nbsp;</a>
					<a class="add_more" href="javascript:void(0);" title="添加" >&nbsp;</a>
				</p>
				<c:forEach var="custSub" items="${custSubInfoList}" varStatus="status" >
					<ul>
					    <li>
					    	<div class="txt"><font color="red">*</font>证件类型：</div>
							<select class="st" name="custSubInfo[${status.index}].regType" value="${custSub.regType}">
								<c:forEach var="dic" items="${regTypeDicList }" >
							          <option value="${dic.value }"  <c:if test="${dic.value ==custSub.regType}">selected="selected"</c:if> >${dic.text }</option>
								</c:forEach>
					        </select>
					    </li>
						<li><div class="txt"><font color="red">*</font>证件号码：</div>
							<input type="text" class="tt"  name="custSubInfo[${status.index}].regNbr" value="${custSub.regNbr}" />
					    </li>
					    <li>
					    	<div class="operate">
						    	<a class="delete" href="javascript:void(0);" data="${custSub.rowId}" title="删除"  >&nbsp;</a>
					    	</div>
					    </li>
					    <li>
					    	&nbsp;
					    	<input type="hidden" value="${custSub.rowId}" name="custSubInfo[${status.index}].rowId"/> 
					    	<input type="hidden" value="${custSub.custId}" name="custSubInfo[${status.index}].custId"/> 
					    </li>
				    </ul>
				</c:forEach>
			</ul>
			</div>
			<div class="prom_cont bdr">
			<ul class="list_4rd" id="contacts">
				<p>联系人信息
					<a href="javascript:void(0);" class="arrow_up" id="contactsArrow">&nbsp;</a>
					<a class="add_more" href="javascript:void(0);" title="添加" >&nbsp;</a>
				</p>
				<c:forEach var="custContactsRela" items="${custContactsList}" varStatus="status" >
					<ul>
						<li><div class="txt"><font color="red">*</font>联系人类型：</div>
							<select class="st"  name="custContactsRela[${status.index}].contactsType" value="${custContactsRela.contactsType}">
								<c:forEach var="dic" items="${contactsTypeDicList }" >
							          <option value="${dic.value }"  <c:if test="${dic.value ==custContactsRela.contactsType}">selected="selected"</c:if> >${dic.text }</option>
								</c:forEach>
					        </select>
					    </li>
						<li><div class="txt"><font color="red">*</font>联系人姓名：</div>
							<input type="text" class="tt trigger" name="custContactsRela[${status.index}].contactsName" value="${custContactsRela.contactsName}" />
					    </li>
					    <li>
					    	<div class="operate">
						    	<a class="detail" href="javascript:void(0);" data="${custContactsRela.contactsId}" title="查看联系人详细">&nbsp;</a>
						    	<a class="delete" href="javascript:void(0);" data="${custContactsRela.rowId}" title="删除">&nbsp;</a>
					    	</div>
					    </li>
					    <li>
					    	<input type="hidden" name="custContactsRela[${status.index}].rowId" value="${custContactsRela.rowId}">
					    	<input type="hidden" name="custContactsRela[${status.index}].custId" value="${custContactsRela.custId}">
					    	<input type="hidden" name="custContactsRela[${status.index}].contactsId" value="${custContactsRela.contactsId}">
					    </li>
				    </ul>
				 </c:forEach>   
			</ul>
			</div>
 </form>
</div>
<div id="contactsDiv">
	<iframe src="" width="99%"  height="435px" style="display: none;" id="contacts_iframe" name="contacts_iframe"></iframe>
</div>
</body>
<script type="text/javascript" >
	if("a"=="${type}"){
		$("#customerTitle").html($("#customerTitle").html()+"新增");
	}else{
		$("#customerTitle").html($("#customerTitle").html()+"修改");
	}
</script>
</html>
