package com.zhangkaigang.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.service.BaseService;
import com.zhangkaigang.base.utils.PoJoConverterUtil;
import com.zhangkaigang.system.dao.RoleAuthDao;
import com.zhangkaigang.system.dao.RoleDao;
import com.zhangkaigang.system.pojo.dto.RoleDTO;
import com.zhangkaigang.system.pojo.po.Role;
import com.zhangkaigang.system.pojo.po.RoleAuth;
import com.zhangkaigang.system.service.RoleAuthService;
import com.zhangkaigang.system.service.RoleService;
import com.zhangkaigang.system.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/17
 * @Version:1.0
 */
@Service
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleAuthService roleAuthService;

    @Autowired
    private RoleAuthDao roleAuthDao;

    @Override
    public PageInfo<RoleDTO> list(String condition) {
        Example example = new Example(Role.class);
        if(StringUtils.isNotEmpty(condition)) {
            Example.Criteria criteria = example.createCriteria();
            criteria.orLike("name", "%" +condition+ "%").orLike("roleCode", "%" +condition+ "%");
        }
        example.orderBy("sort").asc();
        List<Role> roleList = roleDao.selectByExample(example);
        List<RoleDTO> roleDTOList = PoJoConverterUtil.objectListConverter(roleList, RoleDTO.class);
        return LayuiPageFactory.getPageInfo(roleDTOList);
    }

    @Override
    public void add(RoleDTO roleDTO) {
        Role role = PoJoConverterUtil.objectConverter(roleDTO, Role.class);
        role.setRoleId(idWorker.nextId());
        role.setCreateTime(new Date());
        roleDao.insertSelective(role);
    }

    @Override
    public RoleDTO findByRoleCode(String roleCode) {
        Role roleTemp = new Role();
        roleTemp.setRoleCode(roleCode);
        Role role = roleDao.selectOne(roleTemp);
        RoleDTO roleDTO = PoJoConverterUtil.objectConverter(role, RoleDTO.class);
        return roleDTO;
    }


    @Override
    public void delete(String roleIds) {
        List<Long> roleIdsList = StringUtils.isNotEmpty(roleIds) ?
                new ArrayList(Arrays.asList(roleIds.split(","))) : new ArrayList<>();
        // 删除用户角色关联和角色权限关联
        roleAuthService.deleteByRoleIdList(roleIdsList);
        userRoleService.deleteByRoleIdList(roleIdsList);
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("roleId", roleIdsList);
        roleDao.deleteByExample(example);
    }

    @Override
    public void edit(RoleDTO roleDTO) {
        Role role = PoJoConverterUtil.objectConverter(roleDTO, Role.class);
        role.setModifyTime(new Date());
        roleDao.updateByPrimaryKeySelective(role);
    }

    @Override
    public RoleDTO findByRoleId(Long roleId) {
        Role role = roleDao.selectByPrimaryKey(roleId);
        RoleDTO roleDTO = PoJoConverterUtil.objectConverter(role, RoleDTO.class);
        return roleDTO;
    }

    @Override
    public List<RoleAuth> findAuthByRoleId(Long roleId) {
        Example example = new Example(RoleAuth.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        return roleAuthDao.selectByExample(example);
    }


    @Override
    public void saveRoleAuth(Long roleId, String authIds) {
        String[] authIdsArray = authIds.split(",");
        Example example = new Example(RoleAuth.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        roleAuthDao.deleteByExample(example);
        for(String authIdStr : authIdsArray){
            Long authId = Long.parseLong(authIdStr);
            if(authId == 0L) {
                continue;
            }
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setRoleId(roleId);
            roleAuth.setAuthId(authId);
            roleAuthDao.insertSelective(roleAuth);
        }
    }
}
