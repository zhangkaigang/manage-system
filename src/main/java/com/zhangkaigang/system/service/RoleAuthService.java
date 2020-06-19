package com.zhangkaigang.system.service;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/6/18
 * @Version:1.0
 */
public interface RoleAuthService {

    /**
     * 根据角色ID列表删除角色权限关联
     * @param roleIdList
     */
    void deleteByRoleIdList(List<Long> roleIdList);

    /**
     * 根据权限ID删除角色权限关联
     * @param authId
     */
    void deleteByAuthId(Long authId);
}
