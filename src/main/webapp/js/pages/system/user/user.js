layui.use(['tree', 'table'], function () {
    var tree = layui.tree;
    var table = layui.table;

    var cols = [[
        {type: 'checkbox'},
        {field: 'loginName', align: "center", sort: true, title: '登录名'},
        {field: 'userName', align: "center", sort: true, title: '姓名'},
        {field: 'deptName', align: "center", sort: true, title: '部门'},
        {field: 'positionNames', align: "center", sort: true, title: '职位'},
        {field: 'createTime', align: "center", sort: true, title: '创建时间'},
        {field: 'positionNames', align: "center", sort: true, title: '职位'},
        {field: 'status', align: "center", sort: true, title: '状态', templet: '#statusTpl'},
        {align: 'center', toolbar: '#btnBar', title: '操作', minWidth: 150}
    ]];

    var tableIns;
    var treeId = 'deptTree', tableId = 'userTable';

    var returnDataTemp = commonFuns.$Ajax(contextPath + '/sys/dept/getDeptLayuiTree');
    var returnData = returnDataTemp.returnData;
    if (returnData) {
        // 仅节点左侧图标控制收缩
        tree.render({
            elem: '#' + treeId,
            data: returnData,
            // 是否仅允许节点左侧图标控制展开收缩
            onlyIconControl: true,
            click: function(obj){
                var data = obj.data;
                // condition.deptId = data.id;
                // deptFuns.search();
            }
        });
    } else {
        returnDataTemp.errMsg = "加载部门树失败";
        commonFuns.errorInfo(returnDataTemp);
        return false;
    }

    // 渲染表格
    tableIns = table.render({
        height: 'full-90',
        id: tableId,
        elem: '#' + tableId,
        url: contextPath + '/sys/user/list',
        cols: cols,
        method:'post',
        where:{},
        page: {
            limit:10,
            limits:[10, 50, 100, 200]
        },
        done: function(res, curr, count){

        }

    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        userFuns[type] ? userFuns[type].call(this) : '';
    });

    // 自定义函数
    var userFuns = {
        // 添加
        btnAdd : function () {
            // 页面层
            layer.open({
                type: 2,
                title : '添加用户',
                area: ['800px', '500px'],
                content: contextPath + '/sys/user/addPage'
            });
        }
    };
});