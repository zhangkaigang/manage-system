package com.zhangkaigang.system.service.impl;

import com.zhangkaigang.system.dao.UserRoleDao;
import com.zhangkaigang.system.pojo.po.UserRole;
import com.zhangkaigang.system.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/6/18
 * @Version:1.0
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void deleteByUserIdList(List<Long> userIdList) {
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("userId", userIdList);
        userRoleDao.deleteByExample(example);
    }

    @Override
    public void deleteByUserId(Long userId) {
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        userRoleDao.deleteByExample(example);
    }

    @Override
    public void deleteByRoleIdList(List<Long> roleIdList) {
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("roleId", roleIdList);
        userRoleDao.deleteByExample(example);
    }

    @Override
    public List<UserRole> findByUserId(Long userId) {
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        return userRoleDao.selectByExample(example);
    }

    @Override
    public void saveUserRole(String roleIds, Long userId) {
        if(StringUtils.isNotEmpty(roleIds)) {
            String[] roleIdsArray = roleIds.split(",");
            for (String roleId : roleIdsArray) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Long.parseLong(roleId));
                userRoleDao.insert(userRole);
            }
        }
    }
}
