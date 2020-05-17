layui.use(['form'], function () {
    var form = layui.form;

    // 获取角色信息，回显
    var roleId = parent.param.roleId;
    var returnDataTemp = commonFuns.$Ajax(contextPath + '/sys/role/findByRoleId/' + roleId);
    var returnData = returnDataTemp.returnData;
    if(returnData.success) {
        var data = returnData.data;
        $("#roleForm input").each(function(index, element) {
            $("#"+element.id).val(data[element.name]);
        });
    } else {
        returnDataTemp.errMsg = "查询角色信息失败";
        commonFuns.errorInfo(returnDataTemp);
        return false;
    }

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        positionEditFuns[type] ? positionEditFuns[type].call(this) : '';
    });

    // 自定义函数
    var positionEditFuns = {
        // 保存
        btnSave : function () {
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                var formVal = data.field;
                var returnDataTemp = commonFuns.$Ajax( contextPath + "/sys/role/edit", formVal);
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