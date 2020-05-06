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
     * @return
     */
    PageInfo<PositionDTO> list();

    /**
     * 新增职位
     * @param positionDTO
     */
    void addPosition(PositionDTO positionDTO);
}
