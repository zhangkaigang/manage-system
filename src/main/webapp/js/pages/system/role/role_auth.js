var setting = {
    check: {
        enable: true,
        chkboxType:{ "Y":"ps", "N":"ps"}
    },
    data: {
        key : {
            title : "name", //鼠标悬停显示的信息
            name : "name" //网页上显示出节点的名称
        },
        simpleData: {
            enable: true,
            idKey: "id", //修改默认的ID为自己的ID
            pIdKey: "pId",//修改默认父级ID为自己数据的父级ID
            rootPId: 0     //根节点的ID
        }
    }
};
var treeId = "authTree";
layui.use(['form'], function() {
    var form = layui.form;
    var roleId = parent.param.roleId;
    var returnDataTemp = commonFuns.$Ajax(contextPath + "/sys/role/findAuthByRoleId/" + roleId);
    var returnData = returnDataTemp.returnData;
    if(returnData.success) {
        var treeNode = returnData.data;
        $.fn.zTree.init($("#"+ treeId), setting, treeNode);
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        treeObj.expandAll(true);
    } else {
        commonFuns.errorInfo(returnDataTemp);
        return false;
    }

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        roleAuthFuns[type] ? roleAuthFuns[type].call(this) : '';
    });

    // 自定义函数
    var roleAuthFuns = {
        // 保存
        btnSave: function () {
            // 获取选中的权限
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var nodes = treeObj.getCheckedNodes(true);
            // 选中的复选框
            var nodeIds =new Array();
            for (var i = 0; i < nodes.length; i++) {
                nodeIds.push(nodes[i].id);
            }
            //校验是否授权
            var authIds = nodeIds.join(",");
            if(authIds == null || authIds ==''){
                layer.msg("请给该角色添加权限！", {icon : 5})
                return false;
            }
            var returnDataTemp = commonFuns.$Ajax(contextPath+"/sys/role/saveRoleAuth",
                {'roleId' : roleId, 'authIds' : authIds});
            commonFuns.dealChildResult(returnDataTemp);
        },
        // 取消
        btnCancel: function () {
            commonFuns.btnCancel();
        }
    };
});