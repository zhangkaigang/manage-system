var deptAdd = {
    selectedNode : {}
};
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
                var selectedNode = deptAdd.selectedNode;
                if (selectedNode && JSON.stringify(selectedNode) != '{}') {
                    $("#pId").val(selectedNode.id);
                    $("#pName").val(selectedNode.name);
                }
            }
        });
    });
});