layui.use(['layer', 'form'], function () {
    var layer = layui.layer;
    var form = layui.form;

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        positionAddFuns[type] ? positionAddFuns[type].call(this) : '';
    });

    // 自定义函数
    var positionAddFuns = {
        // 保存
        btnSave : function () {
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                var formVal = data.field;
                var returnData = commonFuns.$Ajax( contextPath + "/sys/position/addPosition", formVal);
                commonFuns.dealChildResult(returnData);
                return false;
            });
        },
        // 取消
        btnCancel : function () {
            commonFuns.btnCancel();
        }
    };
});