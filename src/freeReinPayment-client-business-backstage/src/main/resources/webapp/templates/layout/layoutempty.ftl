<#compress>
    <#macro head title="富勤金融">
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <#include "head.ftl" encoding="utf8">
    <#nested>
    <title>${title!""}</title>
</head>
    </#macro>
    <#macro body>
	<body class="full-width">
    <#nested>
    </#macro>





    <#macro js>
    <#include "js.ftl" encoding="utf8">
    <script type="text/javascript">
        //用于子页面关闭窗口使用
        var frameindex = parent.layer.getFrameIndex(window.name); //获取窗口索引
       //获取url参数
        function getQueryString(name) {
            var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return unescape(r[2]);
            }
            return null;
        }
    </script>
    <#nested>
</body>
</html>
    </#macro>
</#compress>