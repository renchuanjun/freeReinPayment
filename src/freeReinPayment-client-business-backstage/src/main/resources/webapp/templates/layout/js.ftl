<script language="JavaScript" type="text/javascript">
    //ajax异步提交数据
    function submitAjaxByAsync(url,jsonValue,fun){
        alert(jsonValue);
    	$.ajax({
            url: (virtualPath+url),
            type: "post",
            data: jsonValue,
            dataType: 'text',
            async: true,
            success: fun
        });
    }
</script>
<script src="${contextPath!"" }/scripts/ace-extra.min.js"></script>
<script src="${contextPath!"" }/scripts/bootstrap.min.js"></script>
<script src="${contextPath!"" }/scripts/bootstrap-table.js" type="text/javascript"></script>
<script src="${contextPath!"" }/scripts/ace.min.js"></script><!--菜单js-->
<script src="${contextPath!"" }/scripts/jquery.alerts.js" type="text/javascript"></script>
<script src="${contextPath!"" }/scripts/bootstrap-table-zh-CN.js" type="text/javascript"></script>
<script src="${contextPath!"" }/scripts/common.js" type="text/javascript"></script>
<script src="${contextPath!"" }/scripts/baiduTemplate.js" type="text/javascript"></script>

<script src="${contextPath!"" }/scripts/jquery.form.js" type="text/javascript"></script>
<!-- 解决IE8 兼容问题 -->
<script src="${contextPath!"" }/scripts/respond.js" type="text/javascript"></script>
<!-- 解决非 html5 浏览器 兼容 html5 css 样式-->
<script src="${contextPath!"" }/scripts/html5shiv.js" type="text/javascript"></script>
<script src="${contextPath!"" }/scripts/jquery.nicescroll.js" type="text/javascript"></script>
<script src="${contextPath!"" }/scripts/layer/layer.js" type="text/javascript"></script>
<script src="${contextPath!"" }/scripts/site.js" type="text/javascript"></script>
<#--表单验证-->
<script src="${contextPath!"" }/scripts/Validform_v5.3.2.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(document).ready(function(){ 	
    //初始化宽度、高度
    $("#main-container").height($(window).height()-76); 
	$("#iframe").height($(window).height()-140); 
	 
	$(".sidebar").height($(window).height()-99); 
    var thisHeight = $("#nav_list").height($(window).outerHeight()-135); 
	$(".submenu").height();
	$("#nav_list").children(".submenu").css("height",thisHeight);
	
    //当文档窗口发生改变时 触发  
    $(window).resize(function(){
		$("#main-container").height($(window).height()-76); 
		$("#iframe").height($(window).height()-140);
		$(".sidebar").height($(window).height()-99); 
		
		var thisHeight = $("#nav_list").height($(window).outerHeight()-135); 
		$(".submenu").height();
		$("#nav_list").children(".submenu").css("height",thisHeight);
  	});
});



</script>
