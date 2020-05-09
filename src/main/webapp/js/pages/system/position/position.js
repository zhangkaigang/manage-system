var tableIns, pageCurr;
var position = {
    tableId: 'positionTable',
    initCols: [[
        {type: 'checkbox'},
        {field: 'name', align: "center", sort: true, title: '职位名称'},
        {field: 'description', align: "center", sort: true, title: '职位描述'},
        {field: 'createTime', align: "center", sort: true, title: '创建时间'},
        {field: 'modifyTime', align: "center", sort: true, title: '修改时间'},
        {field: 'status', align: "center", sort: true, title: '状态'},
        {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
    ]]
};
layui.use(['layer', 'form', 'table'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;

    // 渲染表格
    tableIns = table.render({
        height: 'full-90',
        id: position.tableId,
        elem: '#' + position.tableId,
        url: contextPath + '/sys/position/list',
        cols: position.initCols,
        method:'post',
        where:{},
        page: {
            limit:10,
            limits:[10, 50, 100, 200]
        },
        done: function(res, curr, count){
            pageCurr = curr;
        }

    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        positionFuns[type] ? positionFuns[type].call(this) : '';
    });

    // 自定义函数
    var positionFuns = {
        // 添加
        btnAdd : function () {
            // 页面层
            layer.open({
                type: 2,
                title : '添加职位',
                area: ['600px', '350px'],
                content: contextPath + '/sys/position/addPositionPage'
            });
        }
    };


});