package com.zhangkaigang.system.service;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.system.pojo.dto.PositionDTO;

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
     * 编辑职位
     * @param positionDTO
     */
    void edit(PositionDTO positionDTO);
}
