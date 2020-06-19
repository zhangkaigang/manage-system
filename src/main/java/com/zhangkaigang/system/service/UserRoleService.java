package com.zhangkaigang.system.service;

import com.zhangkaigang.system.pojo.po.UserRole;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/6/18
 * @Version:1.0
 */
public interface UserRoleService {

    /**
     * 根据用户ID列表删除用户角色关联
     * @param userIdList
     */
    void deleteByUserIdList(List<Long> userIdList);

    /**
     * 根据用户ID删除用户角色关联
     * @param userId
     */
    void deleteByUserId(Long userId);

    /**
     * 根据角色ID列表删除用户角色关联
     * @param roleIdList
     */
    void deleteByRoleIdList(List<Long> roleIdList);

    /**
     * 根据用户ID得到用户角色关联数据
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(Long userId);

    /**
     * 插入用户角色关联
     * @param roleIds
     * @param userId
     */
    void saveUserRole(String roleIds, Long userId);
}
