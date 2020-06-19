package com.zhangkaigang.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.constant.CommonConstants;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.service.BaseService;
import com.zhangkaigang.base.utils.PoJoConverterUtil;
import com.zhangkaigang.system.dao.RoleDao;
import com.zhangkaigang.system.dao.UserDao;
import com.zhangkaigang.system.pojo.dto.UserDTO;
import com.zhangkaigang.system.pojo.po.Role;
import com.zhangkaigang.system.pojo.po.User;
import com.zhangkaigang.system.pojo.po.UserRole;
import com.zhangkaigang.system.service.UserPositionService;
import com.zhangkaigang.system.service.UserRoleService;
import com.zhangkaigang.system.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/22
 * @Version:1.0
 */
@Service
public class UserServiceImpl  extends BaseService implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPositionService userPositionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageInfo<UserDTO> list(String condition, Long deptId) {
        Example example = new Example(User.class);
        if(!StringUtils.isEmpty(condition)) {
            Example.Criteria criteria = example.createCriteria();
            criteria.orLike("loginName", "%" + condition + "%")
                    .orLike("userName", "%" + condition + "%");
        }
        if(!StringUtils.isEmpty(deptId)) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("deptId", deptId);
            example.and(criteria);
        }
        example.orderBy("createTime").desc();
        List<User> userList = userDao.selectByExample(example);
        List<UserDTO> userDTOList = PoJoConverterUtil.objectListConverter(userList, UserDTO.class);
        return LayuiPageFactory.getPageInfo(userDTOList);
    }

    @Override
    public UserDTO findByLoginName(String loginName) {
        User userCondition = new User();
        userCondition.setLoginName(loginName);
        User user = userDao.selectOne(userCondition);
        UserDTO userDTO = PoJoConverterUtil.objectConverter(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public void add(UserDTO userDTO) {
        User user = PoJoConverterUtil.objectConverter(userDTO, User.class);
        user.setCreateTime(new Date());
        Long userId = idWorker.nextId();
        user.setUserId(userId);
        // 添加用户，默认的密码设置为123
        String encodePwd = BCrypt.hashpw(CommonConstants.DEFAULT_PWD, BCrypt.gensalt());
        user.setPassword(encodePwd);
        userDao.insertSelective(user);
        String positionIds = userDTO.getPositionIds();
        userPositionService.saveUserPosition(positionIds, userId);

    }

    @Override
    public void delete(String userIds) {
        List<Long> userIdList = org.apache.commons.lang3.StringUtils.isNotEmpty(userIds) ?
                new ArrayList(Arrays.asList(userIds.split(","))) : new ArrayList<>();
        if(CollectionUtils.isNotEmpty(userIdList)) {
            // 删除用户角色关联和用户职位关联
            userPositionService.deleteByUserIdList(userIdList);
            userRoleService.deleteByUserIdList(userIdList);
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("userId", userIdList);
            userDao.deleteByExample(example);
        }
    }

    @Override
    public UserDTO findByUserId(Long userId) {
        User user = userDao.selectByPrimaryKey(userId);
        UserDTO userDTO = PoJoConverterUtil.objectConverter(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public void edit(UserDTO userDTO) {
        User user = PoJoConverterUtil.objectConverter(userDTO, User.class);
        user.setModifyTime(new Date());
        userDao.updateByPrimaryKeySelective(user);
        if(!StringUtils.isEmpty(userDTO.getPositionIds())) {
            userPositionService.deleteByUserId(user.getUserId());
            userPositionService.saveUserPosition(userDTO.getPositionIds(), user.getUserId());
        }
    }

    @Override
    public Map<String, Object> getAssignRoleData(Long userId) {
        Map<String, Object> resultMap = new HashMap<>();
        // 得到所有角色数据
        List<Role> allRoleList = roleDao.selectAll();
        if(CollectionUtils.isNotEmpty(allRoleList)) {
            List<Map<String, Object>> allMapList = new ArrayList<>();
            for (Role role : allRoleList) {
                Map<String, Object> allMap = new HashMap<>();
                allMap.put("value", role.getRoleId());
                allMap.put("title", role.getName());
                allMapList.add(allMap);
            }
            resultMap.put("allRole", allMapList);
        }
        // 得到用户已经分配到的角色集合
        List<UserRole> userRoleList = userRoleService.findByUserId(userId);
        if(CollectionUtils.isNotEmpty(userRoleList)) {
            Long[] userRoleArr = new Long[userRoleList.size()];
            for (int i = 0; i < userRoleList.size(); i++) {
                UserRole userRole = userRoleList.get(i);
                userRoleArr[i] = userRole.getRoleId();
            }
            resultMap.put("myRole", userRoleArr);
        }

        return resultMap;
    }

    @Override
    public void assignRole(String roleIds, Long userId) {
        userRoleService.deleteByUserId(userId);
        userRoleService.saveUserRole(roleIds, userId);
    }
}
