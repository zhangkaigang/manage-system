var selectedNode = {};
layui.config({
    base: contextPath + '/js/layui/extend/formSelects/'
}).extend({
    formSelects:'formSelects-v4'
}).use(['form', 'formSelects'], function(){
    var form = layui.form;
    var formSelects = layui.formSelects;

    // 获取用户信息，回显
    var userId = parent.param.userId;
    var returnDataTemp = commonFuns.$Ajax(contextPath + '/sys/user/findByUserId/' + userId);
    var returnData = returnDataTemp.returnData;
    if(returnData.success) {
        var data = returnData.data;
        form.val('userForm', data);
        $('#loginName').attr("disabled",true);
        $('#loginName').addClass("layui-disabled");
        formSelects.config('selPosition', {
            searchUrl: contextPath + "/sys/position/findAllPositions?userId=" + userId,
            keyName: 'name',
            keyVal: 'positionId'
        });
        formSelects.render();
        commonFuns.renderForm();
    } else {
        returnDataTemp.errMsg = "查询用户信息失败";
        commonFuns.errorInfo(returnDataTemp);
        return false;
    }

    // 点击所属部门时
    $('#deptName').click(function () {
        commonFuns.openDeptTree();
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        userEditFuns[type] ? userEditFuns[type].call(this) : '';
    });

    // 自定义函数
    var userEditFuns = {
        // 保存
        btnSave : function () {
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                var formVal = data.field;
                var returnDataTemp = commonFuns.$Ajax( contextPath + "/sys/user/edit", formVal);
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