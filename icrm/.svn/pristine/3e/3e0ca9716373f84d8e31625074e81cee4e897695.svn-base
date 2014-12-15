<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<c:set var="page" value="${sessionScope.page}" ></c:set>
<c:set var="url" value="${param.url }" ></c:set>
<c:set var="urlParams" value="${param.urlParams }" ></c:set>
<c:set var="pathurl" value="${pageContext.request.contextPath}/${url }" ></c:set>

<div class="page_cont">
	<span class="mr10">共<strong class="red">${page.totalCount}</strong>条记录</span>
	<span class="mr10">共<strong class="red">${page.totalPage}</strong>页</span>
	<span class="mr10">每页显示<strong class="red">${page.everyPage}</strong>条</span>
	<span class="mr10">当前第<strong class="red">${page.currentPage}</strong>页</span>
	<c:choose><c:when test="${page.hasPrePage eq false}"></c:when>
		<c:otherwise>
		    <input title="首页"  id="${prefix}_first" type="submit" onclick="goPage('${pathurl }?pageAction=first${urlParams}')" class="btn btn-s01" value="首页" />
  			<input title="上一页" id="${prefix}_previous" type="submit" onclick="goPage('${pathurl }?pageAction=previous${urlParams}')" class="btn btn-s01" value="上一页" />
		</c:otherwise>
	</c:choose>
	<c:choose><c:when test="${page.hasNextPage eq false}"></c:when>
		<c:otherwise>
		      <input title="下一页" type="submit" id="${prefix}_next" onclick="goPage('${pathurl }?pageAction=next${urlParams}')" class="btn btn-s01" value="下一页" />
		      <input title="末页" type="submit" id="${prefix}_last" onclick="goPage('${pathurl }?pageAction=last${urlParams}')" class="btn btn-s01" value="末页" />
		</c:otherwise>
	</c:choose>
 </div>
<script>
	//var goPage = function(loadType){
	//	window.location.href = basePath+"userController/index.do?pageAction="+loadType;
	//}
	var goPage = function(url){
		window.location.href = url;
	}
</script>