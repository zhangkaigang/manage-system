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
    // 重新渲染表单
    renderForm : function(){
        layui.use('form', function(){
            // 高版本建议把括号去掉，有的低版本，需要加()
            var form = layui.form;
            // 也可以单单初始化下拉框form.render('select');
            form.render();
        });
    },
    // 封装ajax异步调用
    $Ajax : function(url, data){
        var returnDataTemp = {};
        returnDataTemp.successMsg = "";
        returnDataTemp.errMsg = "";
        $.ajax({
            type : 'post',
            url : url,
            async : false,
            data : data,
            dataType : 'json',
            success : function(data){
                returnDataTemp.returnData = data;
            },
            error: function (response) {
                var responseTextObj = JSON.parse(response.responseText)
                if(responseTextObj.errcode == 403) {
                    returnDataTemp.errCodeMsg = "您没有此操作权限，请确认";
                }
            }
        });
        return returnDataTemp;
    },
    // 处理结果
    dealResult : function(returnDataTemp){
        var returnData = returnDataTemp.returnData;
        if(returnData.success){
            commonFuns.successInfo(returnDataTemp);
            // 刷新页面
            window.location.reload();
        }else{
            commonFuns.errorInfo(returnDataTemp);
            return false;
        }
    },
    // 处理子页面结果
    dealChildResult : function(returnDataTemp){
        var returnData = returnDataTemp.returnData;
        if(returnData.success){
            commonFuns.childSuccessInfo(returnDataTemp);
            // 刷新父页面
            window.parent.location.reload();
        }else{
            commonFuns.errorInfo(returnDataTemp);
            return false;
        }
    },
    // 页面成功提示信息
    successInfo : function(returnDataTemp) {
        var successMsg = returnDataTemp.successMsg;
        layer.msg(successMsg ? successMsg : '操作成功', {
            offset: 't',
            time: 1, //1ms后自动关闭
            icon : 1
        });

    },
    // 子页面成功提示信息
    childSuccessInfo : function(returnDataTemp) {
        var successMsg = returnDataTemp.successMsg;
        var index = parent.layer.getFrameIndex(window.name);
        // 关闭弹出层
        parent.layer.close(index);
        parent.layer.msg(successMsg ? successMsg : '操作成功', {
            offset: 't',
            time: 1, //1ms后自动关闭
            icon : 1
        });
    },
    // 错误信息提示信息
    errorInfo : function(returnDataTemp) {
        var returnData = returnDataTemp.returnData;
        var errCodeMsg = returnDataTemp.errCodeMsg;
        var errMsg = returnDataTemp.errMsg;
        if(returnData.message){
            layer.msg(returnData.message, {icon: 5});
        }else{
            layer.msg(errCodeMsg ? errCodeMsg : errMsg ? errMsg : '操作失败', {icon: 5});
        }
    },
    // 取消关闭弹窗
    btnCancel : function(){
        // 获取当前弹出层的等级
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    },
    // 打开部门树
    openDeptTree : function(){
        layer.open({
            type: 2,
            title: '父级部门',
            area: ['400px', '420px'],
            content: contextPath + '/sys/dept/deptTreePage',
            end: function () {
                if (selectedNode && JSON.stringify(selectedNode) != '{}') {
                    $("#parentId").val(selectedNode.id);
                    $("#parentName").val(selectedNode.name);
                }
            }
        });
    }
};

// 定义公共变量
var contextPath = commonFuns.getRootPath ();

layui.use(['form'], function () {
    var form = layui.form;
    // 自定义表单规则，要放在form.on外面，千万不能放在提交步骤中，否则会不触发
    form.verify({
        // 数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]，或者直接自定义
        positiveInteger: function(value) {
            if(value != ""){
                if(!/^[1-9]\d*$/.test(value)){
                    return "只能填写大于0的整数";
                }
            }
        }
    });
});



