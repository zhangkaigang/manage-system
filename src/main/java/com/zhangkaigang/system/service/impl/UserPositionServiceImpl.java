package com.zhangkaigang.system.service.impl;

import com.zhangkaigang.system.dao.UserPositionDao;
import com.zhangkaigang.system.pojo.po.UserPosition;
import com.zhangkaigang.system.service.UserPositionService;
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
public class UserPositionServiceImpl implements UserPositionService {

    @Autowired
    private UserPositionDao userPositionDao;

    @Override
    public void deleteByUserIdList(List<Long> userIdList) {
        Example example = new Example(UserPosition.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("userId", userIdList);
        userPositionDao.deleteByExample(example);
    }

    @Override
    public void deleteByUserId(Long userId) {
        Example example = new Example(UserPosition.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        userPositionDao.deleteByExample(example);
    }

    @Override
    public void deleteByPositionIdList(List<Long> positionIdList) {
        Example example = new Example(UserPosition.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("positionId", positionIdList);
        userPositionDao.deleteByExample(example);
    }

    @Override
    public void saveUserPosition(String positionIds, Long userId) {
        String[] positionIdsArray = positionIds.split(",");
        for (String positionId : positionIdsArray) {
            UserPosition userPosition = new UserPosition();
            userPosition.setUserId(userId);
            userPosition.setPositionId(Long.parseLong(positionId));
            userPositionDao.insert(userPosition);
        }
    }
}
