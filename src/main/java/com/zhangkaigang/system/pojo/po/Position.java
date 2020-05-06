package com.zhangkaigang.system.pojo.po;

import com.zhangkaigang.base.pojo.common.PublicPo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/6
 * @Version:1.0
 */
@Table(name = "sys_position")
public class Position extends PublicPo {

    /**
     * 主键
     */
    @Id
    @Column(name = "position_id")
    private Long positionId;

    /**
     * 职位名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 状态：启用、禁用
     */
    @Column(name = "status")
    private String status;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 职位描述
     */
    @Column(name = "description")
    private String description;

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
