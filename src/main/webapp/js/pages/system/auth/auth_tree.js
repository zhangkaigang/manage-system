var authTree = {
    treeId : 'authTree',
    setting : {
        async: {
            enable: true,//采用异步加载
            dataFilter: ajaxDataFilter,    //预处理数据
            url : contextPath + '/sys/auth/getAuthZTree',
            dataType : "json"
        },
        data: {
            key : {
                title : "title", //鼠标悬停显示的信息
                name : "name" //网页上显示出节点的名称
            },
            simpleData: {
                enable: true,
                idKey: "id", //修改默认的ID为自己的ID
                pIdKey: "pId",//修改默认父级ID为自己数据的父级ID
                rootPId: 0     //根节点的ID
            }
        },
        callback : {
            onAsyncSuccess: zTreeOnAsyncSuccess //异步加载完成调用
        },
        returnVal : {}
    }
};


function ajaxDataFilter(treeId, parentNode, responseData) {
    return responseData;
}

// 异步加载完成时运行，此方法将所有的节点打开
function zTreeOnAsyncSuccess (event, treeId, msg) {
    var treeObj = $.fn.zTree.getZTreeObj(authTree.treeId);
    treeObj.expandAll(true);
}

layui.use(['layer'], function() {
    // 初始化部门树
    $.fn.zTree.init($("#" + authTree.treeId), authTree.setting, null);

    // 确定
    $('#btnSure').click(function () {
        // 获取光标选中的节点
        var treeObj = $.fn.zTree.getZTreeObj(authTree.treeId);
        var selectedNodes = treeObj.getSelectedNodes();
        window.parent.selectedNode = selectedNodes[0];
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

    // 取消
    $('#btnCancel').click(function () {
        commonFuns.btnCancel();
    });
});