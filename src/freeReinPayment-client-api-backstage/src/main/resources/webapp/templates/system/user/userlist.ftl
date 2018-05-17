<#import "../../layout/layout.ftl" as l/>

<@l.head>
	<title>富勤金融管理系统</title>
</@l.head>
<link rel="stylesheet" href="${contextPath!"" }/styles/default/jquery.tree.css"/>
<@l.body>
<div class="main-content breadcrumbs ">
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="javascript:void(0)">系统管理</a>
		</li>
		<li class="active">
			<span class="Current_page iframeurl" name="" style="color: rgb(51, 51, 51); cursor: default;">人员管理</span>
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
				       <li><label class="l_f">添加时间</label><input class="inline laydate-icon" id="start"  style=" margin-left:10px;"></li>
				       <li style="width:90px;"><button type="button" class="btn_search" id="btn_query"><i class="icon-search"></i>查询</button></li>
				      </ul>
				    </div>
				    <!--初始化根节点的id为1-->
                    <input type="hidden" id="hidden_organ_id" name="organId" value="1"/>
				</form>
			</div>
		</div>
		<div class="col-sm-2" id="tree_father">
			<div class="side_list">
				<div class="widget-header header-color-green2">
					<h4 class="lighter smaller">组织机构列表</h4>
				</div>
			</div>
			<div class="widget-main padding-8" id="tree_child" style="overflow-y:auto">
				<div id="tree"></div>
			</div>
		</div>
		<div class="col-sm-10">
		    <div id="toolbar" class="btn-group">
				<button id="system_userAdd" type="button" class="btn btn-primary disabled">
					<span class="glyphicon-plus disabled" aria-hidden="true"></span>新增
				</button>
				<button id="system_userEdit" type="button" class="btn btn-primary disabled">
					<span class="icon-pencil" aria-hidden="true"></span>修改
				</button>
				<button id="system_userDel" type="button" class="btn btn-primary disabled">
					<i class="icon-remove"></i>删除
				</button>
			</div>
			<table id="tablelist"></table>
		</div>
	</div>
</div>

</@l.body>
<@l.js>
<!--时间控件-->
<script src="${contextPath!"" }/scripts/laydate/laydate.js" type="text/javascript"></script>
<script src="${contextPath!"" }/scripts/jquery.tree.js" type="text/javascript"></script>
<script type="text/javascript">
//时间控件
laydate({
    elem: '#start',//ID
    event: 'focus' //样式
})
var oTable;
$(function($){
    //1.初始化Table
    oTable = new TableInit();
    oTable.Init();
    
    var setting = {
        url: virtualPath+"/system/organ/loadorgantree",
        theme: "bbit-tree-lines", //bbit-tree-lines ,bbit-tree-no-lines,bbit-tree-arrows
        theme: "bbit-tree-arrows", //bbit-tree-lines ,bbit-tree-no-lines,bbit-tree-arrows
        //showcheck: true,
        onnodeclick: function (item) {
            $("#hidden_organ_id").val(item.id);
            //取消根节点默认选中的样式
            if(item.id!="1")
                $("div[id='tree_1']").attr("class","bbit-tree-node-el bbit-tree-node-leaf");
            //点击组织机构树，需要清空组织名称搜索条件
            $("#txt_name").val("");
            //加载列表
            $("#tablelist").bootstrapTable('destroy');//先要将table销毁，否则会保留上次加载的内容
            oTable.Init();
        }
    };
    $("#tree").treeview(setting);
    //初始默认根节点选中
    $("div[id='tree_1']").attr("class","bbit-tree-node-el bbit-tree-node-expanded bbit-tree-selected");
});

var TableInit = function () {
var oTableInit = new Object();
//初始化Table
oTableInit.Init = function () {
    $('#tablelist').bootstrapTable({
        url: virtualPath+'/system/user/listjson',         //请求后台的URL（*）
        method: 'post',                      //请求方式（*）
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        uniqueId: "userId",                 //每一行的唯一标识，一般为主键列
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
            field: 'userName',
            sortable: true,
            title: '帐号'
        }, {
            field: 'name',
            sortable: true,
            title: '姓名'
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
        cName: $("#cname").val(),
        createOn: $("#start").val(),
        organId: $("#hidden_organ_id").val()
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
  var s = $("#tree").getTCT();
    var organId = 1;
    if(s){
        organId = s.id;
    }
   var url = virtualPath+'/system/user/adduser/'+organId;
   openTwoWin('30%','60%',url,'添加用户');
}

//修改用户页面
function edit() {
	var rows = $("#tablelist").bootstrapTable('getSelections');
	console.log(rows);
    if (rows.length==1){
        var url = virtualPath+'/system/user/edituser/'+rows[0].userId;
   		openTwoWin('30%','60%',url,'添加用户');
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
	            ids.push(rows[i].userId);
	        }
	        var items = ids.join('\_');
	        var url = virtualPath+"/system/user/delete";
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