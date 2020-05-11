var selectedNode = {};
var layer, form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form;

    // 获取部门信息，回显
    var deptId = parent.param.deptId;
    var returnDataTemp = commonFuns.$Ajax(contextPath + '/sys/dept/findByDeptId/' + deptId);
    var returnData = returnDataTemp.returnData;
    if(returnData.success) {
        var data = returnData.data;
        $("#deptForm input").each(function(index, element) {
            $("#"+element.id).val(data[element.name]);
        });
    } else {
        returnDataTemp.errMsg = "查询部门信息失败";
        commonFuns.errorInfo(returnDataTemp);
        return false;
    }

    // 点击上级部门时
    $('#parentName').click(function () {
        commonFuns.openDeptTree();
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        deptEditFuns[type] ? deptEditFuns[type].call(this) : '';
    });

    // 自定义函数
    var deptEditFuns = {
        // 保存
        btnSave : function () {
            // 监听表单提交
            form.on('submit(btnSave)', function(data){
                var formVal = data.field;
                if(formVal.deptId == formVal.pId) {
                    layer.msg('上级部门不能为自己', {icon: 5});
                    return false;
                }
                var returnDataTemp = commonFuns.$Ajax( contextPath + "/sys/dept/edit", formVal);
                commonFuns.dealChildResult(returnDataTemp);
                return false;
            });
        },
        // 取消
        btnCancel : function () {
            commonFuns.btnCancel();
        }
    };
});