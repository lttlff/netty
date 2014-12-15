<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp"%>

<link href="<%=basePath%>resources/css/module.css" rel="stylesheet" type="text/css" />

<title>iCRM</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="iCRM登录界面">

<script type="text/javascript" src="<%=basePath%>resources/js/jquery/jquery.md5.js" ></script>
<script type="text/javascript" src="<%=basePath%>resources/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath%>rbac/login/login.js"></script>
</head>
<body>
	<div class="container login_container">
		<a href="javascript:;" class="logo" title="iCRM"></a>
		<div class="login_cont">
			<h2 class="tit">账号登录</h2>
			<form id="loginForm" action="<c:url value="/login.do"></c:url>"  method="post" >
			
				<ul class="login_box">
					<li>
						<input type="text" id="userName" name="userName" class="tt" placeholder="用户名" title="请输入用户名">
					</li>
					<li>
						<input type="password" id="userPsw1" class="tt mm" placeholder="密码" title="请输入密码">
						<input type="password" id="userPsw" name="userPsw" style="display: none;">
						<br />
						<c:if test= '${!empty  requestScope["javax.servlet.error.message"] }'>
							<font color="red"><c:out value='${requestScope["javax.servlet.error.message"] }'></c:out></font>
						</c:if>
					</li>
					<li></li>
					<li>
						<label class="checkbox inline"> <input type="checkbox" id="ck_rmbUser">下次自动登录
					</label></li>
					<li><input type="submit" id="submit" value="登录"
						class="btn  btn-primary btn-s05 btn_login"></li>
				</ul>
			</form>
		</div>
	</div>
</body>
</html>
