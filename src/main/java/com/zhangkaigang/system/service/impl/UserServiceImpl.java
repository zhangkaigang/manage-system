package com.zhangkaigang.system.service.impl;

import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.pojo.page.LayuiPageFactory;
import com.zhangkaigang.base.service.BaseService;
import com.zhangkaigang.base.utils.PoJoConverterUtil;
import com.zhangkaigang.system.dao.UserDao;
import com.zhangkaigang.system.pojo.dto.UserDTO;
import com.zhangkaigang.system.pojo.po.User;
import com.zhangkaigang.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/22
 * @Version:1.0
 */
@Service
public class UserServiceImpl  extends BaseService implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<UserDTO> list(String condition, Long deptId) {
        Example example = new Example(User.class);
        if(!StringUtils.isEmpty(condition)) {
            Example.Criteria criteria = example.createCriteria();
            criteria.orLike("loginName", "%" + condition + "%")
                    .orLike("userName", "%" + condition + "%");
        }
        if(!StringUtils.isEmpty(deptId)) {
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("deptId", deptId);
            example.and(criteria);
        }
        example.orderBy("createTime").desc();
        List<User> userList = userDao.selectByExample(example);
        List<UserDTO> userDTOList = PoJoConverterUtil.objectListConverter(userList, UserDTO.class);
        return LayuiPageFactory.getPageInfo(userDTOList);
    }

    @Override
    public void add(UserDTO userDTO) {

    }
}
