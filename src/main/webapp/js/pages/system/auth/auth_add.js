var selectedNode = {};
layui.use(['form'], function () {
    var form = layui.form;
    if(parent.param && parent.param.parentId) {
        // 此时是添加子权限，回显父级权限，且父级权限不可改
        commonFuns.fillAuthInfo(parent.param.parentId);
        $('#parentName').addClass("layui-disabled");
    } else {
        // 点击父级权限时
        $('#parentName').click(function () {
            commonFuns.openAuthTree();
        });
    }

    // 按钮点击事件
    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        authAddFuns[type] ? authAddFuns[type].call(this) : '';
    });

    // 自定义函数
    var authAddFuns = {
        // 保存
        btnSave : function () {
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                var formVal = data.field;
                var returnDataTemp = commonFuns.$Ajax( contextPath + "/sys/auth/add", formVal);
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

// 填充层级
function fillLevels(){


}