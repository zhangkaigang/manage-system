var selectedNode = {};
var layer, form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form;

    // 点击上级部门时
    $('#pName').click(function () {
        commonFuns.openDeptTree();
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        deptAddFuns[type] ? deptAddFuns[type].call(this) : '';
    });

    // 自定义函数
    var deptAddFuns = {
        // 保存
        btnSave : function () {
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                var formVal = data.field;
                var returnData = commonFuns.$Ajax( contextPath + "/sys/dept/add", formVal);
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