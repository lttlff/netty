<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	var basePath = "<%=basePath%>";
</script>
<style type="text/css" >
	body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video { margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; }
	body { font-family: "Helvetica Neue", Helvetica, Arial, "Microsoft YaHei UI", "Microsoft YaHei", "WenQuanYi Micro Hei", sans-serif; color: #333333;font-size: 12px; line-height: 1.5; }
</style>
<script type="text/javascript" src="<%=basePath%>resources/js/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/jquery/jquery-ui-1.9.2/js/jquery-ui-1.9.2.min.js"></script>
<link href="<%=basePath%>resources/js/jquery/jquery-ui-1.9.2/css/start/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" type="text/css" />
