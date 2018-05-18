<div class="main-content clearfix">
<div class="top clearfix">
	<span class="f-right mg-r15">
		<i class="icon-admin"></i>
		你好，<span id="userName_"></span>
		<i class="icon-esc"></i>
	</span>
    <script type="text/javascript">
        $(function(){
            $.ajax({
                url:"${contextPath!"" }/sysuername",
                type:"post",
                data:{
                    "token":tokenVal
                },
                success:function(data){
                    $("#userName_").empty()
                    $("#userName_").append(data);
                }
            });
        })
    </script>
</div>