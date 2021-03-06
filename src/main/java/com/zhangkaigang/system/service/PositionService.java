package com.zhangkaigang.system.service;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.system.pojo.dto.PositionDTO;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/6
 * @Version:1.0
 */
public interface PositionService {

    /**
     * 职位列表分页
     * @param name
     * @return
     */
    PageInfo<PositionDTO> list(String name);

    /**
     * 新增职位
     * @param positionDTO
     */
    void add(PositionDTO positionDTO);

    /**
     * 删除
     * @param positionIds
     */
    void delete(String positionIds);

    /**
     * 根据ID查找职位
     * @param positionId
     * @return
     */
    PositionDTO findByPositionId(Long positionId);

    /**
     * 编辑职位
     * @param positionDTO
     */
    void edit(PositionDTO positionDTO);

    /**
     * 查询下拉多选框的职位列表
     * @param userId
     * @return
     */
    List<Map<String, Object>> findAllPositions(Long userId);
}
