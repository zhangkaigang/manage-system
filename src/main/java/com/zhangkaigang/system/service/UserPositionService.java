package com.zhangkaigang.system.service;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/6/18
 * @Version:1.0
 */
public interface UserPositionService {

    /**
     * 根据用户ID列表删除用户职位关联数据
     * @param userIdList
     */
    void deleteByUserIdList(List<Long> userIdList);

    /**
     * 根据单个用户ID删除用户职位关联数据
     * @param userId
     */
    void deleteByUserId(Long userId);

    /**
     * 根据职位ID列表删除用户职位关联数据
     * @param positionIdList
     */
    void deleteByPositionIdList(List<Long> positionIdList);

    /**
     * 插入用户职位关联
     * @param positionIds
     * @param userId
     */
    void saveUserPosition(String positionIds, Long userId);
}
