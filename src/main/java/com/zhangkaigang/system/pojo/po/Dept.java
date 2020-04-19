package com.zhangkaigang.system.pojo.po;

import com.zhangkaigang.base.pojo.common.PublicPo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/18
 * @Version:1.0
 */
@Table(name = "sys_dept")
public class Dept extends PublicPo {

    /**
     * 主键
     */
    @Id
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 父部门id
     */
    @Column(name = "p_id")
    private Long pId;

    /**
     * 部门简称
     */
    @Column(name = "simple_name")
    private String simpleName;

    /**
     * 部门全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 部门排序
     */
    @Column(name = "sort")
    private Integer sort;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
