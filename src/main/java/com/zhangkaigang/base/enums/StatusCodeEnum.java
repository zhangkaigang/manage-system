package com.zhangkaigang.base.enums;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2020/4/21
 * @Version:1.0
 */
public enum StatusCodeEnum {
    /**
     * 成功
     */
    OK(2000),

    /**
     * 失败
     */
    ERROR(2001);

    private int statusCode;

    StatusCodeEnum(int value) {
        statusCode = value;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
