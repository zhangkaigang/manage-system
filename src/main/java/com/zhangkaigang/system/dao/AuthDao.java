package com.zhangkaigang.system.dao;

import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.system.pojo.po.Auth;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/12
 * @Version:1.0
 */
@Repository
public interface AuthDao extends Mapper<Auth> {

    /**
     * 获取ztree权限树
     * @return
     */
    @Select("select auth_id id, parent_id pId, name name, name title, case when parent_id = 0 then 'true' else 'false' end as open from sys_auth")
    List<ZTreeNode> getAuthZTree();

}
