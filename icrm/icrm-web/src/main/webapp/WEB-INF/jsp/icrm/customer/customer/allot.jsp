<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp"%>
<title>客户分配</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<body>
	<div id="tabs">
		<ul>
			<li><a href='#tab1'>未分配</a></li>
			<li><a href='#tab2'>已分配</a></li>
		</ul>
		<div id="tab1"><iframe name="1" width="99%" height="450px;" src='<c:url value="/customerAllotController/allot_dis.do"></c:url>'></iframe></div>
		<div id="tab2"><iframe name="2" width="99%" height="450px;" src='<c:url value="/customerAllotController/alloted.do"></c:url>'></iframe></div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
	});
</script>
</html>
