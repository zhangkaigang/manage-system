package com.zhangkaigang.system.dao;

import com.zhangkaigang.system.pojo.po.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/17
 * @Version:1.0
 */
@Repository
public interface RoleDao extends Mapper<Role> {
}
