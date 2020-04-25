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
    $Ajax : function(url, data){
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
    // 处理结果
    dealResult : function(returnData){
        var data = returnData.data;
        if(data.success){
            // 刷新页面
            window.location.reload();
            layer.msg('操作成功', {
                offset: 't',
                time: 1000, //1s后自动关闭
                icon : 1
            });
        }else{
            if(data.messgae){
                layer.msg(data.messgae, {icon: 5});
            }else{
                layer.msg(data.errMsg ? data.errMsg : '操作失败', {icon: 5});
            }
            return false;
        }
    },
    // 处理子页面结果
    dealChildResult : function(returnData){
        var data = returnData.data;
        if(data.success){
            // 刷新父页面
            window.parent.location.reload();
            var index = parent.layer.getFrameIndex(window.name);
            // 关闭弹出层
            parent.layer.close(index);
            layer.msg('操作成功', {
                offset: 't',
                time: 1000, //1s后自动关闭
                icon : 1
            });
        }else{
            if(data.messgae){
                layer.msg(data.messgae, {icon: 5});
            }else{
                layer.msg(data.errMsg ? data.errMsg : '操作失败', {icon: 5});
            }
            return false;
        }
    },
    btnCancel : function(){
        // 获取当前弹出层的等级
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }
};

// 定义公共变量
var contextPath = commonFuns.getRootPath ();

layui.use(['form'], function () {
    var form = layui.form;
    // 自定义表单规则，要放在form.on外面，千万不能放在提交步骤中，否则会不触发
    form.verify({
        // 数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        int: [
            /[1-9]+[0-9]*/
            , '请填入大于0的整数'
        ]
    });
});



