var cols = [[
    {type: 'checkbox'},
    {field: 'name', align: "center", sort: true, title: '职位名称'},
    {field: 'description', align: "center", sort: true, title: '职位描述'},
    {field: 'createTime', align: "center", sort: true, title: '创建时间', templet: function (d) {
            return d.createTime ? dayjs(d.createTime).format('YYYY-MM-DD HH:mm') : '';
        }},
    {field: 'modifyTime', align: "center", sort: true, title: '修改时间', templet: function (d) {
            return d.modifyTime ? dayjs(d.modifyTime).format('YYYY-MM-DD HH:mm') : '';
        }},
    {field: 'sort', align: "center", sort: true, title: '排序'},
    {field: 'status', align: "center", sort: true, title: '状态', templet: '#statusTpl'},
    {align: 'center', toolbar: '#btnBar', title: '操作', minWidth: 150}
]];
var tableIns;
var tableId = 'positionTable';
var layer, table;
layui.use(['layer', 'table'], function () {
    layer = layui.layer;
    table = layui.table;

    // 渲染表格
    tableIns = table.render({
        height: 'full-90',
        id: tableId,
        elem: '#' + tableId,
        url: contextPath + '/sys/position/list',
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
        positionFuns.search();
    });
    // 搜索输入框回车事件
    $('#searchName').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            positionFuns.search();
        }
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        positionFuns[type] ? positionFuns[type].call(this) : '';
    });

    // 自定义函数
    var positionFuns = {
        // 删除
        btnDelete : function(){
            var checkStatus = table.checkStatus(tableId);
            var checkData = checkStatus.data;
            if(checkData.length < 1) {
                layer.msg('请至少选择一条记录进行操作', {icon: -1});
                return false;
            }
            var positionIdsArr = [];
            for(var i in checkData) {
                positionIdsArr.push(checkData[i].positionId);
            }
            var positionIds = positionIdsArr.join(',');
            positionFuns.delete(positionIds);
        },
        // 删除逻辑
        delete : function(positionIds){
            layer.confirm('确定删除所选记录？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    var returnDataTemp = commonFuns.$Ajax(contextPath + "/sys/position/delete", {"positionIds" : positionIds});
                    commonFuns.dealResult(returnDataTemp);
                }
            );
        },
        // 搜索
        search : function() {
            tableIns.reload({
                where:{
                    name : $('#searchName').val().trim()
                }
            });
        },
        // 添加
        btnAdd : function () {
            // 页面层
            layer.open({
                type: 2,
                title : '添加职位',
                area: ['600px', '350px'],
                content: contextPath + '/sys/position/addPage'
            });
        }
    };

    // 监听行工具事件
    table.on('tool('+ tableId +')', function(obj) {
        var selectData = obj.data;
        if (obj.event === 'btnEdit') {

        } else if(obj.event === 'btnDelete') {
            positionFuns.delete(selectData.positionId);
        }
    });


});