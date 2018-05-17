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

$(function () {
	//1.初始化Table
    var oTable = new TableInit();
    oTable.Initoperategroup();
    oTable.Inituser();
    
    //操作组添加
    $("#btn_operategroup").click(function () {
        var url = virtualPath+'/system/role/roleselectoperategroup';
        openThreeOrMoreWin('75%','70%',url,'操作组选择');
       
    });
    //操作组删除
    $("#btn_operategroup_del").click(function () {
        layer.confirm("确定删除操作组吗?","信息",function (index) {
            returnSelectByOperateGroup("[]");
            layer.close(index);
        },null);

    });
    
    //人员选择
    $("#btn_user").click(function () {
        var url = virtualPath+'/system/role/roleselectuser';
        openThreeOrMoreWin('75%','70%',url,'人员选择');
    });
    //删除人员
    $("#btn_user_del").click(function () {
        var rows = $("#usertablelist").bootstrapTable('getSelections');
        var jsons = [];
        if (rows && rows.length > 0) {
            layer.confirm("确定删除选中的人员吗?", "信息", function (index) {
                var a = $("#userData").val();
                var jsons = JSON.parse(a);
                var newjsons = [];
                $.each(jsons, function(idx, obj) {
                    var flag = true;
                    for(var i=0; i<rows.length; i++){
                        if (obj.userId == rows[i].userId){
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        newjsons.push(obj);
                    }
                });
                var str = JSON.stringify(newjsons);
                //alert(str);
                $("#userData").val(str);
                $("#usertablelist").bootstrapTable("refresh",{
                    query:{
                        token: tokenVal,
                    }
                });
                layer.close(index);
            }, null);
        }
        else{
            layer.alert("请选择删除的数据", {});
        }
    });
})

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Initoperategroup = function () {
        $('#operategrouptablelist').bootstrapTable({
            url: virtualPath+'/system/operategroup/listjsontorole?groupId=${form.groupId!""}',         //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            contentType:"application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            toolbar: '#toolbar1',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            uniqueId: "groupId",                     //每一行的唯一标识，一般为主键列
            sortOrder: "desc",                   //排序方式
            sortName:"createOn",
            queryParams: oTableInit.queryParamsOperateGroup,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            /*         height: 500, */                       //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'groupName',
                sortable: true,
                title: '操作组名称',
                width:'70%',
            }, {
                field: 'createOn',
                sortable: true,
                title: '创建日期',
                width:'30%',
            }],
            onLoadSuccess: function (data) {
                var rows = data.rows;
                var jsons = [];
                $.each(rows, function(idx, obj) {
                    var aa = {"groupId":obj.groupId,"groupName":obj.groupName,"createOn":obj.createOn};
                    jsons.push(aa);
                });
                var val = JSON.stringify(jsons);
                $("#operateGroupData").val(val);
            }
        });
    };


    //初始化Table
    oTableInit.Inituser = function () {
        $('#usertablelist').bootstrapTable({
            url: virtualPath+'/system/userrole/listjsontorole?roleId=${form.roleId!""}',         //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            contentType:"application/x-www-form-urlencoded; charset=UTF-8",
            dataType: "json",
            toolbar: '#toolbar3',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: false,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            uniqueId: "userId",                     //每一行的唯一标识，一般为主键列
            sortOrder: "desc",                   //排序方式
            sortName:"createOn",
            queryParams: oTableInit.queryParamsUser,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            /*         height: 500, */                       //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true,
                width:'5%'
            },{
                field: 'userName',
                sortable: true,
                title: '帐号',
                width:'25%',
            },{
                field: 'name',
                sortable: true,
                title: '姓名',
                width:'25%',
            },
            {
                field: 'organName',
                sortable: true,
                title: '组织机构名称',
                width:'25%',
            },
            {
            field: 'createOn',
            sortable: true,
            title: '创建日期',
            width:'20%',
            }],
            onLoadSuccess: function (data) {
                var rows = data.rows;
                var jsons = [];
                $.each(rows, function(idx, obj) {

                    var aa = {"userId":obj.userId,"name":obj.name,"userName":obj.userName,"organName":obj.organName,"createOn":obj.createOn};
                    jsons.push(aa);
                });
                var val = JSON.stringify(jsons);
                $("#userData").val(val);
            }
        });
    };

    //得到查询的参数
    oTableInit.queryParamsOperateGroup = function (params) {
        var temp = {
            token: tokenVal,
            pageSize: params.limit,   //页面大小
            pageIndex: params.offset,  //页码
            order : params.order,   //排序字段
            sort : params.sort,     //asc正序，desc反序
            operateGroupData:$("#operateGroupData").val(),

        };
        return temp;
    };

    /*
    oTableInit.queryParamsDataPermission = function (params) {
        var temp = {
            token: tokenVal,
            pageSize: params.limit,   //页面大小
            pageIndex: params.offset,  //页码
            order : params.order,   //排序字段
            sort : params.sort,     //asc正序，desc反序
            dataPermissionData:$("#dataPermissionData").val(),

        };
        return temp;
    };
    */

    oTableInit.queryParamsUser = function (params) {
        var temp = {
            token: tokenVal,
            pageSize: params.limit,   //页面大小
            pageIndex: params.offset,  //页码
            order : params.order,   //排序字段
            sort : params.sort,     //asc正序，desc反序
            userData:$("#userData").val(),

        };
        return temp;
    };

    return oTableInit;
};

/***
 * 子页面返回值给父页面
 * @param val
 */
function returnSelectByOperateGroup(val) {
	//alert(val);
	console.log();
    $("#operateGroupData").val(val);
    $("#operategrouptablelist").bootstrapTable("refresh",{
        query:{
            token: tokenVal
        }
    });

}


/***
 * $.each的跳出循环
 *  break           用return false
    continue      用return ture
 */
function returnSelectByUser(val) {
    /***
     * 取出旧数据，与回传的新数据进行比较
     * */
    var a = $("#userData").val();
    var jsons = JSON.parse(a);
    var newjsons = JSON.parse(val);
    $.each(newjsons, function(idx, newObj) {
        var flag = true;
        $.each(jsons,function (idx,oldObj) {
            if (newObj.userId == oldObj.userId){
                flag = false;
                return false;
            }
        })
        if (flag){
            jsons.push(newObj);
        }


    });
    var str = JSON.stringify(jsons);

    $("#userData").val(str);
    $("#usertablelist").bootstrapTable("refresh",{
        query:{
            token: tokenVal,
        }
    });

}

</script>
</@l.js>