var cols = [[
    {type: 'checkbox'},
    {field: 'name', align: "center", sort: true, title: '角色名称'},
    {field: 'roleCode', align: "center", sort: true, title: '角色编码'},
    {field: 'description', align: "center", sort: true, title: '角色描述'},
    {field: 'sort', align: "center", sort: true, title: '排序'},
    {field: 'createTime', align: "center", sort: true, title: '创建时间', templet: function (d) {
            return d.createTime ? dayjs(d.createTime).format('YYYY-MM-DD HH:mm') : '';
        }},
    {align: 'center', toolbar: '#btnBar', title: '操作', minWidth: 150}
]];
var tableIns, page;
var tableId = 'roleTable';
layui.use(['layer', 'table'], function () {
    var layer = layui.layer;
    var table = layui.table;

    // 渲染表格
    tableIns = table.render({
        height: 'full-90',
        id: tableId,
        elem: '#' + tableId,
        url: contextPath + '/sys/role/list',
        cols: cols,
        method:'post',
        where:{},
        page: {
            limit:10,
            limits:[10, 50, 100, 200]
        },
        done: function(res, curr, count){
            page = curr;
        }

    });

    // 搜索
    $('#btnSearch').on('click', function () {
        roleFuns.search();
    });
    // 搜索输入框回车事件
    $('#searchName').bind('keypress', function (event) {
        if (event.keyCode == "13") {
            roleFuns.search();
        }
    });

    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        roleFuns[type] ? roleFuns[type].call(this) : '';
    });

    // 自定义函数
    var roleFuns = {
        // 删除
        btnDelete : function(){
            var checkStatus = table.checkStatus(tableId);
            var checkData = checkStatus.data;
            if(checkData.length < 1) {
                layer.msg('请至少选择一条记录进行操作', {icon: -1});
                return false;
            }
            var roleIdsArr = [];
            for(var i in checkData) {
                roleIdsArr.push(checkData[i].roleId);
            }
            var roleIds = roleIdsArr.join(',');
            roleFuns.delete(roleIds);
        },
        // 删除逻辑
        delete : function(roleIds){
            layer.confirm('确定删除所选记录？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    var returnDataTemp = commonFuns.$Ajax(contextPath + "/sys/role/delete", {"roleIds" : roleIds});
                    commonFuns.dealResult(returnDataTemp);
                }
            );
        },
        // 添加
        btnAdd : function () {
            // 页面层
            layer.open({
                type: 2,
                title : '添加角色',
                area: ['600px', '400px'],
                content: contextPath + '/sys/role/addPage'
            });
        },
        // 搜索
        search : function() {
            tableIns.reload({
                where:{
                    condition : $('#searchName').val().trim()
                },
                page: {
                    curr: 1
                }
            });
        }
    };

    // 监听行工具事件
    table.on('tool('+ tableId +')', function(obj) {
        var selectData = obj.data;
        if (obj.event === 'btnEdit') {
            param = {
                roleId : selectData.roleId
            };
            layer.open({
                type: 2,
                title : '修改角色',
                area: ['600px', '400px'],
                content: contextPath + '/sys/role/editPage'
            });
        } else if(obj.event === 'btnDelete') {
            roleFuns.delete(selectData.roleId);
        } else if(obj.event === 'btnAuthConfig') {
            param = {
                roleId : selectData.roleId
            };
            layer.open({
                type: 2,
                title : '权限配置',
                area: ['600px', '400px'],
                content: contextPath + '/sys/role/roleAuth'
            });
        }
    });
});