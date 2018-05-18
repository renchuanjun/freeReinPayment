<#global contextPath=(rc.getContextPath())!"">
<#global skin="default">
<link rel="stylesheet" href="${contextPath!"" }/styles/default/bootstrap.min.css"/>
<link rel="stylesheet" href="${contextPath!"" }/styles/default/font-awesome.min.css" />
<link rel="stylesheet" href="${contextPath!"" }/styles/default/ace.min.css" />
<link rel="stylesheet" href="${contextPath!"" }/styles/default/ace-rtl.min.css" />
<link rel="stylesheet" href="${contextPath!"" }/styles/default/ace-skins.min.css" />
<link rel="stylesheet" href="${contextPath!"" }/styles/default/style.css"/>
<!--分页表格插件-->
<link href="${contextPath!"" }/styles/${skin!"" }/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
<link href="${contextPath!"" }/styles/${skin!"" }/bootstrap-table-reset.css" rel="stylesheet" type="text/css" />
<!--日历控件-->
<link href="${contextPath!"" }/styles/${skin!"" }/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${contextPath!"" }/styles/${skin!"" }/jAlert.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    //虚拟路径
    var virtualPath = "${contextPath!"" }";
    var flagSubmit = true;//标记,防止重复提交
    var tokenVal = "${tokenVal!""}";
    var skin = "${skin!""}";
</script>
<script src="${contextPath!"" }/scripts/jquery-1.12.4.min.js" type="text/javascript"></script>
