package com.zhangkaigang.system.pojo.po;

import com.zhangkaigang.base.pojo.common.PublicPo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/22
 * @Version:1.0
 */
@Table(name = "sys_user")
public class User extends PublicPo {

    /**
     * 主键
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 状态：启用、禁用
     */
    @Column(name = "status")
    private String status;

    /**
     * 所属部门
     */
    @Column(name = "dept_id")
    private Long deptId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
