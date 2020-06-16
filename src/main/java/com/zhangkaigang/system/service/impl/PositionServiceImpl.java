package com.zhangkaigang.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.service.BaseService;
import com.zhangkaigang.base.utils.PoJoConverterUtil;
import com.zhangkaigang.system.dao.PositionDao;
import com.zhangkaigang.system.dao.UserPositionDao;
import com.zhangkaigang.system.pojo.dto.PositionDTO;
import com.zhangkaigang.system.pojo.po.Position;
import com.zhangkaigang.system.pojo.po.UserPosition;
import com.zhangkaigang.system.service.PositionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/6
 * @Version:1.0
 */
@Service
public class PositionServiceImpl extends BaseService implements PositionService {

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private UserPositionDao userPositionDao;

    @Override
    public PageInfo<PositionDTO> list(String name) {
        Example example = new Example(Position.class);
        if(StringUtils.isNotEmpty(name)) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("name", "%" + name + "%");
        }
        example.orderBy("sort").asc();
        List<Position> positionList = positionDao.selectByExample(example);
        List<PositionDTO> deptDTOList = PoJoConverterUtil.objectListConverter(positionList, PositionDTO.class);
        return LayuiPageFactory.getPageInfo(deptDTOList);
    }

    @Override
    public void add(PositionDTO positionDTO) {
        Position position = PoJoConverterUtil.objectConverter(positionDTO, Position.class);
        position.setPositionId(idWorker.nextId());
        position.setCreateTime(new Date());
        positionDao.insertSelective(position);
    }

    @Override
    public void delete(String positionIds) {
        List<Long> positionIdsList = StringUtils.isNotEmpty(positionIds) ?
                new ArrayList(Arrays.asList(positionIds.split(","))) : new ArrayList<>();
        Example example = new Example(Position.class);
        Example.Criteria criteria = example.createCriteria();
        // Arrays.asList，则不能用remove和add这样的方法，因为其返回的类型是 Aarrays$ArrayList 并不是 ArrayList
        criteria.andIn("positionId", positionIdsList);
        positionDao.deleteByExample(example);
    }

    @Override
    public PositionDTO findByPositionId(Long positionId) {
        Position position = positionDao.selectByPrimaryKey(positionId);
        PositionDTO positionDTO = PoJoConverterUtil.objectConverter(position, PositionDTO.class);
        return positionDTO;
    }

    @Override
    public void edit(PositionDTO positionDTO) {
        Position position = PoJoConverterUtil.objectConverter(positionDTO, Position.class);
        position.setModifyTime(new Date());
        positionDao.updateByPrimaryKeySelective(position);
    }

    @Override
    public List<Map<String, Object>> findAllPositions(Long userId) {
        List<Map<String, Object>> positionList = positionDao.selectAllPositions();
        if(userId != null) {
            Example example = new Example(UserPosition.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId", userId);
            List<UserPosition> userPositionList = userPositionDao.selectByExample(example);
            if(CollectionUtils.isNotEmpty(userPositionList)) {
                for (Map<String, Object> positionMap : positionList) {
                    Object positionId = positionMap.get("positionId");
                    for (UserPosition userPosition : userPositionList) {
                        if (userPosition.getPositionId().equals(positionId)) {
                            positionMap.put("selected", true);
                        }
                    }
                }

            }
        }
        return positionList;
    }
}
