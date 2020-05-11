package com.zhangkaigang.system.pojo.dto;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/21
 * @Version:1.0
 */
public class DeptDTO {

    /**
     * 主键
     */
    private Long deptId;

    /**
     * 父部门id
     */
    private Long parentId;

    /**
     * 部门简称
     */
    private String simpleName;

    /**
     * 部门全称
     */
    private String fullName;

    /**
     * 描述
     */
    private String description;

    /**
     * 部门排序
     */
    private Integer sort;

    /**
     * 冗余字段-父级部门名称
     */
    private String parentName;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
