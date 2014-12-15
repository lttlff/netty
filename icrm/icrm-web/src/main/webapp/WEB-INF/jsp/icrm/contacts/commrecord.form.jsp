<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.form.jsp" %>
<title>联系信息编辑</title>
<script type="text/javascript" src="<%=basePath%>resources/js/My97DatePicker/WdatePicker.js" ></script>
</head>
<body>
	<div class="container">
		<form action="<%=basePath%>commRecordController/submitEdit.do"  id="mainForm" method="post">
			<div class="prom_cont bdr">
				<div class="title">
				     <h2 id="top_title">联系信息-</h2>
				     <div class="fr tit_r">
    		     	 	<a class="btn btn-s03" id="tt_goback" href="<%=basePath%>commRecordController/index.do">返回</a>
			     		<input type="button" class="btn btn-s03" id="doSubmit" value="保存" />
				     </div>
				     <input type="hidden" name="rowId" value="${domain.rowId}"  />
				 </div>
				     <ul class="list_4rd">
				     	<li><div class="txt"><font color="red">*</font>联系人名称：</div>
							<input type="hidden" name="contactsId" value="${domain.contactsId}"  />
							<input type="text" class="tt trigger"  readonly="readonly" name="contactsName" value="${domain.contactsName}"/>
					    </li>
						<li><div class="txt">销售人员：</div>
							<input type="text" class="tt" name="userId" readonly="readonly" value="${domain.userId}"/>
					    </li>
					    <li>
					    	<div class="txt">联系方式：</div>
							<select class="st"  name="contactWay">
								<c:forEach var="dic" items="${contactWayDicList }" >
							          <option value="${dic.value }"   <c:if test="${dic.value ==domain.contactWay}">selected="selected"</c:if>  >${dic.text }</option>
								</c:forEach>
					        </select>
					    </li>
						<li><div class="txt">联系时间：</div>
							<input type="text" class="tt Wdate" onClick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd H:mm:ss'})" name="contactTime" value="${domain.contactTimeStr}" />
					    </li>
				     </ul>
				     <ul class="list_2rd">
					      <li>
					        <div class="txt">联系内容：</div>
					        <textarea class="ta" rows="3"   style="resize:none;" name="contactComment" >${domain.contactComment}</textarea>
					      </li>
					  </ul>
			</div>
		</form>
	</div>
<div id="windiv">
	<iframe src="" width="99%"  height="435px" style="display: none;" id="win_iframe" name="win_iframe"></iframe>
</div>
</body>
<script type="text/javascript" >
	var g_type = "${type}";
	var $contactName  = $("#mainForm input[name='contactsName']");
	if("a"==g_type){
		$("#top_title").html($("#top_title").html()+"新增");
		$contactName.removeAttr('readonly');
		$contactName.addClass('trigger');
	}else{
		$("#top_title").html($("#top_title").html()+"修改");
		$contactName.attr('readonly','readonly');
		$contactName.removeClass('trigger');
	}
	
	$contactName.live('focus',function(){
		var $now_input = $(this);
		if($now_input.attr('readonly')=="readonly"){
			return false;
		}
		var $winIframe = $("#win_iframe");
		$winIframe.attr("src",basePath+"contactsController/listWin.do");
		$winIframe.css('display', 'block');
		$("#windiv").dialog({
			autoOpen : true,
			title : '联系人选择',
			width : 980,
			height : 500,
			show : {
				effect : "blind",
				duration : 200
			},
			close : function(event, ui) {
				//关闭dialog事件
			},
			buttons: {
				'选择':function(){
					var $table = $(window.frames["win_iframe"].document).find("#mainTable");
					var $tr = $table.find("td input:checked").parents("tr:first");
					if($tr.length==0){
						alert("请先选择一条联系人记录!");
						return;
					}else{
						var id = $tr.find('td:first').html();
						var contactsName = $tr.find('td:eq(3)').html();
						$now_input.siblings("input:hidden[name$=contactsId]").val(id);
						$now_input.val(contactsName);
						$(this).dialog("close");
					}
				} ,
			    "关闭": function() {
			    	$(this).dialog("close");
			    }
			}
		});
	});
	
	
</script>
<script type="text/javascript" src="<%=basePath%>icrm/contacts/commrecord.form.js"></script>
</html>
