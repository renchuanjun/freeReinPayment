<#import "../../layout/layout.ftl" as l/>

<@l.head>
	<title>富勤金融管理系统</title>
</@l.head>

<@l.body>
<div class="main-content breadcrumbs ">
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="javascript:void(0)">系统管理</a>
		</li>
		<li class="active">
			<span class="Current_page iframeurl" name="" style="color: rgb(51, 51, 51); cursor: default;">角色管理</span>
		</li>
	</ul>
</div>
<div class="main-content">
	<div class="col-sm-12">
	   <div class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal" action="/list">
					<div class="search_style">
				      <ul class="search_content clearfix">
				       <li><label class="l_f">姓名</label><input id="cname" type="text"  class="text_add" placeholder="输入用户姓名"  style=" width:400px"/></li>
				       <li style="width:90px;"><button type="button" class="btn_search" id="btn_query"><i class="icon-search"></i>查询</button></li>
				      </ul>
				    </div>
				</form>
			</div>
		</div>
	    <div id="toolbar" class="btn-group">
			<button id="system_roleAdd" type="button" class="btn btn-primary disabled">
				<span class=" glyphicon-plus disabled" aria-hidden="true"></span>新增
			</button>
			<button id="system_roleEdit" type="button" class="btn btn-primary disabled">
				<span class="icon-pencil" aria-hidden="true"></span>修改
			</button>
			<button id="system_roleDel" type="button" class="btn btn-primary disabled">
				<i class="icon-remove"></i>删除
			</button>
		</div>
		<table id="tablelist"></table>
	</div>
</div>

</@l.body>
<@l.js>
<script src="${contextPath!"" }/scripts/laydate/laydate.js" type="text/javascript"></script>
<script type="text/javascript">
//时间控件


$(function($){
    //1.初始化Table
    oTable = new TableInit();
    oTable.Init();
});

var TableInit = function () {
var oTableInit = new Object();
//初始化Table
oTableInit.Init = function () {
    $('#tablelist').bootstrapTable({
        url: virtualPath+'/system/role/listjson',         //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        uniqueId: "roleId",                 //每一行的唯一标识，一般为主键列
        sortOrder: "desc",                   //排序方式
        sortName:"createOn",
        queryParams: oTableInit.queryParams,//传递参数（*）
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
        /*         height: 500, */           //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                   //是否显示父子表
        columns: [{
            checkbox: true,
            width:'5%'
        }, {
            field: 'roleName',
            sortable: true,
            title: '角色名称'
        }, {
            field: 'createOn',
            sortable: true,
            title: '创建日期',
            width:'20%'
        }, {
            field: 'isEnable',
            title: '是否启用',
            formatter:function(value,row,index){
                return value=="1"?"启用":"禁用";
            },
            width:'5%'
        },]
    });
};

//得到查询的参数
oTableInit.queryParams = function (params) {
    var temp = {
        token: tokenVal,
        pageSize: params.limit,   //页面大小
        pageIndex: params.offset,  //页码
        order : params.order,   //排序字段
        sort : params.sort,     //asc正序，desc反序
        //以下是搜索字段
        cName: $("#cname").val()
    };
    return temp;
	};
	return oTableInit;
};


//搜索
$("#btn_query").click(function () {
    $("#hidden_organ_id").val("");//清空左侧选中的值
    $("#tablelist").bootstrapTable('destroy');//先要将table销毁，否则会保留上次加载的内容
    oTable.Init();
})

//添加用户页面
function add() {
   var url = virtualPath+"/system/role/create";
   openTwoWin('80%','80%',url,'编辑角色');
}

//修改用户页面
function edit() {
	var rows = $("#tablelist").bootstrapTable('getSelections');
    if (rows.length==1){
        var url = virtualPath+'/system/role/edit/'+rows[0].roleId;
   		openTwoWin('80%','80%',url,'编辑角色');
    }
    else{
        layer.alert("请选中一行数据", {});
    }
}

//删除
function del(){
	var rows = $("#tablelist").bootstrapTable('getSelections');
	if (rows && rows.length > 0){
	    layer.confirm("确定删除吗?","信息",function () {
	        var ids = [];//获取选中的Id的值
	        for(var i=0; i<rows.length; i++){
	            ids.push(rows[i].roleId);
	        }
	        var items = ids.join('\_');
	        var url = virtualPath+"/system/role/delete";
	        var param='{"token":"'+tokenVal+'","ids":"'+items+'"}';
	        var jsonValue = JSON.parse(param);
	        submitAjaxByAsync(url,jsonValue,function(val){
	            if("success" == val){
	                layer.alert("删除成功", {});
	                $("#tablelist").bootstrapTable("refresh",{
	                    query:{
	                        token: tokenVal,
	                    }
	                });
	            }
	            else if ("fail" == val){
	                layer.alert("失败", {});
	            }
	            else{
	                layer.alert(val, {});
	            }
	        });
	    },null);
	}
	else{
	    layer.alert("请选择删除的数据", {});
	}
}


/***
 * 子页面返回值给父页面
 * @param val
 */
function returnAdd(val) {
    if("success" == val){
        $("#tablelist").bootstrapTable("refresh",{
            query:{
                token: tokenVal,
            }
        });
    }
}
</script>
</@l.js>