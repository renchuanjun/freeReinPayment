<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<#include "layout/head.ftl" encoding="utf8">
<link href="${contextPath!"" }/styles/${skin!"" }/alls.css" rel="stylesheet" type="text/css" />
<title>index</title> <#include "layout/js.ftl" encoding="utf8">
</head>
<script type="text/javascript">
	function validateSubmits(){
		if(account()){
			$("#uidcss").css("display","block");
			$("input").focus(function(){
  				$("#uidcss").css("display","");
  				$("#codecss").css("display","");
			});
			return  false;
		}else if(passWord()){
			$("#pwdcss").css("display","block");
			$("input").focus(function(){
  				$("#pwdcss").css("display","");
  				$("#codecss").css("display","");
			});
			return  false;
		}else if(code()){
			$("#codecss").css("display","block");
			$("#codecss").empty();
			$("#codecss").html("验证码不能为空");
			$("input").focus(function(){
  				$("#codecss").css("display","");
			});
			return  false;
		}else{
			$("#loginForm").ajaxSubmit({
                dataType: "text",
                type: "post",
                async: false,
				success:function(Object){
					if("success" == Object){
						location.href="${contextPath!'' }/backlog/index";
					}else if("fail" == Object){
                        $("#codecss").css("display","block");
                        $("#codecss").empty();
                        $("#codecss").html("用户名或密码错误");
                        $("input").focus(function(){
                            $("#codecss").css("display","");
                        });
                    }else{
						$("#codecss").css("display","block");
						$("#codecss").empty();
						$("#codecss").html("验证码错误");
						$("input").focus(function(){
  							$("#codecss").css("display","");
						});
					}
				}

			});
		}
	};

	//验证账号是否为空
	function account(){
		var reg = /^\s*$/g;
		var bool = true;
		var uid = $("#userName").val();
		if(null != uid && !reg.test(uid)){
			bool = false;
		}
		return bool;
	}
	
	//验证密码不能为空
	function passWord(){
		var reg = /^\s*$/g;
		var bool = true;
		var pwd = $("#passWord").val();
		if(null != pwd && !reg.test(pwd)){
			bool = false;
		}
		return bool;
	}

	//验证密码不能为空
	function code(){
		var reg = /^\s*$/g;
		var bool = true;
		var code = $("#code").val();
		if(null != code && !reg.test(code)){
			bool = false;
		}
		return bool;
	}
	
	$(function(){ 
		$(document).keydown(function(event){ 
			if(event.keyCode==13){ 
				validateSubmits(); 
			} 
		});

        window.load = function(){
            document.getElementById("passWord").value='';
        };
	})


</script>
<body id="body" class="easyui-layout"> 
	<div class="warp">
		<div class="login-bg"></div>
		<div class="content m-top35">
			<div class="login-box">
				<div class="f-left p-r login-boxleft">
					<img src="${contextPath!" " }/styles/${skin!"" }/images/loginlogo.png" style="margin-top:100px;padding-left: 70px;"/>
				</div>
				<div class="f-right login-boxright">
					<h3 class="amyh3 l_60 t-l m-left20">海航资本BIM报送平台</h3>
					<form id="loginForm" action="${contextPath!"" }/loginsubmit" method="post">
					<input type="hidden" name="token" value="${tokenVal!""}"/>
					<ul class="loginul">
						<li>
							<input class="amyinput icon-user" type="text" autocomplete="off" name="userName" id="userName" value="" placeholder="请输入登录帐号" />
							<em class="error" style="display: ;" id="uidcss">账号不能为空</em>
						</li>
						<li>
							<input class="amyinput icon-password" onfocus="this.type='password'" autocomplete="off" type="password" name="passWord" id="passWord" value="" placeholder="请输入登录密码" />
							<em class="error" style="display: ;" id="pwdcss">密码不能为空</em>
						</li>
						<li>
							<input class="amyinput w80 icon-yzm" type="text" name="code" id="code" value="" placeholder="请输入验证码" />
							<img id="imgVerify" alt="看不清？点击更换" onclick="this.src='${contextPath!"" }/validatecode?t='+Math.random()"
                			src="${contextPath!"" }/validatecode" align="bottom" class="yzm"/>
							<em class="error"style="display: ;" id="codecss">验证码不能为空</em>
						</li>
						<li><a class="login-btn" id="loginSubmit" onclick="return validateSubmits()">登&nbsp;&nbsp;录</a></li>
					</ul>
					</form>
				</div>
				
			</div>
			<div class="foot">
				<em class="copyright">版权所有&nbsp;&nbsp;京ICP证070359号</em>
			</div>
			
		</div>
	</div>

</body>
</html>
