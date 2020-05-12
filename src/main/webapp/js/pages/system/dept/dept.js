layui.use(['tree', 'table'], function () {
    var tree = layui.tree;
    var table = layui.table;

    var cols = [[
        {type: 'checkbox'},
        {field: 'deptId', hide: true, sort: true, title: 'id'},
        {field: 'simpleName', align: "center", sort: true, title: '部门简称'},
        {field: 'fullName', align: "center", sort: true, title: '部门全称'},
        {field: 'sort', align: "center", sort: true, title: '排序'},
        {field: 'description', align: "center", sort: true, title: '备注'},
        {align: 'center', toolbar: '#btnBar', title: '操作', minWidth: 150}
    ]];
    var tableIns;
    var treeId = 'deptTree', tableId = 'deptTable';
    var condition = {
        deptId : ''
    };

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
                condition.deptId = data.id;
                deptFuns.search();
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
        url: contextPath + '/sys/dept/list',
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

    // 搜索
    $('#btnSearch').on('click', function () {
        deptFuns.search();
    });
    // 搜索输入框回车事件
    $('#searchName').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            deptFuns.search();
        }
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        deptFuns[type] ? deptFuns[type].call(this) : '';
    });
    // 自定义函数
    var deptFuns = {
        // 搜索
        search : function() {
            tableIns.reload({
                where:{
                    deptName : $('#searchName').val().trim(),
                    deptId : condition.deptId
                }
            });
        },
        // 添加
        btnAdd : function () {
            // 页面层
            layer.open({
                type: 2,
                title : '添加部门',
                area: ['800px', '500px'],
                content: contextPath + '/sys/dept/addPage'
            });
        }
    };

    // 监听行工具事件
    table.on('tool('+ tableId +')', function(obj){
        var selectData = obj.data;
        if(obj.event === 'btnEdit'){
            param = {
                deptId : selectData.deptId
            };
            layer.open({
                type: 2,
                title : '修改部门',
                area: ['800px', '500px'],
                content: contextPath + '/sys/dept/editPage'
            });
        } else if (obj.event === 'btnDelete') {
            layer.confirm('是否删除该部门以及其所有子部门？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    var returnDataTemp = commonFuns.$Ajax(contextPath + "/sys/dept/delete", {"deptId" : selectData.deptId});
                    commonFuns.dealResult(returnDataTemp);
                }
            );
        }
    });

});