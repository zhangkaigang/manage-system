package com.zhangkaigang.base.pojo.node;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:layui树形组件节点
 * @Author:zhang.kaigang
 * @Date:2020/4/15
 * @Version:1.0
 */
public class LayuiTreeNode {

    /**
     * 节点id
     */
    private Long id;

    /**
     * 父节点id
     */
    private Long pId;

    /**
     * 节点标题
     */
    private String title;

    /**
     * 节点是否初始展开
     */
    private Boolean spread;

    /**
     * 节点是否初始为选中状态
     */
    private Boolean checked;

    /**
     * 节点是否为禁用状态
     */
    private Boolean disabled;

    /**
     * 子节点
     */
    private List<LayuiTreeNode> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public List<LayuiTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<LayuiTreeNode> children) {
        this.children = children;
    }
}
