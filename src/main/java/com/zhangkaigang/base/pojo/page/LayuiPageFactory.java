package com.zhangkaigang.base.pojo.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangkaigang.base.utils.SpringUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/16
 * @Version:1.0
 */
public class LayuiPageFactory {

    /**
     * 处理分页
     * @param list
     * @return
     */
    public static PageInfo getPageInfo(List<?> list) {
        HttpServletRequest request = SpringUtil.getRequest();

        int currentPage = 1;
        int pageSize = 10;

        // 第几页
        String page = request.getParameter("page");
        if (StringUtils.isNotEmpty(page)) {
            currentPage = Integer.parseInt(page);
        }

        // 每页多少条数据
        String limit = request.getParameter("limit");
        if (StringUtils.isNotEmpty(limit)) {
            pageSize = Integer.parseInt(limit);
        }

        PageHelper.startPage(currentPage, pageSize);
        PageInfo<?> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }



    /**
     * 创建layui能识别的分页响应参数
     * @param count
     * @param data
     * @return
     */
    public static LayuiPageInfo createPageInfo(long count, List data) {
        LayuiPageInfo result = new LayuiPageInfo();
        result.setCount(count);
        result.setData(data);
        return result;
    }

}
