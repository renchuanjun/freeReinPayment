<#compress>
<#macro head title="富勤金融管理系统">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <#include "head.ftl" encoding="utf8">
    <#nested>
    <title>${title!""}</title>
    <base target="_self" />
</head>
</#macro>
<#macro body>
<body>
	<div class="navbar navbar-default" id="navbar">
		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"  style="text-align: center;">				
					<img src="${contextPath!"" }/images/default/logo.png" width="430px" style="margin: 0 auto;">
				</a>
			</div>
		   <div class="navbar-header pull-right" role="navigation">
           <ul class="nav ace-nav">	
            <li class="light-blue">
			<a data-toggle="dropdown" href="#" class="dropdown-toggle">
			 <span  class="time"><em id="time"></em></span><span class="user-info"><small>欢迎光临,</small>超级管理员</span>
			 <i class="icon-caret-down"></i>
			</a>
			<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
			 <li><a title="个人信息" class="iframeurl"><i class="icon-user"></i>个人资料</a></li>
			 <li class="divider"></li>
			 <li><a id="Exit_system"><i class="icon-off"></i>退出</a></li>
			</ul>
		   </li>			
            </div>
		</div>
		</div>
		<div class="main-container" id="main-container">
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
	<#include "menu.ftl" encoding="utf8">
	<#include "bottom.ftl" encoding="utf8">
	<#nested>
	</#macro>
	<#macro top>
	<#include "top.ftl" encoding="utf8">
	
	<#nested>
	</#macro>

	


	

	<#macro js>
	<#include "js.ftl" encoding="utf8">
	<#nested>
	</div>
	</div>
     <!--底部样式-->
     <div class="footer_style" id="footerstyle">  
      <p class="l_f">版权所有：南京四美软件  苏ICP备11011739号</p>
      <p class="r_f">地址：南京市鼓楼区阅江楼街道公共路64号  邮编：210011 技术支持：XXXX</p>
    </div>
</body>
</html>
</#macro>
</#compress>