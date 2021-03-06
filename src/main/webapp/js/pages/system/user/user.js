layui.use(['tree', 'table', 'form'], function () {
    var tree = layui.tree;
    var table = layui.table;
    var form = layui.form;

    var cols = [[
        {type: 'checkbox'},
        {field: 'loginName', align: "center", sort: true, title: '登录名'},
        {field: 'userName', align: "center", sort: true, title: '姓名'},
        {field: 'deptName', align: "center", sort: true, title: '部门'},
        {field: 'positionNames', align: "center", sort: true, title: '职位'},
        {field: 'createTime', align: "center", sort: true, title: '创建时间', templet: function (d) {
                return d.createTime ? dayjs(d.createTime).format('YYYY-MM-DD HH:mm') : '';
            }},
        {field: 'status', align: "center", sort: true, title: '状态', templet: '#statusTpl'},
        {align: 'center', toolbar: '#btnBar', title: '操作', minWidth: 300}
    ]];

    var tableIns, page;
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
            page = curr;
        }

    });

    // 监听行工具事件
    table.on('tool('+ tableId +')', function(obj) {
        var selectData = obj.data;
        if (obj.event === 'btnEdit') {
            param = {
                userId: selectData.userId
            };
            layer.open({
                type: 2,
                title: '修改用户',
                area: ['800px', '500px'],
                content: contextPath + '/sys/user/editPage'
            });
        } else if (obj.event === 'btnDelete') {
            userFuns.delete(selectData.userId);
        } else if (obj.event == 'btnResetPwd') {
            userFuns.resetPwd(selectData.userId);
        } else if (obj.event == 'btnAssignRole') {
            param = {
                userId : selectData.userId
            };
            layer.open({
                type: 2,
                title : '分配角色',
                area: ['600px', '400px'],
                content: contextPath + '/sys/user/assignRolePage'
            });

        }
    });

    // 监听启用禁用开关
    form.on('switch(status)', function(obj){
        var userId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;
        userFuns.changeStatus(userId, checked);
    });


    // 按钮点击事件
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        userFuns[type] ? userFuns[type].call(this) : '';
    });

    // 自定义函数
    var userFuns = {
        // 重置密码
        resetPwd : function (userId) {
            layer.confirm('您确定要把该用户的密码重置为123吗？', {
                    btn: ['确认','取消']
                }, function(){
                var url = contextPath + "/sys/user/resetPwd/" + userId;
                var returnDataTemp = commonFuns.$Ajax(url);
                commonFuns.dealResult(returnDataTemp);
            });
        },
        // 改变激活状态
        changeStatus : function(userId, checked){
            var statusName = checked ? '启用' : '禁用';
            var status = checked ? 'ENABLE' : 'DISBABLE';
            layer.confirm('您确定要把该用户设置为' + statusName + '状态吗？', {
                btn: ['确认','取消']
            }, function(){
                var url = contextPath + "/sys/user/changeStatus/" + userId + "/" + status;
                var returnDataTemp = commonFuns.$Ajax(url);
                commonFuns.dealResult(returnDataTemp);
            }, function(){
                // 加载load方法
                tableIns.reload({
                    page: {
                        curr: page
                    }
                });
            });
        },
        // 删除逻辑
        delete : function(userIds){
            layer.confirm('确定删除所选记录？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {
                    var returnDataTemp = commonFuns.$Ajax(contextPath + "/sys/user/delete", {"userIds" : userIds});
                    commonFuns.dealResult(returnDataTemp);
                }
            );
        },
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