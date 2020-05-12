package com.zhangkaigang.base.constant;

import com.zhangkaigang.system.dao.AuthDao;
import com.zhangkaigang.system.dao.DeptDao;
import com.zhangkaigang.system.pojo.po.Auth;
import com.zhangkaigang.system.pojo.po.Dept;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/5/8
 * @Version:1.0
 */
@Component
public class CacheFactory {

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private AuthDao authDao;

    /**
     * 获取部门名称
     * @param deptId
     * @return
     */
    @Cacheable(value = CacheConstants.CONSTANT_CACHE, key = "'" + CacheConstants.DEPT_NAME + "'+#deptId")
    public String getDeptName(Long deptId) {
        if (deptId == null) {
            return "";
        } else if (deptId == 0L) {
            return "顶级";
        } else {
            Dept dept = deptDao.selectByPrimaryKey(deptId);
            if(dept != null && StringUtils.isNotEmpty(dept.getFullName())) {
                return dept.getFullName();
            }
            return "";
        }
    }

    /**
     * 获取权限名称
     * @param authId
     * @return
     */
    @Cacheable(value = CacheConstants.CONSTANT_CACHE, key = "'" + CacheConstants.AUTH_NAME + "'+#authId")
    public String getAuthName(Long authId) {
        if (authId == null) {
            return "";
        } else if (authId == 0L) {
            return "顶级";
        } else {
            Auth auth = authDao.selectByPrimaryKey(authId);
            if(auth != null && StringUtils.isNotEmpty(auth.getName())) {
                return auth.getName();
            }
            return "";
        }
    }

}
