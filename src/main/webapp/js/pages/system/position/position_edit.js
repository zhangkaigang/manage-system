var layer, form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form;

    // 获取职位信息，回显
    var positionId = parent.param.positionId;
    var returnDataTemp = commonFuns.$Ajax(contextPath + '/sys/position/findByPositionId/' + positionId);
    var returnData = returnDataTemp.returnData;
    if(returnData.success) {
        var data = returnData.data;
        $("#positionForm input").each(function(index, element) {
            $("#"+element.id).val(data[element.name]);
        });
    } else {
        returnDataTemp.errMsg = "查询职位信息失败";
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
                var returnDataTemp = commonFuns.$Ajax( contextPath + "/sys/position/edit", formVal);
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