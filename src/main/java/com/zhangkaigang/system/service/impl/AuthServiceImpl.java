package com.zhangkaigang.system.service.impl;

import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.base.service.BaseService;
import com.zhangkaigang.base.utils.PoJoConverterUtil;
import com.zhangkaigang.system.dao.AuthDao;
import com.zhangkaigang.system.pojo.dto.AuthDTO;
import com.zhangkaigang.system.pojo.po.Auth;
import com.zhangkaigang.system.service.AuthService;
import com.zhangkaigang.system.service.RoleAuthService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/12
 * @Version:1.0
 */
@Service
public class AuthServiceImpl  extends BaseService implements AuthService {

    @Autowired
    private AuthDao authDao;

    @Autowired
    private RoleAuthService roleAuthService;

    @Override
    public List<AuthDTO> listTree(String condition, String levels) {
        Example example = new Example(Auth.class);
        if(StringUtils.isNotEmpty(condition)) {
            Example.Criteria criteria = example.createCriteria();
            criteria.orLike("name", "%" + condition + "%").orLike("authCode", "%" + condition + "%");
        }
        if(StringUtils.isNotEmpty(levels)) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("levels", levels);
            example.and(criteria);
        }
        example.orderBy("sort").asc();
        List<Auth> authList = authDao.selectByExample(example);
        List<AuthDTO> authDTOList = PoJoConverterUtil.objectListConverter(authList, AuthDTO.class);
        return authDTOList;
    }

    @Override
    public List<ZTreeNode> getAuthZTree() {
        return authDao.getAuthZTree();
    }

    @Override
    public void add(AuthDTO authDTO) {
        Auth auth = PoJoConverterUtil.objectConverter(authDTO, Auth.class);
        auth.setCreateTime(new Date());
        auth.setAuthId(idWorker.nextId());
        authDao.insertSelective(auth);
    }

    @Override
    public void edit(AuthDTO authDTO) {
        Auth auth = PoJoConverterUtil.objectConverter(authDTO, Auth.class);
        auth.setModifyTime(new Date());
        authDao.updateByPrimaryKeySelective(auth);
    }

    @Override
    public AuthDTO findByAuthId(Long authId) {
        Auth auth = authDao.selectByPrimaryKey(authId);
        AuthDTO authDTO = PoJoConverterUtil.objectConverter(auth, AuthDTO.class);
        return authDTO;
    }

    @Override
    public void delete(Long authId) {
        List<Auth> authList = selectByParentId(authId);
        if(CollectionUtils.isNotEmpty(authList)) {
            for (Auth auth : authList) {
                // 删除角色权限关联
                roleAuthService.deleteByAuthId(auth.getAuthId());
                delete(auth.getAuthId());
            }
        }
        authDao.deleteByPrimaryKey(authId);
    }

    /**
     * 根据父级ID查询权限列表
     * @param parentId
     * @return
     */
    public List<Auth> selectByParentId(Long parentId) {
        Example example = new Example(Auth.class);
        Example.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andEqualTo("parentId", parentId);
        return authDao.selectByExample(example);
    }

    @Override
    public List<AuthDTO> findAll(Integer authType, Long parentId) {
        List<AuthDTO> authDTOList = findAllByAuthTypeAndParentId(authType, parentId);
        getChildAuthDTOList(authDTOList, authType);
        return authDTOList;
    }

    @Override
    public List<AuthDTO> findByUserId(Long userId, Integer authType, Long parentId) {
        List<Auth> authList = authDao.findByUserId(userId, authType, parentId);
        List<AuthDTO> authDTOList = PoJoConverterUtil.objectListConverter(authList, AuthDTO.class);
        if(authType == 1) {
            // 如果是菜单，则递归
            getChildAuthDTOListByUserId(authDTOList, userId, authType);
        }
        return authDTOList;
    }

    /**
     * 根据authType和parentId查询权限数据
     * @param authType
     * @param parentId
     * @return
     */
    private List<AuthDTO> findAllByAuthTypeAndParentId(Integer authType, Long parentId) {
        Example example = new Example(Auth.class);
        Example.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andEqualTo("authType", authType);
        criteria.andEqualTo("parentId", parentId);
        example.orderBy("sort").asc();
        List<Auth> authList = authDao.selectByExample(example);
        List<AuthDTO> authDTOList = PoJoConverterUtil.objectListConverter(authList, AuthDTO.class);
        return authDTOList;
    }

    /**
     * 根据authType递归查询所有权限数据
     * @param authDTOList
     * @param authType
     */
    private void getChildAuthDTOList(List<AuthDTO> authDTOList, Integer authType) {
        if(CollectionUtils.isNotEmpty(authDTOList)) {
            for (AuthDTO authDTO : authDTOList) {
                List<AuthDTO> childAuthDTOList = findAllByAuthTypeAndParentId(authType, authDTO.getAuthId());
                authDTO.setChildren(childAuthDTOList);
                getChildAuthDTOList(childAuthDTOList, authType);
            }
        }
    }

    /**
     * 根据authType和userId递归查询用户所拥有的权限数据
     * @param authDTOList
     * @param userId
     * @param authType
     */
    private void getChildAuthDTOListByUserId(List<AuthDTO> authDTOList, Long userId, Integer authType) {
        if(CollectionUtils.isNotEmpty(authDTOList)) {
            for (AuthDTO authDTO : authDTOList) {
                List<Auth> childAuthList = authDao.findByUserId(userId, authType, authDTO.getAuthId());
                List<AuthDTO> childAuthDTOList = PoJoConverterUtil.objectListConverter(childAuthList, AuthDTO.class);
                authDTO.setChildren(childAuthDTOList);
                getChildAuthDTOListByUserId(childAuthDTOList, userId, authType);
            }
        }
    }
}
