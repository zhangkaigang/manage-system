package com.zhangkaigang.base.constant;

import com.zhangkaigang.system.dao.AuthDao;
import com.zhangkaigang.system.dao.DeptDao;
import com.zhangkaigang.system.dao.PositionDao;
import com.zhangkaigang.system.dao.UserPositionDao;
import com.zhangkaigang.system.pojo.po.Auth;
import com.zhangkaigang.system.pojo.po.Dept;
import com.zhangkaigang.system.pojo.po.Position;
import com.zhangkaigang.system.pojo.po.UserPosition;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/8
 * @Version:1.0
 */
@Component
public class CacheFactory {

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private AuthDao authDao;

    @Autowired
    private UserPositionDao userPositionDao;

    @Autowired
    private PositionDao positionDao;

    /**
     * 获取部门名称
     * @param deptId
     * @return
     */
    @Cacheable(value = CacheConstants.CONSTANT_CACHE, key = "'" + CacheConstants.DEPT_NAME + "'+#deptId")
    public String getDeptName(Long deptId) {
        if (deptId == null) {
            return "";
        } else if (deptId == 0L) {
            return "顶级";
        } else {
            Dept dept = deptDao.selectByPrimaryKey(deptId);
            if(dept != null && StringUtils.isNotEmpty(dept.getFullName())) {
                return dept.getFullName();
            }
            return "";
        }
    }

    /**
     * 获取权限名称
     * @param authId
     * @return
     */
    @Cacheable(value = CacheConstants.CONSTANT_CACHE, key = "'" + CacheConstants.AUTH_NAME + "'+#authId")
    public String getAuthName(Long authId) {
        if (authId == null) {
            return "";
        } else if (authId == 0L) {
            return "顶级";
        } else {
            Auth auth = authDao.selectByPrimaryKey(authId);
            if(auth != null && StringUtils.isNotEmpty(auth.getName())) {
                return auth.getName();
            }
            return "";
        }
    }

    /**
     * 根据用户id得到职位集合
     * @param userId
     * @return
     */
    public String getPositionNames(Long userId) {
        if(userId == null) {
            return "";
        } else {
            Example example = new Example(UserPosition.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId", userId);
            List<UserPosition> userPositionList = userPositionDao.selectByExample(example);
            List<Position> positionList = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(userPositionList)) {
                for (UserPosition userPosition : userPositionList) {
                    Position position = positionDao.selectByPrimaryKey(userPosition.getPositionId());
                    positionList.add(position);
                }
            }
            String positionNames = positionList.stream().map(Position::getName).collect(Collectors.joining(","));
            return positionNames;
        }
    }

}
