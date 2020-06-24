package com.zhangkaigang.system.service;

import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.system.pojo.dto.AuthDTO;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/12
 * @Version:1.0
 */
public interface AuthService {

    List<AuthDTO> listTree(String condition, String levels);

    /**
     * 获取ztree风格的权限树
     * @return
     */
    List<ZTreeNode> getAuthZTree();

    /**
     * 新增
     * @param authDTO
     */
    void add(AuthDTO authDTO);

    /**
     * 编辑部门
     * @param authDTO
     */
    void edit(AuthDTO authDTO);

    /**
     * 根据主键查询权限
     * @param authId
     * @return
     */
    AuthDTO findByAuthId(Long authId);

    /**
     * 删除权限，删除本身和子权限
     * @param authId
     */
    void delete(Long authId);

    /**
     * 找到所有权限数据
     * @param authType
     * @param parentId
     * @return
     */
    List<AuthDTO> findAll(Integer authType, Long parentId);

    /**
     * 根据用户ID查询用户关联的权限
     * @param userId
     * @param authType
     * @param parentId
     * @return
     */
    List<AuthDTO> findByUserId(Long userId, Integer authType, Long parentId);


}
