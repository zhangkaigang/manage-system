package com.zhangkaigang.system.dao;

import com.zhangkaigang.system.pojo.po.Position;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/6
 * @Version:1.0
 */
@Repository
public interface PositionDao extends Mapper<Position> {

    /**
     * 查询用户拥有的下拉多选职位列表
     * @param userId
     * @return
     */
    List<Map<String, Object>> findPositionsByUserId(@Param("userId") Long userId);

    /**
     * 查询下拉多选框的职位列表
     * @return
     */
    List<Map<String, Object>> selectAllPositions();
}
