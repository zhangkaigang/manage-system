package com.zhangkaigang.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.utils.IdWorker;
import com.zhangkaigang.base.utils.PoJoConverterUtil;
import com.zhangkaigang.system.dao.PositionDao;
import com.zhangkaigang.system.pojo.dto.PositionDTO;
import com.zhangkaigang.system.pojo.po.Position;
import com.zhangkaigang.system.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/6
 * @Version:1.0
 */
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public PageInfo<PositionDTO> list() {
        List<Position> positionList = positionDao.selectAll();
        List<PositionDTO> deptDTOList = PoJoConverterUtil.objectListConverter(positionList, PositionDTO.class);
        return LayuiPageFactory.getPageInfo(deptDTOList);
    }

    @Override
    public void addPosition(PositionDTO positionDTO) {
        Position position = PoJoConverterUtil.objectConverter(positionDTO, Position.class);
        position.setPositionId(idWorker.nextId());
        position.setCreateTime(new Date());
        positionDao.insert(position);
    }
}
