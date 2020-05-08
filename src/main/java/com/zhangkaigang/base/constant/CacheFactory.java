package com.zhangkaigang.base.constant;

import com.zhangkaigang.system.dao.DeptDao;
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

}
