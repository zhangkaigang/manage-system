package com.zhangkaigang.system.pojo.po;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/22
 * @Version:1.0
 */
@Table(name = "sys_user_position")
public class UserPosition {

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 职位id
     */
    @Column(name = "position_id")
    private Long positionId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}
