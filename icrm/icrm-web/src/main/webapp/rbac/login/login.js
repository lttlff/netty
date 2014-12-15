$(document).ready(function() {
	$(".yzmChange").bind('click', function() {
				alert("切换验证码");
			});
	var isValid = true;
	var $loginForm = $("#loginForm");
	if ($.cookie("icrm_rmbUser") == "true") {  
        $("#ck_rmbUser").prop("checked", true);  
        $("#userName").val($.cookie("icrm_username"));  
        $("#userPsw").val($.cookie("icrm_password"));
        $("#userPsw1").val($.cookie("icrm_password"));
     }
	

    var errorCount = 0;
	$("#submit").bind('click', function() {
		if($loginForm.find("input[name=userName]").val()==""||$loginForm.find("input[name=userPsw]")==""){
			return false;
		}

//		var $yzmLi = $(".login_box").find("li:eq(2)");
//		if($yzmLi.find("#validCode").length>0){
//			if($yzmLi.find("#validCode").val()==""){
//				return false;
//			}
//		}
//		
//		
//		if ("密码错误") {
//			errorCount++;
//			if (errorCount >= 1) {
//				$yzmLi.html('<input type="text" class="tt yzm" placeholder="验证码" title="请输入验证码" id="validCode" name="validCode">'
//								+ '<span class="img_yzm"><img src="resources/images/img_yzm.png" width="75" height="32" alt="点击换一张" /></span>'
//								+ '<a href="javascript:;" class="yzmChange">点击换一张</a>');
//			}
//			$yzmLi.find(".yzmChange").bind('click',function(){
//				$yzmLi.find("img").attr("src",basePath+"randCode/image.do?"+Math.random());
//			});
//		}
        if($("#userPsw").val()==""){
	        $("#userPsw").val($.md5($("#userPsw1").val()));
        }
        //判断密码是不是修改了
        if(!($.cookie("icrm_password")==$("#userPsw1").val()||$.md5($("#userPsw1").val())==$.cookie("icrm_password"))){
        	$("#userPsw").val($.md5($("#userPsw1").val()));
        }
        if ($("#ck_rmbUser").prop("checked")) {
            var userName = $("#userName").val();  
            var userPsw = $("#userPsw").val();  
            $.cookie("icrm_rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
            $.cookie("icrm_username", userName, { expires: 7 });  
            $.cookie("icrm_password", userPsw, { expires: 7 });  
        }  
        else {  
            $.cookie("icrm_rmbUser", "false", { expire: -1 });  
            $.cookie("icrm_username", "", { expires: -1 });  
            $.cookie("icrm_password", "", { expires: -1 });  
        }
        
        $("#loginForm").submit();
	});
});