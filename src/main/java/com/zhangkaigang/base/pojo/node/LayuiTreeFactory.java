package com.zhangkaigang.base.pojo.node;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/18
 * @Version:1.0
 */
public class LayuiTreeFactory {

    /**
     * 生成跟节点
     * @return
     */
    public static LayuiTreeNode createRoot() {
        LayuiTreeNode treeNode = new LayuiTreeNode();
        treeNode.setId(1L);
        treeNode.setpId(0L);
        treeNode.setTitle("顶级");
        treeNode.setChecked(true);
        treeNode.setSpread(true);

        return treeNode;
    }



}
