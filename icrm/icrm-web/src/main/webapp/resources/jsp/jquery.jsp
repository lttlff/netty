<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
</script>
<script type="text/javascript" src="<%=basePath%>resources/js/jquery/jquery-1.8.0.min.js"></script>
<link href="<%=basePath%>resources/css/icon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>resources/js/jquery/jquery-ui-1.9.2/js/jquery-ui-1.9.2.js"></script>
<link href="<%=basePath%>resources/js/jquery/jquery-ui-1.9.2/css/start/jquery-ui-1.10.4.custom.css" rel="stylesheet"	type="text/css" />
<script type="text/javascript" src="<%=basePath%>resources/js/util.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/ss_util.js"></script>
<link href="<%=basePath%>resources/css/module.css" rel="stylesheet" type="text/css" />