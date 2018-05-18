<div class="sidebar" id="sidebar">
	
</div>
<#include "menutemplate.ftl" encoding="utf8">
<script type="text/javascript">
var currentUrl=window.location.pathname;
$(document).ready(function(){
menu();
});
function menu(){
	$.ajax({
		url:virtualPath+"/menu",
		dataType: "json",
		type:"post",
		async: false,
		data:{
			"token":tokenVal,
		},
		success:function(date){
			$("#sidebar").empty();
			var html = baidu.template('menutemplate', date);
			$("#sidebar").append(html);
			$.a = $("li[name='"+currentUrl+"']");
			$.a.addClass('active');
			$.a.parent().parent('li').addClass("open");
			$.a.parent().css("display","block");
			if($.a){
				$("li[name='/home']").addClass('active');
			}
			$("#menu_style").niceScroll({  
			    cursorcolor:"#888888",  
			    cursoropacitymax:1,  
			 	touchbehavior:false,  
			    cursorwidth:"5px",  
			    cursorborder:"0",  
			    cursorborderradius:"5px"  
		    }); 
		}
	})
  }
  /*********************点击事件*********************/
/**
**/
$( document).ready(function(){
  /**
  $('#nav_list,.link_cz').find('li.home').on('click',function(){
	$('#nav_list,.link_cz').find('li.home').removeClass('active');
	$(this).addClass('active');
	
  });	
  **/
											
//时间设置
  function currentTime(){ 
    var d=new Date(),str=''; 
    str+=d.getFullYear()+'年'; 
    str+=d.getMonth() + 1+'月'; 
    str+=d.getDate()+'日'; 
    str+=d.getHours()+'时'; 
    str+=d.getMinutes()+'分'; 
    str+= d.getSeconds()+'秒'; 
    return str; 
} 
setInterval(function(){$('#time').html(currentTime)},1000); 
});

</script>
