//框架级别的js文件,放一些公用的方法
var aa;
//解决异步提交注入的检测
$(document).ajaxStart(function () {
    //alert('ajaxStart');
    //aa = layer.load(2);//加载等待遮罩
}).ajaxStop(function () {
    //alert('ajaxStop');
    layer.close(aa);//关闭等待遮罩
}).ajaxError(function (e, xhr, opt) {
    alert("Error requesting " + opt.url + ": " + xhr.status + " " + xhr.statusText);
    if (xhr.status == 998) {
        //window.location.href = virtualPath + '/Error/Error?type=SessionTimeOut';
        var url = window.location.href;
        window.location.href = virtualPath + '/login?returnUrl='+encodeURIComponent(url);
        //cancelLoading();
        //jAlert('Session已经过期，请重新登录', "提示", function (r) { if (r) { window.location.href = virtualPath + '/Login/Login'; } });
    }
    else if (xhr.status == 999) {
        window.location.href = virtualPath + '/errorsql';
    }
    else if (xhr.status == 997) {
        window.location.href = virtualPath + '/errorchar';
    }
    else if (xhr.status == 996) {
        window.location.href = virtualPath + '/errorpermission';
    }
    else if (xhr.status == 995) {
        window.location.href = virtualPath + '/errorcsrf';
    }
    else {
        window.location.href = virtualPath + '/error';
    }

});

//ajax异步提交数据
function submitAjaxByAsync(url,jsonValue,fun){
    $.ajax({
        url: url,
        type: "post",
        data: jsonValue,
        dataType: 'text',
        async: true,
        success: fun
    });
}

/**开启二级页面窗口*/
function openTwoWin(width,height,url,title) {
    layer.open({
    	title :("" != title && "undefined" != typeof(title)) ? title : '信息',
        type: 2,
        area: [width, height],
        fixed: false, //不固定
        maxmin: true,
        content: url
    });
}

/**开启三级及以上的页面窗口*/
function openThreeOrMoreWin(width,height,url,title) {
    if (url.indexOf("?")>0){
        url = url+'&frameindex='+frameindex;
    }
    else{
        url = url+'?frameindex='+frameindex;
    }
    parent.layer.open({
    	title :("" != title && "undefined" != typeof(title)) ? title : '信息',
        type: 2,
        area: [width, height],
        fixed: false, //不固定
        maxmin: true,
        content: url
    });
}
/***
 * 关闭二级以上窗口
 */
function closeWin() {
    parent.layer.close(frameindex);//关闭当前窗口
}
/****
 * 二级及以上页面获取父对象
 * @returns {*}
 */
function getParentObject() {
    var pindex = getQueryString("frameindex");
    if (pindex){
        return window.parent.frames["layui-layer-iframe"+pindex];
    }
    else{
        return parent;
    }

}

//初始化只能是数字的文本框
$(function () {

    //只能大于0的数字
    $('.number').keyup(function(){  //keyup事件处理
        $(this).val($(this).val().replace(/\D|^0/g,''));
    }).bind("paste",function(){  //CTR+V事件处理
        $(this).val($(this).val().replace(/\D|^0/g,''));
    }).css("ime-mode", "disabled");  //CSS设置输入法不可用

    //只能输入0-9的数字和小数点。
    $(".rnumber").keyup(function(){
        $(this).val($(this).val().replace(/[^0-9.]/g,''));
    }).bind("paste",function(){  //CTR+V事件处理
        $(this).val($(this).val().replace(/[^0-9.]/g,''));
    }).css("ime-mode", "disabled"); //CSS设置输入法不可用
})