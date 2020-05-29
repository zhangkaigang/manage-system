package com.zhangkaigang.system.dao;

import com.zhangkaigang.system.pojo.po.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/6
 * @Version:1.0
 */
@Repository
public interface PositionDao extends Mapper<Position> {

    /**
     * 职位列表查询，如果有用户则查询用户拥有的职位列表
     * @param userId
     * @return
     */
    List<Position> findPositionsByUserId(@Param("userId") Long userId);
}
