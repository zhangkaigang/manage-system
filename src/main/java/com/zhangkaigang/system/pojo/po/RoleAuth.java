package com.zhangkaigang.system.pojo.po;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/17
 * @Version:1.0
 */
@Table(name = "sys_role_auth")
public class RoleAuth {

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @Column(name = "auth_id")
    private Long authId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }
}
