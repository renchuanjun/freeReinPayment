<#import "../../layout/layoutempty.ftl" as l/>
<@l.head>
</@l.head>
<@l.body>
<!--添加管理员-->
 <div id="add_administrator_style" class="add_menber" style="display:">
    <form action="${contextPath!"" }/system/user/saveorupdateuser" method="post" id="form_admin_add">
   		<!--token 表单验证时必须提交到后台-->
   		<input type="hidden" name="token" value="${tokenVal!""}"/>
   		<input type="hidden" name="userId" value="${form.userId!""}"/>
   		<input type="hidden" name="organId" value="${form.organId!""}"/>
		<div class="form-group">
			<label class="form-label"><span class="c-red">*</span>帐号：</label>
			<div class="formControls">
				<input type="hidden" id="oldUserName" value="${form.userName!""}"/>
				<input type="text" class="input-text" value="${form.userName!""}" placeholder="" id="userName" name="userName" datatype="userName" nullmsg="账号不能为空" errormsg="该帐号已经存在，请更换帐号!">
			</div>
			<div class="col-4"> <span class="Validform_checktip"></span></div>
		</div>
		<div class="form-group">
			<label class="form-label"><span class="c-red">*</span>姓名：</label>
			<div class="formControls">
				<input type="text" class="input-text" value="${form.name!""}" placeholder="" id="cName" name="cName" datatype="*" nullmsg="姓名不能为空" />
			</div>
			<div class="col-4"> <span class="Validform_checktip"></span></div>
		</div>
		<#if form??>
			<div class="form-group">
				<label class="form-label "><span class="c-red">*</span>性别：</label>
				<div class="formControls  skin-minimal">
				    <#if (1 == form.sex)>
				    	<label><input name="sex" value="1" type="radio" class="ace" checked="checked"><span class="lbl">男</span></label>&nbsp;&nbsp;
				    <#else>
				    	<label><input name="sex" value="1" type="radio" class="ace" ><span class="lbl">男</span></label>&nbsp;&nbsp;
				    </#if>
				    <#if (0 == form.sex)>
				    	<label><input name="sex" value="0" type="radio" class="ace" checked="checked"><span class="lbl">女</span></label>
				    <#else>
				    	<label><input name="sex" value="0" type="radio" class="ace" ><span class="lbl">女</span></label>
				    </#if>
				</div>
				<div class="col-4"> <span class="Validform_checktip"></span></div>
			</div>
		</#if>
		<div class="form-group">
			<label class="form-label "><span class="c-red">*</span>手机：</label>
			<div class="formControls ">
				<input type="text" class="input-text" value="${form.mobliePhone!""}" placeholder="" id="mobliePhone" name="mobliePhone" datatype="m" nullmsg="手机不能为空">
			</div>
			<div class="col-4">
				<span class="Validform_checktip"></span>
			</div>
		</div>
		<div class="form-group">
			<label class="form-label"><span class="c-red">*</span>邮箱：</label>
			<div class="formControls ">
				<input type="text" class="input-text" value="${form.email!""}" placeholder="@" name="email" id="email" datatype="e" nullmsg="请输入邮箱！">
			</div>
			<div class="col-4"> <span class="Validform_checktip"></span></div>
		</div>
		<#--
		<div class="form-group">
			<label class="form-label">角色：</label>
			<div class="formControls "> <span class="select-box" style="width:150px;">
				<select class="select" name="superUser" size="1">
					<option value="0">超级管理员</option>
					<option value="1">管理员</option>
				</select>
				</span> 
			</div>
		</div>
		-->
        <div style="text-align:center"> 
        	<a class="btn btn-primary radius"  id="add_Administrators">提交</a>
        </div>
       </form>
   </div>
</@l.body>
<@l.js>
<script type="text/javascript">
//表单验证提交
$("#form_admin_add").Validform({
	tiptype:2,
	btnSubmit:"#add_Administrators",
	ignoreHidden:true,
	datatype:{
		address: /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/,//自定义验证
		userName:function(gets,obj,curform,regxp){
		//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
				var bool = false;
				var oldUserName = $("#oldUserName").val();
				var userName = $("#userName").val();
				if(oldUserName != userName){
					if("" != gets){
						$.ajax({
				    		url:virtualPath+"/system/user/selectusername",
				    		type:"post",
				    		async: false,
				    		data:{
				    			"token":tokenVal,
				    			"userName":gets
				    		},
				    		success:function(date){
				    			if("success" == date){
				    				bool = true;
				    			}
				    		}
			    		})
					}
				}else{
					bool = true;
				};
				return bool;
		}
	},
	callback:function(data){
		$("#form_admin_add").ajaxSubmit({
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
//字数限制
/**
function checkLength(which) {
	var maxChars = 100; //
	if(which.value.length > maxChars){
	   layer.open({
	   icon:2,
	   title:'提示框',
	   content:'您输入的字数超过限制!',	
    });
		// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
		which.value = which.value.substring(0,maxChars);
		return false;
	}else{
		var curr = maxChars - which.value.length; //250 减去 当前输入的
		document.getElementById("sy").innerHTML = curr.toString();
		return true;
	}
};
**/

</script>
</@l.js>