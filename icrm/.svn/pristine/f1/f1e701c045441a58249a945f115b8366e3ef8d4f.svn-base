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

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<script type="text/javascript">
	var basePath = "<%=basePath%>";
</script>

<title>动态表单配置</title>

<!-- Le styles -->
<link href="<%=basePath%>icrm/dynamic/manager/layoutit/css/bootstrap-combined.min.css" rel="stylesheet">
<link href="<%=basePath%>icrm/dynamic/manager/layoutit/css/layoutit.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->

	<!-- Fav and touch icons -->
	<link rel="shortcut icon" href="<%=basePath%>icrm/dynamic/manager/layoutit/img/favicon.png">
	
 	<!--[if lt IE 9]>
	<script src="<%=basePath%>icrm/dynamic/manager/layoutit/js/html5shiv.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	<![endif]-->
	
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/jquery-2.0.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/jquery.ui.touch-punch.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/jquery.htmlClean.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/ckeditor/config.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/scripts.js"></script>
</head>

<body style="min-height: 660px; cursor: auto;" class="edit">

<div class="container-fluid">
	<form name="test_form" class="form-horizontal" role="form">
		<div class="span6 column">
			<div class="control-group">
				 <label class="control-label" for="inputEmail">邮箱</label>
				<div class="controls">
					<input id="inputEmail" type="text" />
				</div>
			</div>
			<div class="control-group">
				 <label class="control-label" for="offer_nbr">销售品编码</label>
				<div class="controls">
					<input id="offer_nbr" name="offer_nbr" type="text" />
				</div>
			</div>
		</div>
		<div class="span6 column">
			<div class="control-group">
				 <label class="control-label" for="prod_offer_name">销售品名称</label>
				<div class="controls">
					<input id="prod_offer_name" name="prod_offer_name" type="text" />
				</div>
			</div>
			<div class="control-group">
				 <label class="control-label" for="password">密码</label>
				<div class="controls">
					<input id="password" class="form-control" type="text" />
				</div>
			</div>
		</div>
	</form>
</div>


</body>
</html>
