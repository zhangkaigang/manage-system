layui.use(['layer', 'form'], function () {
    var layer = layui.layer;
    var form = layui.form;

    // 获取部门信息，回显
    var selectData = parent.param.selectData;
    console.log(selectData)
    $("#deptForm input").each(function(index, element) {
        $("#"+element.id).val(selectData[element.name]);
    });
    /*var deptId = parent.param.deptId;
    var returnDataTemp = commonFuns.$Ajax(contextPath + '/sys/dept/findByDeptId/' + deptId);
    var returnData = returnDataTemp.data;
    if(returnData.success) {
        console.log();
        $("#deptForm input").each(function(index, element) {
            $("#"+element.id).val(selectData[element.name]);
        });
    } else {
        if(data.message){
            layer.msg(data.message, {icon: 5});
        }else{
            layer.msg(data.errMsg ? data.errMsg : '查询部门信息失败', {icon: 5});
        }
        return false;
    }*/



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
                var returnData = commonFuns.$Ajax( contextPath + "/sys/dept/edit", formVal);
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