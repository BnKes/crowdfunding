<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container">
		
      <form id="loginForm" action="${APP_PATH }/doLogin.do" method="POST" class="form-signin" role="form">
      	${exception.message }
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="floginacct" name="loginacct" value="superadmin" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="fuserpswd" name="userpswd" value="123" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select id="ftype" class="form-control" name="type">
                <option value="member" >会员</option>
                <option value="user" selected>管理</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            <input id="rememberme" type="checkbox" value="1"> 记住我两周
          </label>
          <br>
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="reg.html">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${APP_PATH }/jquery/layer/layer.js"></script>
    <script>
    function dologin() {
    	/* $("#loginForm").submit(); */
    	
    	var floginacct = $("#floginacct");
    	var fuserpswd = $("#fuserpswd");
    	var ftype = $("#ftype"); 
    	
    	if($.trim(floginacct.val())==""){
//    		alert("用户账号不能为空，请重新输入！");
/**
 * time:1000,  显示1000ms
 icon:5,    显示第5个图案，一个哭脸
 shift:6    弹窗抖动
 */
    		layer.msg("用户账号不能为空，请重新输入！",{time:1000, icon:5, shift:6}, function(){   
    			floginacct.val("");//赋值为空
        		floginacct.focus();
    		});
    		return false;  //结束if
    	}
    	
    	if($.trim(fuserpswd.val())==""){
    	//	alert("用户密码不能为空，请重新输入！");
    		layer.msg("用户密码不能为空，请重新输入！",{time:1000, icon:5, shift:6}, function(){
    		fuserpswd.val("");
    		fuserpswd.focus();
    		});
    		return false;
    	}
    	
    	var loadingIndex = -1 ;
    	var flag = $("#rememberme")[0].checked;  //选择为true,否则为false
 /*    	var ff = $("#rememberme").val(); */
    	$.ajax({
    		type : "POST",
    		data : {
    			loginacct : floginacct.val(),
    			userpswd : fuserpswd.val(),
    			type : ftype.val(),
    			rememberme : flag?"1":"0"
    		},
    		url : "${APP_PATH}/doLogin.do",
    		beforeSend : function(){
    			loadingIndex = layer.msg('处理中', {icon: 16});//转圈的图案
    			//一般做表单数据校验.
    			return true ;
    		},
    		success : function(result){ //{"success":true}  或    {"success":false,"message":"登录失败!"}
    			layer.close(loadingIndex);//关闭loadingIndex = layer.msg('处理中', {icon: 16})的layer
    			if(result.success){    
     				if(ftype.val()=="user"){
        				window.location.href="${APP_PATH}/main.htm";
    				}else if(ftype.val()=="member"){ 
    					window.location.href="${APP_PATH}/member.htm";
    				}else{
    					layer.msg("登录身份有误!", {time:1000, icon:5, shift:6});
    				} 
    			}else{
    				layer.msg(result.message, {time:1000, icon:5, shift:6});
    			}
    		},
    		error : function(){
    			layer.msg("登录出错啦!", {time:1000, icon:5, shift:6});
    		}	
    	});
        /* var type = $(":selected").val();
        if ( type == "user" ) {
            window.location.href = "main.html";
        } else {
            window.location.href = "index.html";
        } */
    }
    </script>
  </body>
</html>