package com.zhangkaigang.system.service.impl;

import com.zhangkaigang.system.dao.RoleAuthDao;
import com.zhangkaigang.system.pojo.po.RoleAuth;
import com.zhangkaigang.system.service.RoleAuthService;
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
public class RoleAuthServiceImpl implements RoleAuthService {

    @Autowired
    private RoleAuthDao roleAuthDao;

    @Override
    public void deleteByRoleIdList(List<Long> roleIdList) {
        Example example = new Example(RoleAuth.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("roleId", roleIdList);
        roleAuthDao.deleteByExample(example);
    }

    @Override
    public void deleteByAuthId(Long authId) {
        Example example = new Example(RoleAuth.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("authId", authId);
        roleAuthDao.deleteByExample(example);
    }

    @Override
    public List<RoleAuth> findByRoleId(Long roleId) {
        Example example = new Example(RoleAuth.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        return roleAuthDao.selectByExample(example);
    }
}
