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
<link href="<%=basePath%>resources/css/icrm_icon.css"  rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->

	<!-- Fav and touch icons -->
	<link rel="shortcut icon" href="<%=basePath%>icrm/dynamic/manager/layoutit/img/favicon.png">
	
 	<!--[if lt IE 9]>
	<script src="<%=basePath%>icrm/dynamic/manager/layoutit/js/html5shiv.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	<![endif]-->
	
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/jquery.2.1.0.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/jquery.ui.touch-punch.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/jquery.htmlClean.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/ckeditor/config.js"></script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/js/scripts.js"></script>
	<script type="text/javascript"
	     src="<%=basePath%>resources/js/bootstrap-3.0.3/ext_js/bootstrap-datetimepicker.js">
	    </script>
	<script type="text/javascript" src="<%=basePath%>icrm/dynamic/manager/layoutit/my/save.js"></script>
</head>

<body style="min-height: 660px; cursor: auto;" class="edit">

<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container-fluid">
      <button data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar" type="button"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="brand" href="http://justjavac.com/tools/layoutit/"><img src="<%=basePath%>icrm/dynamic/manager/layoutit/img/favicon.png"> 可视化布局<span class="label">BETA</span></a>
      <div class="nav-collapse collapse">
      	<ul class="nav" id="menu-layoutit">
          <li class="divider-vertical"></li>
          <li>
            <div class="btn-group">
              <a class="btn btn-primary" href="http://justjavac.com" target="_blank"><i class="icon-home icon-white"></i>首页</a>
            </div>
            <div class="btn-group" data-toggle="buttons-radio">
              <button type="button" id="edit" class="btn btn-primary active"><i class="icon-edit icon-white"></i>编辑</button>
              <button type="button" class="btn btn-primary" id="devpreview"><i class="icon-eye-close icon-white"></i>布局编辑</button>
              <button type="button" class="btn btn-primary" id="sourcepreview"><i class="icon-eye-open icon-white"></i>预览</button>
            </div>
            <div class="btn-group">
<!--              
	<button type="button" class="btn btn-primary" data-target="#downloadModal" rel="/build/downloadModal" role="button" data-toggle="modal"><i class="icon-chevron-down icon-white"></i>下载</button>
 -->            
 			  <button class="btn btn-primary" href="/share/index" role="button" data-toggle="modal" data-target="#shareModal"><i class="icon-share icon-white"></i>保存</button>
              <button class="btn btn-primary" href="#clear" id="clear"><i class="icon-trash icon-white"></i>清空</button>
            </div>
            <div class="btn-group">
				<button class="btn btn-primary" href="#undo" id="undo" ><i class="icon-arrow-left icon-white"></i>撤销</button>
				<button class="btn btn-primary" href="#redo" id="redo" ><i class="icon-arrow-right icon-white"></i>重做</button>
			</div>
          </li>
        </ul>
        <ul class="nav pull-right">
               	  <li>
               	  <div class="btn-group">
               	  <span><iframe class="github-btn" src="http://ghbtns.com/github-btn.html?user=justjavac&repo=layoutit&type=watch&count=true" allowtransparency="true" frameborder="0" scrolling="0" width="80px" height="20px"></iframe></span>
			      <span><iframe class="github-btn" src="http://ghbtns.com/github-btn.html?user=justjavac&repo=layoutit&type=fork&count=true" allowtransparency="true" frameborder="0" scrolling="0" width="80px" height="20px"></iframe></span>
			      </div>
			      </li>
          </ul>
      </div>
      <!--/.nav-collapse --> 
    </div>
  </div>
</div>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="">
      <div class="sidebar-nav">
        <ul class="nav nav-list accordion-group">
          <li class="nav-header">
            <div class="pull-right popover-info"><i class="icon-question-sign "></i>
              <div class="popover fade right">
                <div class="arrow"></div>
                <h3 class="popover-title">功能</h3>
                <div class="popover-content">在这里设置你的栅格布局, 栅格总数默认为12, 用空格分割每列的栅格值, 如果您需要了解更多信息，请访问<a target="_blank" href="http://twitter.github.io/bootstrap/scaffolding.html#gridSystem">Bootstrap栅格系统.</a></div>
              </div>
            </div>
            <i class="icon-plus icon-white"></i> 布局设置 </li>
          <li style="display: none;" class="rows" id="estRows">
            <div class="lyrow ui-draggable"> <a href="#close" class="remove label label-important"><i class="icon-remove icon-white"></i>删除</a> <span class="drag label"><i class="icon-move"></i>拖动</span>
              <div class="preview">
                <input value="12" type="text">
              </div>
              <div class="view">
                <div class="row-fluid clearfix">
                  <div class="span12 column"></div>
                </div>
              </div>
            </div>
            <div class="lyrow ui-draggable">
            	<a href="#close" class="remove label label-important"><i class="icon-remove icon-white"></i>删除</a>
            	<span class="drag label"><i class="icon-move"></i>拖动</span>
              <div class="preview">
                <input value="2 ColumnForm" type="text">
              </div>
              <div class="view">
              	<form class="form-horizontal" role="form" >
	                  <div class="span6 column group">
	                  
	                  </div>
	                  <div class="span6 column group">
	                  
	                  </div>
              	</form>
              </div>
            </div>
            <div class="lyrow ui-draggable"> <a href="#close" class="remove label label-important"><i class="icon-remove icon-white"></i>删除</a> <span class="drag label"><i class="icon-move"></i>拖动</span>
              <div class="preview">
                <input value="2 6 4" type="text">
              </div>
              <div class="view">
                <div class="row-fluid clearfix">
                  <div class="span2 column"></div>
                  <div class="span6 column"></div>
                  <div class="span4 column"></div>
                </div>
              </div>
            </div>
          </li>
        </ul>
        <ul class="nav nav-list accordion-group">
          <li class="nav-header"><i class="icon-plus icon-white"></i> 基本CSS
            <div class="pull-right popover-info"><i class="icon-question-sign "></i>
              <div class="popover fade right">
                <div class="arrow"></div>
                <h3 class="popover-title">帮助</h3>
                <div class="popover-content">这里提供了一系列基本元素样式，你可以通过区块右上角的编辑按钮修改样式设置。如需了解更多信息，请访问<a target="_blank" href="http://twitter.github.io/bootstrap/base-css.html">基本CSS.</a></div>
              </div>
            </div>
          </li>
          <li style="display: list-item;" class="boxes" id="elmBase">
            <!-- zhunb start-->
            <c:forEach var="field" items="${feildList}" >
	            <div class="box box-element ui-draggable">
					<a href="#close" class="remove label label-important">
					<i class="icon-remove icon-white"></i>删除</a>
					<span class="drag label">
						<i class="icon-move"></i>拖动</span><span class="configuration">
					</span>
					<span class="configuration">
						<button type="button" class="btn btn-mini" data-target="#editorModal" role="button" data-toggle="modal">属性设置</button>
					</span>
					<div class="preview">${field.fieldName}</div>
					<div class="view">
						<div class="control-group">
		                    <label class="control-label" for="${field.fieldCode}" contenteditable="true">${field.fieldName}</label>
		                    <div class="controls">
		                    	<c:set var="fieldType">${field.fieldType}</c:set>
		                    	<c:choose>  
									<c:when test="${fieldType=='text'}">
										 <input type="text" id="${field.fieldCode}" name="${field.fieldCode}" field_attr="${field.id}" field_data="" />
									</c:when>  
									<c:when test="${fieldType=='number'}">
										 <input type="number" id="${field.fieldCode}" name="${field.fieldCode}" field_attr="${field.id}" field_data="">
									</c:when>  
									<c:when test="${fieldType=='combo'}">
										<select  id="${field.fieldCode}" name="${field.fieldCode}" field_attr="${field.id}" field_data="">
										</select>
									</c:when>  
									<c:when test="${fieldType=='date'}">
										<div class="input-append date">
											<input type="text" id="${field.fieldCode}" name="${field.fieldCode}" field_attr="${field.id}" field_data="" >
											<span class="add-on"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
										</div>
									</c:when>  
									<c:when test="${fieldType=='trigger'}">
										<input  id="${field.fieldCode}" name="${field.fieldCode}" field_attr="${field.id}" field_data="" type="text" readonly="true" >
										<button type="button" class="btn btn-default">Click</button>
									</c:when>  
									<c:when test="${fieldType=='textarea'}">
										<textarea  id="${field.fieldCode}" name="${field.fieldCode}" field_attr="${field.id}" field_data="" rows="3" style="style="resize:none";"></textarea>
									</c:when>  
		                    	</c:choose>
		                    </div>
		                </div>
					</div>
				</div>
			</c:forEach> 
			<!-- zhunb end -->
          </li>
        <ul class="nav nav-list accordion-group">
          <li class="nav-header"><i class="icon-plus icon-white"></i> 组件
            <div class="pull-right popover-info"><i class="icon-question-sign "></i>
              <div class="popover fade right">
                <div class="arrow"></div>
                <h3 class="popover-title">帮助</h3>
                <div class="popover-content">拖放组件到布局框内. 拖入后你可以设置组件样式. 查看这里获取更多帮助 <a target="_blank" href="http://twitter.github.io/bootstrap/components.html">Components.</a></div>
              </div>
            </div>
          </li>
          <li style="display: none;" class="boxes" id="elmComponents">
          </li>
        </ul>
        <ul class="nav nav-list accordion-group">
          <li class="nav-header"><i class="icon-plus icon-white"></i> 交互组件 <span class="label label-important">NEW!</span>
            <div class="pull-right popover-info"><i class="icon-question-sign "></i>
              <div class="popover fade right">
                <div class="arrow"></div>
                <h3 class="popover-title">帮助</h3>
                <div class="popover-content">拖放组件到布局容器. 拖入后, 你可以配置显示样式. 如果有任何疑问可访问 <a target="_blank" href="http://twitter.github.io/bootstrap/javascript.html">JavaScript.</a></div>
              </div>
            </div>
          </li>
          <li style="display: none;" class="boxes mute" id="elmJS">
            <div class="box box-element ui-draggable"> <a href="#close" class="remove label label-important"><i class="icon-remove icon-white"></i>删除</a> <span class="drag label"><i class="icon-move"></i>拖动</span> <span class="configuration">
            	<button type="button" class="btn btn-mini" data-target="#editorModal" role="button" data-toggle="modal">编辑</button>
            	<span class="btn-group"> <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">位置 <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li class="active"><a href="#" rel="">默认</a></li>
                <li class=""><a href="#" rel="tabs-below">底部</a></li>
                <li class=""><a href="#" rel="tabs-left">靠左</a></li>
                <li class=""><a href="#" rel="tabs-right">靠右</a></li>
              </ul>
              </span> </span>
              <div class="preview">切换卡</div>
              <div class="view">
                <div class="tabbable" id="myTabs">
                  <ul class="nav nav-tabs">
                    <li class="active"><a href="#tab1" data-toggle="tab" contenteditable="true">第一部分</a></li>
                    <li><a href="#tab2" data-toggle="tab" contenteditable="true">第二部分</a></li>
                  </ul>
                  <div class="tab-content">
                    <div class="tab-pane active" id="tab1" contenteditable="true">
                      <div class="lyrow ui-draggable"> <a href="#close" class="remove label label-important"><i class="icon-remove icon-white"></i>删除</a> <span class="drag label"><i class="icon-move"></i>拖动</span>
		              <div class="preview">
		                <input value="2 6 4" type="text">
		              </div>
		              <div class="view">
		                <div class="row-fluid clearfix">
		                  <div class="span12 column">
		                  </div>
		                </div>
		              </div>
		            </div>
		                      	
                    </div>
                    <div class="tab-pane" id="tab2" contenteditable="true">
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </li>
        </ul>
        <ul class="nav nav-list accordion-group">
          <li class="nav-header"><i class="icon-plus icon-white"></i> 应用扩展 </li>
          <li style="display: none;" class="boxes mute" id="elmComm">
            <div class="preview">建设中...</div>
          </li>
        </ul>
      </div>
    </div>
    <!--/span-->
    <div class="demo ui-sortable" style="min-height: 304px; ">
      
  
  
    </div>
    <!-- end demo -->
    <!--/span-->
    <div id="download-layout">
      <div class="container-fluid"></div>
    </div>
  </div>
  <!--/row--> 
</div>
<!--/.fluid-container--> 
<div class="modal hide fade" role="dialog" id="editorModal">
  <div class="modal-header"> <a class="close" data-dismiss="modal">×</a>
    <h3>编辑</h3>
  </div>
  <div style="padding-right: 15px; padding-left: 15px;">
	    <form class="form-horizontal" role="form">
				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <div class="checkbox">
				        <label>
				          <input type="checkbox" id="attr-allowblank" name="attr-allowblank" />允许为空
				        </label>
				      </div>
				    </div>
				</div>
				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <div class="checkbox">
				        <label>
				          <input type="checkbox" id="attr-readonly" name="attr-readonly" />是否只读
				        </label>
				      </div>
				    </div>
				</div>
				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <div class="checkbox">
				        <label>
				          <input type="checkbox" id="attr-hidden" name="attr-hidden" />是否隐藏
				        </label>
				      </div>
				    </div>
				</div>
				<div class="form-group">
				   <label for="attr-defaultval" class="col-sm-2 control-label">默认值</label>
				   <div class="col-sm-10">
				     <input type="text" class="form-control" id="attr-defaultval" name="attr-defaultval" />
				   </div>
				</div>
		</form>
  </div>
	<div class="modal-footer">
	 	<a id="savecontent" class="btn btn-primary" data-dismiss="modal">保存</a>
	 	<a class="btn" data-dismiss="modal">关闭</a>
	</div>
</div>
<div class="modal hide fade" role="dialog" id="downloadModal">
  <div class="modal-header"> <a class="close" data-dismiss="modal">×</a>
    <h3>下载</h3>
  </div>
  <div class="modal-body">
    <p>已在下面生成干净的HTML, 可以复制粘贴代码到你的项目.</p>
    <div class="btn-group">
      <button type="button" id="fluidPage" class="active btn btn-info"><i class="icon-fullscreen icon-white"></i> 自适应宽度</button>
      <button type="button" class="btn btn-info" id="fixedPage"><i class="icon-screenshot icon-white"></i> 固定宽度</button>
    </div>
    <br>
    <br>
    <p>
      <textarea></textarea>
    </p>
  </div>
  <div class="modal-footer"> <a class="btn" data-dismiss="modal">关闭</a> </div>
</div>
<div class="modal hide fade" role="dialog" id="shareModal">
  <div class="modal-header"> <a class="close" data-dismiss="modal">×</a>
    <h3>保存</h3>
  </div>
  <div class="modal-body">保存成功</div>
  <div class="modal-footer"> <a class="btn" data-dismiss="modal">Close</a> </div>
</div>
</body>
</html>
