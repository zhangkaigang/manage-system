package com.zhangkaigang.system.service;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.system.pojo.dto.UserDTO;

import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/22
 * @Version:1.0
 */
public interface UserService {

    /**
     * 用户列表分页
     * @param condition
     * @param deptId
     * @return
     */
    PageInfo<UserDTO> list(String condition, Long deptId);

    /**
     * 根据登录名查询用户
     * @param loginName
     * @return
     */
    UserDTO findByLoginName(String loginName);

    /**
     * 新增用户
     * @param userDTO
     */
    void add(UserDTO userDTO);

    /**
     * 删除用户，删除用户和角色关联，删除用户和职位的关联
     * @param userIds
     */
    void delete(String userIds);

    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    UserDTO findByUserId(Long userId);

    /**
     * 编辑
     * @param userDTO
     */
    void edit(UserDTO userDTO);

    /**
     * 获取分配角色数据
     * @param userId
     * @return
     */
    Map<String, Object> getAssignRoleData(Long userId);

    /**
     * 分配角色
     * @param roleIds
     * @param userId
     */
    void assignRole(String roleIds, Long userId);
}
