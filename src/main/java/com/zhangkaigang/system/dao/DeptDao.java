package com.zhangkaigang.system.dao;

import com.zhangkaigang.base.pojo.node.LayuiTreeNode;
import com.zhangkaigang.base.pojo.node.ZTreeNode;
import com.zhangkaigang.system.pojo.po.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/18
 * @Version:1.0
 */
@Repository
public interface DeptDao extends Mapper<Dept> {

    /**
     * 获取layui风格的部门树
     * @return
     */
    @Select("select dept_id id, p_id pId, simple_name title, case when p_id = 0 then 'true' else 'false' end as spread from sys_dept")
    List<LayuiTreeNode> getDeptLayuiTree();

    /**
     * 获取ztree部门树
     * @return
     */
    @Select("select dept_id id, p_id pId, simple_name name, full_name title, case when p_id = 0 then 'true' else 'false' end as open from sys_dept")
    List<ZTreeNode> getDeptZTree();

    /**
     * 列表查询
     * @param deptName
     * @param deptId
     * @return
     */
    List<Dept> list(@Param("deptName") String deptName, @Param("deptId") Long deptId);



}
