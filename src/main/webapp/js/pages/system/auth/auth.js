var cols = [[
    {type: 'numbers'},
    {field: 'name', align: "center", sort: true, title: '权限名称'},
    {field: 'authCode', align: "center", sort: true, title: '权限编码'},
    {field: 'authType', align: "center", sort: true, title: '权限类型'},
    {field: 'url', align: "center", sort: true, title: '权限路径'},
    {field: 'levels', align: "center", sort: true, title: '层级'},
    {field: 'authCode', align: "center", sort: true, title: '权限编码'},
    {field: 'sort', align: "center", sort: true, title: '排序'},
    {align: 'center', toolbar: '#btnBar', title: '操作', minWidth: 200}
]];

var tableIns;
var tableId = 'authTable';

layui.config({
    base: contextPath + '/js/layui/extend/treetable/'
}).extend({
    treeTable:'treeTable'
}).use(['layer', 'laytpl', 'form', 'treeTable'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var treeTable = layui.treeTable;

    // 初始化树形表格，使用请参考 https://gitee.com/whvse/treetable-lay/wikis/pages?sort_id=1986057&doc_id=142114
    tableIns = treeTable.render({
        height: "full-90",
        elem: '#' + tableId,
        url: contextPath + "/sys/auth/listTree",
        tree: {
            iconIndex: 1,           // 折叠图标显示在第几列
            idName: 'authId',         // 自定义id字段的名称
            pidName: 'parentId',       // 自定义标识是否还有子节点的字段名称
            haveChildName: 'haveChild',  // 自定义标识是否还有子节点的字段名称
            isPidData: true         // 是否是pid形式的数据，懒加载方式不需要
        },
        cols: cols
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        authFuns[type] ? authFuns[type].call(this) : '';
    });

    // 自定义函数
    var authFuns = {
        // 添加
        btnAdd : function() {
            layer.open({
                type: 2,
                title : '添加权限',
                area: ['800px', '500px'],
                content: contextPath + '/sys/auth/addPage'
            });
        }
    };

    commonFuns.renderForm();
});
