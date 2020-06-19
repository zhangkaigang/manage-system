var selectedNode = {};
layui.config({
    base: contextPath + '/js/layui/extend/formSelects/'
}).extend({
    formSelects:'formSelects-v4'
}).use(['form', 'formSelects'], function(){
    var form = layui.form;
    var formSelects = layui.formSelects;

    // 初始化所有职位列表
    formSelects.config('selPosition', {
        searchUrl: contextPath + "/sys/position/findAllPositions",
        keyName: 'name',
        keyVal: 'positionId'
    });
    formSelects.render();

    // 点击所属部门时
    $('#deptName').click(function () {
        commonFuns.openDeptTree();
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        userAddFuns[type] ? userAddFuns[type].call(this) : '';
    });

    // 自定义函数
    var userAddFuns = {
        // 保存
        btnSave : function () {
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                var formVal = data.field;
                var returnDataTemp = commonFuns.$Ajax( contextPath + "/sys/user/add", formVal);
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