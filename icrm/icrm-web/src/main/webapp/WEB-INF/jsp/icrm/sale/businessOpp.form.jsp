<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
	<%@ include file="/resources/jsp/jquery.form.jsp" %>
	<title>商机详细信息</title>
	<script type="text/javascript" src="<%=basePath%>resources/js/My97DatePicker/WdatePicker.js" ></script>
</head>
<body>
<div class="container">
    <form action="<%=basePath%>businessOppController/submitEdit.do"  id="mainForm" method="post">
	<div class="prom_cont bdr">
	     <div class="title">
		     <h2 id="top_title">商机信息-</h2>
		     <div class="fr tit_r">
		     	  <a  class="btn btn-s03" id="tt_goback" href="<%=basePath%>businessOppController/index.do">返回</a>
			      <input type="button" class="btn btn-s03" id="doSubmit" value="保存" />
		     </div>
		</div>
			<ul class="list_4rd">
				<li><div class="txt"><font color="red">*</font>机会名称：</div>
					<input type="hidden" name="rowId" value="${businessOpp.rowId}"  />
					<input type="text" class="tt" name="businessOppName" value="${businessOpp.oppName}"/>
			    </li>
			 	<li><div class="txt"><font color="red">*</font>客户名称：</div>
					<input type="hidden" class="tt"  name="custId" value="${businessOpp.custId}" />
					<input type="text trigger" class="tt"  name="custName" value="${businessOpp.custName}" />
			    </li>
				<li><div class="txt"><font color="red">*</font>所处阶段：</div>
					<input type="text" class="tt"  name="periodId" value="${businessOpp.salePeriodName}" />
					<input type="hidden" class="tt"  name="periodId" value="${businessOpp.periodId}" />
			    </li>
			    <li><div class="txt">意向购买的产品：</div>
					<input type="text" class="tt" name="prodIntention" value="${businessOpp.prodIntention}" />
			    </li>
			    <li><div class="txt">项目金额：</div>
					<input type="text" class="tt"  name="amount" value="${businessOpp.amount}" />
			    </li>
			    <li><div class="txt">成功可能性：</div>
					<input type="text" class="tt"  name="possibility" value="${businessOpp.possibility}" />
			    </li>
			    <li><div class="txt">预计完成日期：</div>
			    	<input type="text" class="tt Wdate" onClick="WdatePicker()" name="planComplDate" value="${businessOpp.planComplDate}" />
			    </li>
			    <li>
			    	<div class="txt">机会负责人：</div>
			    	<input type="text" class="tt"  name="userId" value="${businessOpp.userId}" />
			    	<input type="hidden" class="tt"  name="userName" value="${businessOpp.userName}" />
			    </li>
			    <li>
			    	<div class="txt">竞争对手：</div>
			    	<input type="hidden" class="tt"  name="competitor" value="${businessOpp.competitor}" />
			    </li>
				<li><div class="txt">机会来源：</div>
					<select class="st"  name="source" value="${businessOpp.source}">
						<c:forEach var="dic" items="${sourceDicList }" >
					          <option value="${dic.value }"   <c:if test="${dic.value ==contacts.sts}">selected="selected"</c:if>  >${dic.text }</option>
						</c:forEach>
			        </select>
			    </li>
			    <li><div class="txt">丢失原因：</div>
					<input type="text" class="tt" name="lostReason" value="${businessOpp.lostReason }" />
			    </li>
			    <li><div class="txt">创建时间：</div>
			    	<input type="text" class="tt Wdate" onClick="WdatePicker()" name="createDate" value="${businessOpp.createDate}" />
			    </li>
			    <li><div class="txt">状态：</div>
					<select class="st"  name="sts" value="${contacts.sts}">
						<c:forEach var="dic" items="${stsDicList }" >
					        <option value="${dic.value }" <c:if test="${dic.value ==contacts.sts}">selected="selected"</c:if>  >${dic.text }</option>
						</c:forEach> 	
			        </select>
			    </li>
			    <li><div class="txt">状态时间：</div>
			    </li>
			</ul>
			</div>
			<div class="prom_cont bdr">
			<div class="title">
		      <div class="fr">
		      	<p><font>沟通记录</font></p>
		      </div>
		    </div>
				<table class="table table-bordered table-border-hd table-striped"  id="mainTable">
					<thead>
						<tr>
							<th width="0px;" style="display: none;"></th>
							<th width="3%"></th>
							<th width="4%">序号</th>
							<th width="6%">联系人</th>
							<th width="20%">联系方式</th>
							<th width="10%">联系时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="domain" items="${businessOpp.commRecordList}" varStatus="status" >
							<tr>
								<td style="display: none;">${domain.rowId}</td>
								<td><input type="checkbox" /></td>
								<td>${status.index+1}</td>
								<td>${domain.contactsName}</td>
								<td>${domain.contactWay_text}</td>
								<td>${domain.contactTimeStr}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
</html>
