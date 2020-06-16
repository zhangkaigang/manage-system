package com.zhangkaigang.system.service;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.system.pojo.dto.UserDTO;

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
     * 新增用户
     * @param userDTO
     */
    void add(UserDTO userDTO);
}
