//框架级别的js文件,放一些公用的方法

//解决异步提交注入的检测
$(document).ajaxStart(function () {
    //alert('ajaxStart');
}).ajaxStop(function () {
    //alert('ajaxStop');
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