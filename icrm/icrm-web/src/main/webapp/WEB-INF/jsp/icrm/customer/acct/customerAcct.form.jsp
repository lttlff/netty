<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.form.jsp"%>
<title>账户信息编辑</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=basePath%>icrm/customer/acct/customerAcct.form.js"></script>
</head>
<body>
	<div class="container">
		<form action="<%=basePath%>customerAcctController/submitEdit.do"
			id="mainForm" method="post">
			<div class="prom_cont bdr">
				<div class="title">
					<h2 id="top_title">账户信息-</h2>
					<div class="fr tit_r">
						<a class="btn btn-s03" id="tt_goback"href="<%=basePath%>customerAcctController/index.do">返回</a>
						<input type="button" class="btn btn-s03" id="doSubmit" value="保存" />
					</div>
				</div>
				<input type="hidden" name="rowId" value="${domain.rowId}"/>
				<ul class="list_4rd">
					<li><div class="txt"><font color="red">*</font>账户编号：</div>
						<input type="text" class="tt" readonly="readonly" value="${domain.acctNbr}"  />
				    </li>
					<li><div class="txt"><font color="red">*</font>账户名称：</div>
						<input type="text" class="tt" name="acctName" value="${domain.acctName}"  />
				    </li>
					<li><div class="txt"><font color="red">*</font>开户银行：</div>
						<input type="text" class="tt" name="branchName" value="${domain.branchName}"  />
				    </li>
					<li><div class="txt"><font color="red">*</font>银行账号：</div>
						<input type="text" class="tt" name="bankAcct" value="${domain.bankAcct}"  />
				    </li>
					<li><div class="txt"><font color="red">*</font>账户地址：</div>
						<input type="text" class="tt trigger"  value="${domain.acctAddrFull}"  />
						<input type="hidden" name="acctAddrId" value="${domain.acctAddrId }"/>
				    </li>
					<li><div class="txt"><font color="red">*</font>账户邮件地址：</div>
						<input type="text" class="tt" name="acctMail" value="${domain.acctMail}"  />
				    </li>
					<li><div class="txt"><font color="red">*</font>客户名称：</div>
						<input type="text" class="tt trigger" name="custName" value="${domain.custName}"  />
						<input type="hidden" name="custId" value="${domain.custId }"/>
				    </li>
				</ul>
			</div>
		</form>
	</div>
<div id="windiv">
	<iframe src="" width="99%"  height="435px" style="display: none;" id="win_iframe" name="win_iframe"></iframe>
</div>
</body>
<script type="text/javascript">

</script>
</html>
