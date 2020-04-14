layui.use(['tree', 'table'], function () {
    var tree = layui.tree;
    var table = layui.table;
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
    var data1 = [{
        title: '江西'
        ,id: 1
        ,spread: true
        ,children: [{
            title: '南昌'
            ,id: 1000
            ,children: [{
                title: '青山湖区'
                ,id: 10001
            },{
                title: '高新区'
                ,id: 10002
            }]
        },{
            title: '九江'
            ,id: 1001
        },{
            title: '赣州'
            ,id: 1002
        }]
    }];


    //仅节点左侧图标控制收缩
    tree.render({
        elem: '#' + Dept.treeId,
        data: data1,
        // 是否仅允许节点左侧图标控制展开收缩
        onlyIconControl: true,
        click: function(obj){
            layer.msg(JSON.stringify(obj.data));
        }
    });

    table.render({
        height: "450",
        elem: '#' + Dept.tableId,
        id: Dept.tableId,
        url: contextPath + '/dept/deptTree',
        page: true,
        cellMinWidth: 100,
        cols: Dept.initCols
    });

});