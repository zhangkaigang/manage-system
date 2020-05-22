package com.zhangkaigang.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.pojo.node.LayuiTreeNode;
import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.service.BaseService;
import com.zhangkaigang.base.utils.PoJoConverterUtil;
import com.zhangkaigang.system.dao.DeptDao;
import com.zhangkaigang.system.pojo.dto.DeptDTO;
import com.zhangkaigang.system.pojo.po.Dept;
import com.zhangkaigang.system.service.DeptService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/18
 * @Version:1.0
 */
@Service
public class DeptServiceImpl extends BaseService implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<LayuiTreeNode> getDeptLayuiTree() {
        return deptDao.getDeptLayuiTree();
    }

    @Override
    public PageInfo<DeptDTO> list(String deptName, Long deptId) {
        List<Dept> deptList = deptDao.list(deptName, deptId);
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
        dept.setCreateTime(new Date());
        dept.setDeptId(idWorker.nextId());
        deptDao.insertSelective(dept);
    }

    @Override
    public void delete(Long deptId) {
        List<Dept> deptList = selectByParentId(deptId);
        if(CollectionUtils.isNotEmpty(deptList)) {
            for (Dept dept : deptList) {
                delete(dept.getDeptId());
            }
        }
        deptDao.deleteByPrimaryKey(deptId);

    }

    @Override
    public DeptDTO findByDeptId(Long deptId) {
        Dept dept = deptDao.selectByPrimaryKey(deptId);
        DeptDTO deptDTO = PoJoConverterUtil.objectConverter(dept, DeptDTO.class);
        return deptDTO;
    }

    /**
     * 根据父级ID查询部门列表
     * @param parentId
     * @return
     */
    public List<Dept> selectByParentId(Long parentId) {
        Example example = new Example(Dept.class);
        Example.Criteria criteria = example.createCriteria();
        // 添加条件
        criteria.andEqualTo("parentId", parentId);
        return deptDao.selectByExample(example);
    }

    @Override
    public void edit(DeptDTO deptDTO) {
        Dept dept = PoJoConverterUtil.objectConverter(deptDTO, Dept.class);
        dept.setModifyTime(new Date());
        deptDao.updateByPrimaryKeySelective(dept);
    }
}
