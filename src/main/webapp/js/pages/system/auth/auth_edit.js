var selectedNode = {};
layui.use(['layer', 'form'], function () {
    var layer = layui.layer;
    var form = layui.form;
    // 获取信息，回显
    var authId = parent.param.authId;
    var returnDataTemp = commonFuns.$Ajax(contextPath + '/sys/auth/findByAuthId/' + authId);
    var returnData = returnDataTemp.returnData;
    if(returnData.success) {
        var data = returnData.data;
        $("#authForm input").each(function(index, element) {
            $("#"+element.id).val(data[element.name]);
        });
        // radio回显，且不可修改
        var authType = data.authType;
        $("#authType" + authType).attr("checked", "checked");
        $("input[name='authType']").attr("disabled", "disabled");
    } else {
        returnDataTemp.errMsg = "查询权限信息失败";
        commonFuns.errorInfo(returnDataTemp);
        return false;
    }


    // 点击上级部门时
    $('#parentName').click(function () {
        commonFuns.openAuthTree();
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        authEditFuns[type] ? authEditFuns[type].call(this) : '';
    });

    // 自定义函数
    var authEditFuns = {
        // 保存
        btnSave : function () {
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                var formVal = data.field;
                if(formVal.authId == formVal.parentId) {
                    layer.msg( '父级权限不能为自身', {icon: 5});
                    return false;
                }
                var returnDataTemp = commonFuns.$Ajax( contextPath + "/sys/auth/edit", formVal);
                commonFuns.dealChildResult(returnDataTemp);
                return false;
            });
        },
        // 取消
        btnCancel : function () {
            commonFuns.btnCancel();
        }
    };

    commonFuns.renderForm();
});