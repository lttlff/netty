<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/css/style_yl.css" />

<script type="text/javascript">

	var basePath = "<%=basePath%>";
</script>

<title>My JSP 'dynamicForm.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript"
	src="<%=basePath%>resources/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/js/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/js/jquery/plugins/jquery.form.js"></script>
<script type="text/javascript"
	src="<%=basePath%>icrm/dynamic/display/js/main.js"></script>


</head>

<body>
	<fieldset>
		<legend>基本信息</legend>
		<form id="dynamicForm" action="<%=basePath%>${actionUrl}"
			method="post">
			<table border="0" cellpadding="0" cellspacing="1" class="ui-table"
				width="100%">
				${formInnerHtml}
			</table>
		</form>
		<button id="dynamicFormSubmit">提交</button>
		<button id="testBtn">Test Button</button>
	</fieldset>
</body>
</html>
