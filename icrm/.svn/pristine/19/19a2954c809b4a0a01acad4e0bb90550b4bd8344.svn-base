<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.simple.jsp"%>
<meta http-equiv="pragma" content="cache">
<title>联系人分配</title>
</head>
<body>
<div id="tabs">
<ul><li><a href='#tab1'>未分配</a></li><li><a href='#tab2'>已分配</a></li></ul>
<div id="tab1"><iframe name="1" width="99%" height="550px;" src='<%=basePath%>contactsController/disAllot.do'></iframe></div>
<div id="tab2"><iframe name="2" width="99%" height="550px;" src='<%=basePath%>contactsController/alloted.do'></iframe></div>
</div>
</body>
<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
	});
</script>
</html>
