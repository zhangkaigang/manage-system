layui.use(["layer", "element"], function () {
    var element = layui.element;
    $('.menu_click').on('click', function () {
        var menuData = $(this);
        // 判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
        if ($(".layui-tab-title li[lay-id]").length <= 0) {
            // 打开新的tab项
            active.tabAdd(menuData.attr("data-title"), menuData.attr("data-url"), menuData.attr("data-id"));
        } else {
            // 判断该tab项是否以及存在
            var isData = false;
            $.each($('.layui-tab-title li[lay-id]'), function () {
                // 如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                if ($(this).attr("lay-id") == menuData.attr("data-id")) {
                    isData = true;
                }
            })
            if (isData == false) {
                //标志为false 新增一个tab项
                active.tabAdd(menuData.attr("data-title"), menuData.attr("data-url"), menuData.attr("data-id"));
            }

        }

        // 转到要打开的选项页面上
        active.tabChange(menuData.attr("data-id"));

    });

    // 定义函数
    var active = {
        // 增加tab标签
        tabAdd : function (title, url, id) {
            // 新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
            element.tabAdd('menu-tab-nav', {
                title: title,
                content: '<iframe class="content-iframe" data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + contextPath + url + '"></iframe>',
                id: id
            })
        },
        // 切换到指定tab项
        tabChange : function (id) {
            element.tabChange('menu-tab-nav', id);
        }
    };
});

