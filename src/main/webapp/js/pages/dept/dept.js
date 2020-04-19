var Dept = {
    treeId: 'deptTree',
    tableId: 'deptTable',
    initCols: [[
        {type: 'checkbox'},
        {field: 'deptId', hide: true, sort: true, title: 'id'},
        {field: 'simpleName', align: "center", sort: true, title: '部门简称'},
        {field: 'fullName', align: "center", sort: true, title: '部门全称'},
        {field: 'sort', align: "center", sort: true, title: '排序'},
        {field: 'description', align: "center", sort: true, title: '备注'},
        {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
    ]],
    condition: {
        deptId: ""
    }
};
layui.use(['tree', 'table'], function () {
    var tree = layui.tree;
    var table = layui.table;
    var returnDataTemp = commonFuns.$Ajax(contextPath + '/sys/dept/getDeptLayuiTree');
    var returnData = returnDataTemp.data;
    if (returnData) {
        // 仅节点左侧图标控制收缩
        tree.render({
            elem: '#' + Dept.treeId,
            data: returnData,
            // 是否仅允许节点左侧图标控制展开收缩
            onlyIconControl: true,
            click: function(obj){
                console.log(JSON.stringify(obj.data))
            }
        });
    } else {
        layer.msg(returnDataTemp.errMsg ? returnDataTemp.errMsg : '加载部门树失败', {icon: 5});
        return false;
    }


    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        deptFuns[type] ? deptFuns[type].call(this) : '';
    });
    // 自定义函数
    var deptFuns = {
        // 添加
        btnAdd : function () {
            // 页面层
            layer.open({
                type: 2,
                title : '添加部门',
                area: ['800px', '500px'],
                content: contextPath + '/sys/dept/addDeptPage'
            });
        }
    };







/*    table.render({
        height: "450",
        elem: '#' + Dept.tableId,
        id: Dept.tableId,
        url: contextPath + '/sys/dept/getDeptLayuiTree',
        page: true,
        cellMinWidth: 100,
        cols: Dept.initCols
    });*/

});