<#import "../../layout/layoutempty.ftl" as l/>
<@l.head>
</@l.head>
<@l.body>
<div class="panel-body" style="padding-bottom:0px;">
	<div class="panel panel-default" style="margin-bottom: 0px">
		<div class="panel-heading">添加组织机构</div>
		<div class="margin  search_style">
			<div id="add_administrator_style" class="add_menber" style="display:">
			    <form action="${contextPath!"" }/system/organ/submit" method="post" id="form1" >
			   		<!--token 表单验证时必须提交到后台-->
			   		<input type="hidden" name="token" value="${tokenVal!""}"/>
			        <input type="hidden" name="organId" value="${form.organId!""}"/>
			        <input type="hidden" name="parentId" value="${form.parentId!""}"/>
					<div class="form-group">
						<label class="form-label"><span class="c-red">*</span>组织名称：</label>
						<div class="formControls">
							<input type="text" class="input-text" value="${form.organName!""}" placeholder="" id="organName" name="organName" datatype="*" nullmsg="组织名称不能为空">
						</div>
						<div class="col-4"> <span class="Validform_checktip"></span></div>
					</div>
					<div class="form-group">
						<label class="form-label"><span class="c-red">*</span>组织代码：</label>
						<div class="formControls">
							<input type="text" class="input-text" value="${form.organCode!""}" placeholder="" id="organCode" name="organCode" datatype="*" nullmsg="组织代码" />
						</div>
						<div class="col-4"> <span class="Validform_checktip"></span></div>
					</div>
					<div class="form-group">
						<label class="form-label"><span class="c-red">*</span>组织层级：</label>
						<div class="formControls">
						<input type="text" placeholder="组织层级" name="organLevel" autocomplete="off" value="${form.organLevel!""}" class="input-text number" datatype="n" nullmsg="组织层级不能为空" errormsg="请填写数字！"/>
						</div>
						<div class="col-4"> <span class="Validform_checktip"></span></div>
					</div>
					<div class="form-group">
						<label class="form-label "><span class="c-red">*</span>组织排序：</label>
						<div class="formControls ">
							<input type="text" class="input-text number"  value="${form.orderBy!""}"  placeholder="" id="orderBy" name="orderBy" datatype="n" nullmsg="组织排序不能为空" errormsg="请填写数字！">
						</div>
						<div class="col-4">
							<span class="Validform_checktip"></span>
						</div>
					</div>
					<div style="text-align:center"> 
			        	<a class="btn btn-primary radius" id="add_submit">提交</a>
			        </div>
				</form>
			</div>
		</div>
	</div>
</div>
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