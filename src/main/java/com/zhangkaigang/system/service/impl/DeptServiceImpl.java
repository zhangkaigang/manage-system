package com.zhangkaigang.system.service.impl;

import com.zhangkaigang.base.pojo.node.LayuiTreeNode;
import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.system.dao.DeptDao;
import com.zhangkaigang.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<LayuiTreeNode> getDeptLayuiTree() {
        return deptDao.getDeptLayuiTree();
    }

    @Override
    public List<ZTreeNode> getDeptZTree() {
        return deptDao.getDeptZTree();
    }
}
