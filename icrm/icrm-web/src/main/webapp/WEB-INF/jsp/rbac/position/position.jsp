<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/resources/jsp/jstree.jsp" %>
<title>职位管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="<%=basePath%>rbac/position/position.tree.js"></script>

</head>
<body>
	
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				<tr >
					 <td id="frmlf" name="frmlf" style="width: 18%; height: 1000px">
						  <div id="posTreeDiv"></div>
                     </td>
                     <td id="frmmd" valign="top" width="2%">
                          <TABLE cellPadding="0" cellSpacing="0" class="table100">
                            <TBODY>
	                           <tr onClick="switchSysBar()" >
                                    <td width="2%" class="bg_arrow" valign="top" >
                                         <SPAN class="navPoint" id="switchPoint" title=关闭/打开左栏><img src="<%=basePath%>resources/images/l_arrow.jpg"  name="aa" id="aa" border="0"></SPAN>
                                    </td>
                               </tr>
	                        </TBODY>
                          </TABLE>
                      </td>
                     <td valign="top" id="frmrf" name="frmrf" style="width: 80%; height: 100%" >
                         <IFRAME src="<%=basePath%>position/main.do?parPosId=-1"  width="100%" height="100%"  scroll="auto" frameBorder=0 border="0" id="mainFrame" name="mainFrame"></IFRAME>
                     </td>
                 </tr>
    
</table>
</body>
<SCRIPT language="javascript">

  function switchSysBar(){
    if (switchPoint.innerHtml=='<img src='+basePath+'"resources/images/r_arrow.jpg" border=0>'){
      switchPoint.innerHtml='<img src='+basePath+'"resources/images/l_arrow.jpg" border=0>';
	  document.all("aa").src=basePath + "resources/images/l_arrow.jpg";
      document.all("frmlf").style.display=""
      document.all("frmrf").style.width=screen.width-160;
	  //document.all("frmmd").style.width='2%';
    }
    else{
      switchPoint.innerHtml='<img src='+basePath+'"resources/images/r_arrow.jpg" border=0>'
	  document.all("aa").src=basePath + "resources/images/r_arrow.jpg";
      document.all("frmlf").style.display="none"
      document.all("frmrf").style.width='100%';
    }
  }
</SCRIPT>
</html>