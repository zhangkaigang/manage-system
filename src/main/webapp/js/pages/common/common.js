// 定义公共方法
var commonFuns = {
    // 获取项目根路径
    getRootPath : function () {
        // 获取当前网址，eg：http://localhost:8888/manage-system/user
        var curWwwPath = window.document.location.href;
        // 获取主机地址之后的目录，eg：manage-system/user
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        // 获取主机地址，如： http://localhost:8888
        var localhostPaht = curWwwPath.substring(0, pos);
        // 获取带"/"的项目名，如：/manage-system
        var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        return (localhostPaht + projectName);
    },
    // 封装ajax异步调用
    $Ajax : function(url,data){
        var returnDataTemp = {};
        $.ajax({
            type : 'post',
            url : url,
            async : false,
            data : data,
            dataType : 'json',
            success : function(data){
                returnDataTemp.data = data;
            },
            error: function (response) {
                var responseTextObj = JSON.parse(response.responseText)
                if(responseTextObj.errcode == 403) {
                    returnDataTemp.errMsg = "您没有此操作权限，请确认";
                }
            }
        });
        return returnDataTemp;
    },
};

// 定义公共变量
var contextPath = commonFuns.getRootPath ();



