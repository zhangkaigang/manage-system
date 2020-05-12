package com.zhangkaigang.system.service.impl;

import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.base.utils.IdWorker;
import com.zhangkaigang.base.utils.PoJoConverterUtil;
import com.zhangkaigang.system.dao.AuthDao;
import com.zhangkaigang.system.pojo.dto.AuthDTO;
import com.zhangkaigang.system.pojo.po.Auth;
import com.zhangkaigang.system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/12
 * @Version:1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<AuthDTO> listTree() {
        List<Auth> authList = authDao.selectAll();
        List<AuthDTO> authDTOList = PoJoConverterUtil.objectListConverter(authList, AuthDTO.class);
        return authDTOList;
    }

    @Override
    public List<ZTreeNode> getAuthZTree() {
        return authDao.getAuthZTree();
    }

    @Override
    public void add(AuthDTO authDTO) {
        Auth auth = PoJoConverterUtil.objectConverter(authDTO, Auth.class);
        auth.setCreateTime(new Date());
        auth.setAuthId(idWorker.nextId());
        authDao.insertSelective(auth);
    }
}
