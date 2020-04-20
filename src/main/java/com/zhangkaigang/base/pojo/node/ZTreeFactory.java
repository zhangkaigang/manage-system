package com.zhangkaigang.base.pojo.node;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/20
 * @Version:1.0
 */
public class ZTreeFactory {

    public static ZTreeNode createRoot() {
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setChecked(true);
        zTreeNode.setId(0L);
        zTreeNode.setName("顶级");
        zTreeNode.setTitle("顶级");
        zTreeNode.setOpen(true);
        zTreeNode.setpId(0L);
        return zTreeNode;
    }
}
