package com.zhangkaigang.base.pojo.node;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/18
 * @Version:1.0
 */
public class LayuiTreeFactory {

    /**
     * 生成根节点
     * @return
     */
    public static LayuiTreeNode createRoot() {
        LayuiTreeNode treeNode = new LayuiTreeNode();
        treeNode.setId(0L);
        treeNode.setpId(-1L);
        treeNode.setTitle("顶级");
        treeNode.setChecked(true);
        treeNode.setSpread(true);

        return treeNode;
    }

    public static List<LayuiTreeNode> getLayuiTree(List<LayuiTreeNode> layuiTreeNodeList){
        if (CollectionUtils.isNotEmpty(layuiTreeNodeList)) {
            List<LayuiTreeNode> resultListTemp = new ArrayList<>();
            for (LayuiTreeNode layuiTreeNode : layuiTreeNodeList) {
                List<LayuiTreeNode> childList = getChildList(layuiTreeNodeList, layuiTreeNode);
                layuiTreeNode.setChildren(childList);
                resultListTemp.add(layuiTreeNode);
            }
            return getFinalLayuiTree(resultListTemp);
        }
        return Collections.EMPTY_LIST;
    }

    private static List<LayuiTreeNode> getChildList(List<LayuiTreeNode> layuiTreeNodeList, LayuiTreeNode layuiTreeNode) {
        List<LayuiTreeNode> childList = new ArrayList<>();
        for (LayuiTreeNode treeNode : layuiTreeNodeList) {
            if(treeNode.getpId().equals(layuiTreeNode.getId())) {
                childList.add(treeNode);
            }
        }
        return childList;
    }

    private static List<LayuiTreeNode> getFinalLayuiTree(List<LayuiTreeNode> layuiTreeNodeList){
        List<LayuiTreeNode> resultList = new ArrayList<>();
        for (LayuiTreeNode treeNode : layuiTreeNodeList) {
            Long rootId = -1L;
            if(rootId.equals(treeNode.getpId())) {
                resultList.add(treeNode);
            }
        }
        return resultList;
    }



}
