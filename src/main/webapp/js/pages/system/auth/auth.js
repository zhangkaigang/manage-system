var cols = [[
    {type: 'numbers'},
    {field: 'name', align: "left", sort: true, title: '权限名称', width: '30%'},
    {field: 'authCode', align: "left", sort: true, title: '权限编码'},
    {field: 'authType', align: "left", sort: true, title: '权限类型', templet: function (d) {
            if(d.authType == 1) {
                return '菜单';
            } else {
                return '功能';
            }
        }
    },
    {field: 'url', align: "left", sort: true, title: '权限路径'},
    {field: 'levels', align: "left", sort: true, title: '层级'},
    {field: 'description', align: "left", sort: true, title: '描述'},
    {field: 'sort', align: "left", sort: true, title: '排序'},
    {align: 'center', toolbar: '#btnBar', title: '操作', minWidth: 150}
]];

var tableIns;
var tableId = 'authTable';

layui.config({
    base: contextPath + '/js/layui/extend/treetable/'
}).extend({
    treeTable:'treeTable'
}).use(['layer', 'form', 'treeTable'], function () {
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
            isPidData: true,         // 是否是pid形式的数据，懒加载方式不需要
            // getIcon: function(d) {  // 自定义图标
            //     // d是当前行的数据
            //     if (d.children && d.children.length>0) {  // 判断是否有子集
            //         return '<i class="ew-tree-icon ew-tree-icon-folder"></i>';
            //     } else {
            //         return '<i class="ew-tree-icon ew-tree-icon-file"></i>';
            //     }
            // }
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

    // 监听行工具事件
    treeTable.on('tool('+ tableId +')', function(obj) {
        var selectData = obj.data;
        if(obj.event === 'btnAddChild') {

        } else if (obj.event === 'btnEdit'){
            param = {
                authId : selectData.authId
            };
            layer.open({
                type: 2,
                title : '修改权限',
                area: ['800px', '500px'],
                content: contextPath + '/sys/auth/editPage'
            });
        } else if (obj.event === 'btnDelete') {
            layer.confirm('是否删除该权限以及其所有子权限？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    var returnDataTemp = commonFuns.$Ajax(contextPath + "/sys/auth/delete", {"authId" : selectData.authId});
                    commonFuns.dealResult(returnDataTemp);
                }
            );
        }
    });

    commonFuns.renderForm();
});
