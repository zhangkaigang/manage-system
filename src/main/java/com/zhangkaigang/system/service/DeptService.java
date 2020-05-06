package com.zhangkaigang.system.service;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.pojo.node.LayuiTreeNode;
import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.system.pojo.dto.DeptDTO;

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

    /**
     * 部门列表分页
     * @return
     */
    PageInfo<DeptDTO> list();

    /**
     * 获取ztree风格的部门树
     * @return
     */
    List<ZTreeNode> getDeptZTree();

    /**
     * 新增部门
     * @param deptDTO
     */
    void add(DeptDTO deptDTO);

    /**
     * 删除部门，删除本身和子部门
     * @param deptId
     */
    void delete(Long deptId);
}
