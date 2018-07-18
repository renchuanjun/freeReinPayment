<#import "../../layout/layoutempty.ftl" as l/>
<@l.head>
</@l.head>
<@l.body>
<form class="cmxform form-horizontal " id="form1" method="post" action="${contextPath!"" }/system/role/submit">
<div class="panel-body" style="padding-bottom:0px;">
	<div class="panel panel-default" style="margin-bottom: 0px">
		<div class="panel-heading">角色编辑</div>
		<div class="margin  search_style">
	      <ul class="search_content clearfix">
	      	<div class="form-group"  style="height:35px">
				<label class="form-label">色名称：</label>
				<div class="formControls">
					<input type="text" class="input-text" value="${form.roleName!""}" placeholder="角色名称" id="roleName" name="roleName" datatype="*" nullmsg="账号不能为空">
				</div>
				<div class="col-4 formControls"> <span class="Validform_checktip" style="margin-top;0px"></span></div>
			</div>
			<div class="form-group"  style="height:35px">
				<label class="form-label">描述：</label>
				<div class="formControls">
					<input type="text" class="input-text" value="${form.comments!""}" placeholder="描述" id="comments" name="comments" datatype="*" nullmsg="描述不能为空" />
				</div>
				<div class="col-4 formControls"> <span class="Validform_checktip" style="margin-top;0px"></span></div>
			</div>
	      	
	      	<input type="hidden" name="token" value="${tokenVal!""}"/>
            <input type="hidden" name="roleId" value="${form.roleId!""}"/>
            <input type="hidden" name="groupId" value="${form.groupId!""}"/>
            <input type="hidden" id="operateGroupData" name="operateGroupData" value="${form.operateGroupData!""}"/>
            <input type="hidden" id="dataPermissionData" name="dataPermissionData" value="${form.dataPermissionData!""}"/>
            <input type="hidden" id="userData" name="userData" value="${form.userData!""}"/>
	      </ul>
	    </div>
		<div class="margin clearfix">
			<div class="stystems_style">
				<div class="tabbable">
					<ul class="nav nav-tabs" id="myTab">
						<li class="active">
							<a data-toggle="tab" href="#home"><i class="green fa fa-home bigger-110"></i>&nbsp;操作组</a></li>
						<li class="">
							<a data-toggle="tab" href="#profile">人员</a></li>
						<#--
						<li class="">
							<a data-toggle="tab" data-toggle="dropdown" class="dropdown-toggle" href="#dropdown">数据权限组</a>
						</li>
						-->
					</ul>
					<div class="tab-content margin" style="min-height:370px">
						<#--内容-->
						<div id="home" class="tab-pane active">
                            <div id="toolbar1" class="btn-group">
                                <button id="btn_operategroup" type="button" class="btn btn-primary ">
									<span class="glyphicon-plus disabled" aria-hidden="true"></span>新增
								</button>
								<button id="btn_operategroup_del" type="button" class="btn btn-primary ">
									<i class="icon-remove"></i>删除
								</button>
                            </div>
                            <table id="operategrouptablelist" class="table-responsive"></table>
                        </div>
                        <div id="profile" class="tab-pane">
	                        <div id="toolbar3" class="btn-group">
	                            <button id="btn_user" type="button" class="btn btn-primary ">
									<span class="glyphicon-plus disabled" aria-hidden="true"></span>新增
								</button>
								<button id="btn_user_del" type="button" class="btn btn-primary ">
									<i class="icon-remove"></i>删除
								</button>
	                        </div>
	                        <table id="usertablelist" class="table-responsive"></table>
	                    </div>
					</div>
				</div> 
			</div>
		</div>
	</div>
	<div style="text-align:center"> 
    	<a class="btn btn-primary radius"  id="add_submit">提交</a>
    </div>
</div>
</form>
</@l.body>
<@l.js>
<script type="text/javascript">
//表单验证提交
$("#form1").Validform({
	tiptype:2,
	btnSubmit:"#add_submit",
	ignoreHidden:true,
	callback:function(data){
		$("#form1").ajaxSubmit({
            success:function(val){
                if("success" == val){
                	getParentObject().returnAdd(val);//返回值到父页面
                    closeWin();
                }else{

                }
            }
        })
		return false;
	}
});

</script>
</@l.js>