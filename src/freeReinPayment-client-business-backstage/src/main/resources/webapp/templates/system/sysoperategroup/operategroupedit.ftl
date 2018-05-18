<#import "../../layout/layoutempty.ftl" as l/>
<@l.head>
</@l.head>
<link rel="stylesheet" href="${contextPath!"" }/styles/default/jquery.tree.css"/>
<@l.body>
<div  class="panel-body">
<div  id="tree_father" style="width:26%;float:left;margin:0px 10px;overflow-y: auto;max-height:485px;">
	<div class="side_list">
		<div class="widget-header header-color-green2">
			<h4 class="lighter smaller">组织机构列表</h4>
		</div>
	</div>
	<div class="widget-main padding-8" id="tree_child" >
		<div id="tree"></div>
	</div>
</div>
<div  style="padding-bottom:0px;width:70%;float:right">
	<div class="panel panel-default" style="margin-bottom: 0px">
		<div class="panel-heading">添加组织机构</div>
		<div class="margin  search_style">
			<div id="add_administrator_style" class="add_menber" style="display:">
			    <form action="${contextPath!"" }/system/operategroup/submit" method="post" id="form1" >
			   		<!--token 表单验证时必须提交到后台-->
			   		<input type="hidden" name="token" value="${tokenVal!""}"/>
                    <input type="hidden" name="groupId" value="${form.groupId!""}"/>
                    <input type="hidden" id="permissionIds" name="permissionIds"/>
                    <input type="hidden" id="hkPermissionIds" name="hkPermissionIds"/>
					<div class="form-group">
						<label class="form-label"><span class="c-red">*</span>组织名称：</label>
						<div class="formControls">
							<input type="text" class="input-text"  placeholder="组织名称" id="groupName" name="groupName" value="${form.groupName!""}" datatype="*" nullmsg="组织名称不能为空">
						</div>
						<div class="col-4"> <span class="Validform_checktip"></span></div>
					</div>
					<div class="form-group">
						<label class="form-label"><span class="c-red">*</span>组织代码：</label>
						<div class="formControls">
							<input type="text" class="input-text" placeholder="" id="groupDescription" name="groupDescription" value="${form.groupDescription!""}" datatype="*" nullmsg="组织代码" />
						</div>
						<div class="col-4"> <span class="Validform_checktip"></span></div>
					</div>
					<div style="text-align:center"> 
			        	<a class="btn btn-primary radius" id="add_submit">提交</a>
			        </div>
				</form>
			</div>
		</div>
	</div>
</div>
</div>
</@l.body>
<@l.js>
<script src="${contextPath!"" }/scripts/jquery.tree.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {

	var setting = {
        url: virtualPath+"/system/permission/loadpermissiontree",
        theme: "bbit-tree-lines", //bbit-tree-lines ,bbit-tree-no-lines,bbit-tree-arrows
        theme: "bbit-tree-arrows", //bbit-tree-lines ,bbit-tree-no-lines,bbit-tree-arrows
        showcheck: true,
        paramval:{"token":tokenVal,"groupId":"${form.groupId!""}"}
    };
    $("#tree").treeview(setting);
    //初始默认根节点选中
    $("div[id='tree_1']").attr("class","bbit-tree-node-el bbit-tree-node-expanded bbit-tree-selected");
    
    
	
})


//表单验证提交
$("#form1").Validform({
	tiptype:2,
	btnSubmit:"#add_submit",
	ignoreHidden:true,
	callback:function(data){
		var ck = $("#tree").getTSVs();
        var ckHalf = $("#tree").getTSVsByHalfCk();
        if (ck){
            var ckStr  = ck.toString().replace(/,/g, "_");
            $("#permissionIds").val(ckStr);
        }
        if (ckHalf){
            var ckHalfStr = ckHalf.toString().replace(/,/g, "_");
            $("#hkPermissionIds").val(ckHalfStr);
        }
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