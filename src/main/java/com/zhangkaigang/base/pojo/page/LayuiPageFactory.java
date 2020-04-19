package com.zhangkaigang.base.pojo.page;

import java.util.List;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/16
 * @Version:1.0
 */
public class LayuiPageFactory {



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
