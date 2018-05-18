<#compress>
<#macro head title="海航资本管控">
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
	<div class="warp outer">
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
</body>
</html>
</#macro>
</#compress>