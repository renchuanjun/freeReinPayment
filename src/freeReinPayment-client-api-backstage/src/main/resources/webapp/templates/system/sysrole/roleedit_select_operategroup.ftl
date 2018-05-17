<#import "../../layout/layoutempty.ftl" as l/>
<@l.head>
</@l.head>
<@l.body>
<div class="panel-body" style="padding-bottom:0px;">
	<div class="panel panel-default" style="margin-bottom: 0px">
		<div class="panel-heading">查询操作组</div>
		<div class="margin  search_style">
			<ul class="search_content clearfix">
				<li><label class="l_f">操作组名称</label><input id="roleName" name="roleName" type="text"  class="text_add" placeholder="操作组名称"  style=" width:400px"/></li>
				<li style="width:90px;"><button type="button" class="btn_search" id="btn_query"><i class="icon-search"></i>查询</button></li>
			</ul>
	    </div>
		<div class="margin clearfix">
			<div class="stystems_style">
				<div class="tabbable">
					<div class="tab-content margin" style="min-height:340px">
						<#--内容-->
						<div id="home" class="tab-pane active">
                            <div id="toolbar1" class="btn-group">
                                <button id="btn_add" type="button" class="btn btn-primary " onclick="add();">
									<span class="glyphicon-plus disabled" aria-hidden="true"></span>确定
								</button>
                            </div>
                            <table id="tablelist" class="table-responsive"></table>
                        </div>
					</div>
				</div> 
			</div>
		</div>
	</div>
</div>
</@l.body>
<@l.js>
<script language="JavaScript" type="text/javascript">	
	var oTable;
    var oButtonInit;

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
                url: virtualPath+'/system/operategroup/listjson',         //请求后台的URL（*）
                method: 'post',                      //请求方式（*）
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                dataType: "json",
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                uniqueId: "groupId",                     //每一行的唯一标识，一般为主键列
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
                /*         height: 500, */                       //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                singleSelect : true,
                columns: [{
                    checkbox: true,
                    width:'5%'
                }, {
                    field: 'groupName',
                    sortable: true,
                    title: '操作组名称'
                }, {
                    field: 'createOn',
                    sortable: true,
                    title: '创建日期',
                    width:'20%'
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
                groupName: $("#txt_group_name").val(),
            };
            return temp;
        };
        return oTableInit;
    };


    //~~~~~~~~~~~~~~初始化页面结束~~~~~~~~~~~~~~~~~

    //搜索
    $("#btn_query").click(function () {
        $("#tablelist").bootstrapTable('destroy');//先要将table销毁，否则会保留上次加载的内容
        oTable.Init();
    })

function add(){
	var rows = $("#tablelist").bootstrapTable('getSelections');
	if (rows && rows.length > 0){
	    var val = '[{"groupId":"'+rows[0].groupId+'","groupName":"'+rows[0].groupName+'","createOn":"'+rows[0].createOn+'"}]';
	    getParentObject().returnSelectByOperateGroup(val);//返回值到父页面
	    closeWin();
	}
	else{
	    layer.alert("请选择操作组", {});
	}
}

</script>
</@l.js>