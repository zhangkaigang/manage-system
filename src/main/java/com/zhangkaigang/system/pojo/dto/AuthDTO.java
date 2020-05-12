package com.zhangkaigang.system.pojo.dto;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/12
 * @Version:1.0
 */
public class AuthDTO {

    /**
     * 主键
     */
    private Long authId;

    /**
     * 权限编码
     */
    private String authCode;

    /**
     * 父级ID
     */
    private Long parentId;

    /**
     * 权限分类，1菜单，2功能
     */
    private Integer authType;

    /**
     * 层级
     */
    private Integer levels;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限路径
     */
    private String url;

    /**
     * 权限图标
     */
    private String icon;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 冗余字段-父级权限名称
     */
    private String parentName;

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


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
