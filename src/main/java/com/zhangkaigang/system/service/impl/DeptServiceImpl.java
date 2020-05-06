package com.zhangkaigang.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.pojo.node.LayuiTreeNode;
import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.utils.IdWorker;
import com.zhangkaigang.base.utils.PoJoConverterUtil;
import com.zhangkaigang.system.dao.DeptDao;
import com.zhangkaigang.system.pojo.dto.DeptDTO;
import com.zhangkaigang.system.pojo.po.Dept;
import com.zhangkaigang.system.service.DeptService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/18
 * @Version:1.0
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<LayuiTreeNode> getDeptLayuiTree() {
        return deptDao.getDeptLayuiTree();
    }

    @Override
    public PageInfo<DeptDTO> list() {
        List<Dept> deptList = deptDao.selectAll();
        List<DeptDTO> deptDTOList = PoJoConverterUtil.objectListConverter(deptList, DeptDTO.class);
        return LayuiPageFactory.getPageInfo(deptDTOList);
    }

    @Override
    public List<ZTreeNode> getDeptZTree() {
        return deptDao.getDeptZTree();
    }

    @Override
    public void add(DeptDTO deptDTO) {
        Dept dept = PoJoConverterUtil.objectConverter(deptDTO, Dept.class);
        dept.setDeptId(idWorker.nextId());
        deptDao.insert(dept);
    }

    @Override
    public void delete(Long deptId) {
        List<Dept> deptList = selectByPid(deptId);
        if(CollectionUtils.isNotEmpty(deptList)) {
            for (Dept dept : deptList) {
                delete(dept.getDeptId());
            }
        }
        deptDao.deleteByPrimaryKey(deptId);

    }

    /**
     * 根据父级ID查询部门列表
     * @param pId
     * @return
     */
    public List<Dept> selectByPid(Long pId) {
        Example example = new Example(Dept.class);
        Example.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andEqualTo("pId", pId);
        return deptDao.selectByExample(example);
    }
}
