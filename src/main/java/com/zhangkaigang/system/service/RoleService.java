package com.zhangkaigang.system.service;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.system.pojo.dto.RoleDTO;
import com.zhangkaigang.system.pojo.po.RoleAuth;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/17
 * @Version:1.0
 */
public interface RoleService {

    /**
     * 角色列表-分页
     * @param condition
     * @return
     */
    PageInfo<RoleDTO> list(String condition);

    /**
     * 添加角色
     * @param roleDTO
     */
    void add(RoleDTO roleDTO);

    /**
     * 根据角色编码查询
     * @param roleCode
     * @return
     */
    RoleDTO findByRoleCode(String roleCode);

    /**
     * 删除，同时需要删除角色权限关联数据
     * @param roleIds
     */
    void delete(String roleIds);

    /**
     * 编辑
     * @param roleDTO
     */
    void edit(RoleDTO roleDTO);

    /**
     * 根据角色ID查询角色
     * @param roleId
     * @return
     */
    RoleDTO findByRoleId(Long roleId);

    /**
     * 查询角色拥有的权限列表
     * @return
     */
    List<RoleAuth> findAuthByRoleId(Long roleId);
}
