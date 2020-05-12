package com.zhangkaigang.system.pojo.po;

import com.zhangkaigang.base.pojo.common.PublicPo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/12
 * @Version:1.0
 */
@Table(name = "sys_auth")
public class Auth extends PublicPo {

    /**
     * 主键
     */
    @Id
    @Column(name = "auth_id")
    private Long authId;

    /**
     * 权限编码
     */
    @Column(name = "auth_code")
    private String authCode;

    /**
     * 父级ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 权限分类，1菜单，2功能
     */
    @Column(name = "auth_type")
    private Integer authType;

    /**
     * 层级
     */
    @Column(name = "levels")
    private Integer levels;

    /**
     * 权限名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 权限路径
     */
    @Column(name = "url")
    private String url;

    /**
     * 权限图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
}
