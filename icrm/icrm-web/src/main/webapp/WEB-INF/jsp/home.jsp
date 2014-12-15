<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jquery.jsp"%>
<title>iCRM</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<script type="text/javascript" src="<%=basePath%>rbac/login/home.js"></script>

</head>
<body>
	<div class="container">
		<div class="header">
			<a href="javascript:;" class="logo"></a>
			<div class="top_r">
				你好，<a href="javascript:void(0);" title="${user.realName }">${user.realName }</a><i>|</i>
					  <a href="javascript:void(0);" title="点击退出" id="logout">注销</a>
			</div>
			<ul class="nav_list">
				<c:forEach var="menu" items="${menuList}" varStatus="status">
					<c:choose>
						<c:when test="${status.index==0}">
							<li class="cur" id="${menu.id}"  menuDesc="${menu.menuDesc }" menuUrl="${menu.menuUrl }"><a href="javascript:;" >${menu.menuName}</a></li>
						</c:when>
						<c:otherwise>
							<li id="${menu.id}" menuDesc="${menu.menuDesc }" menuUrl="${menu.menuUrl }"><a href="javascript:;">${menu.menuName}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
			<c:forEach var="menu" items="${menuList}"  varStatus="status">
				<ul class="nav_sub"  id="menu_sub_${menu.id}" style="display: none;">
						<c:forEach var="subMenu" items="${menu.subList}" varStatus="subStatus">
							<c:choose>
								<c:when test="${!empty subMenu.subList}">
									<li menuDesc="${subMenu.menuDesc }" menuUrl="${subMenu.menuUrl }"><a href="javascript:;">${subMenu.menuName}
										<span class="ico_d"></span></a>
										<ul class="nav_sub_list" style="display: none;">
											<c:forEach var="subSubMenu" items="${subMenu.subList}">
												<li menuDesc="${subSubMenu.menuDesc }" menuUrl=${subSubMenu.menuUrl }><a href="javascript:void(0);">${subSubMenu.menuName }</a></li>
											</c:forEach>
										</ul>
										<c:if test="${!subStatus.last}"><i>|</i></c:if>
									</li>
								</c:when>
								<c:otherwise>
									<li menuDesc="${subMenu.menuDesc }" menuUrl="${subMenu.menuUrl }">
										<a href="javascript:;">${subMenu.menuName}<c:if test="${!subStatus.last}"><i>|</i></c:if></a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</ul>
			</c:forEach>
		</div>
		<div>
			<div class="header2" style=""><span>当前位置：</span><span id="bodyLocation">页面当前位置........</span></div>
			<div>
				<iframe id="bodyFrame" onload="iframeHeight()" width="100%" src="" style="border: 0px;"></iframe>
				<script type="">
					function iframeHeight(){

						var ifm = document.getElementById("bodyFrame");
var subWeb = document.frames?document.frames["bodyFrame"].document:ifm.contentDocument
if(ifm!=null && subWeb!=null){
	ifm.height = (subWeb.body.scrollHeight+30)>500?(subWeb.body.scrollHeight+30):500;
}
					}
				</script>
				
			</div>
		</div>
	</div>
</body>
</html>
