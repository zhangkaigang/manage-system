package com.zhangkaigang.base.pojo.common;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/18
 * @Version:1.0
 */
public class PublicPo {

    /**
     * 创建人
     */
    @Column(name = "create_op")
    private Long createOp;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "modify_op")
    private Long modifyOp;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    public Long getCreateOp() {
        return createOp;
    }

    public void setCreateOp(Long createOp) {
        this.createOp = createOp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyOp() {
        return modifyOp;
    }

    public void setModifyOp(Long modifyOp) {
        this.modifyOp = modifyOp;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
