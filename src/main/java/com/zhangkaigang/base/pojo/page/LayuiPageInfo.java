package com.zhangkaigang.base.pojo.page;

import java.util.List;

/**
 * @Description:Layui分页结果的封装
 * @Author:zhang.kaigang
 * @Date:2020/4/16
 * @Version:1.0
 */
public class LayuiPageInfo {

    private Integer code = 0;

    private String msg = "请求成功";

    private List data;

    private long count;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
