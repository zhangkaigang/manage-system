layui.use(['transfer', 'layer', 'util'], function(){
    var transfer = layui.transfer;
    var layer = layui.layer;
    var util = layui.util;

    var userId = parent.param.userId;
    var returnDataTemp = commonFuns.$Ajax(contextPath + "/sys/user/getAssignRoleData/" + userId);
    var returnData = returnDataTemp.returnData;
    if(returnData.success) {
        var data = returnData.data;
        // 初始化穿梭框
        transfer.render({
            id: 'assignRole',
            elem: '#assignRole',
            data: data.allRole,
            value: data.myRole,
            title: ['所有角色', '已分配角色'],
            height: 300
        });

    } else {
        returnDataTemp.errMsg = "查询角色信息失败";
        commonFuns.errorInfo(returnDataTemp);
        return false;
    }

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        userAssignRoleFuns[type] ? userAssignRoleFuns[type].call(this) : '';
    });

    // 自定义函数
    var userAssignRoleFuns = {
        // 保存
        btnSave : function () {
            //获得右侧数据
            var assignRoleData = transfer.getData('assignRole');
            var roleIdsArr = [];
            for(var i in assignRoleData) {
                roleIdsArr.push(assignRoleData[i].value);
            }
            var roleIds = roleIdsArr.join(',');
            var returnDataTemp = commonFuns.$Ajax(contextPath + "/sys/user/assignRole",
                {'roleIds' : roleIds, 'userId' : userId });
            commonFuns.dealChildResult(returnDataTemp);
        },
        // 取消
        btnCancel : function () {
            commonFuns.btnCancel();
        }
    }

});