package com.zhangkaigang.system.service;

import com.zhangkaigang.base.pojo.node.LayuiTreeNode;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/18
 * @Version:1.0
 */
public interface DeptService {

    /**
     * 获取layui风格的部门树
     * @return
     */
    List<LayuiTreeNode> getDeptLayuiTree();
}
