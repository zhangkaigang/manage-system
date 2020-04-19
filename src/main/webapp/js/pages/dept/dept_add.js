layui.use(['layer', 'form'], function () {
    var layer = layui.layer;
    var form = layui.form;

    // 点击上级部门时
    $('#pName').click(function () {
        layer.open({
            type: 2,
            title: '父级部门',
            area: ['400px', '450px'],
            content: contextPath + '/sys/dept/deptTreePage',
            end: function () {
                // $("#pid").val(DeptInfoDlg.data.pid);
                // $("#pName").val(DeptInfoDlg.data.pName);
            }
        });
    });
});