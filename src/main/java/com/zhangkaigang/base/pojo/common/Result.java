package com.zhangkaigang.base.pojo.common;

/**
 * @Description:自定义响应结构
 * @Author:zhang.kaigang
 * @Date:2020/4/21
 * @Version:1.0
 */
public class Result {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 返回码
     */
    private Integer statusCode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public Result(Boolean success) {
        this.success = success;
    }

    public Result(Boolean success, Integer statusCode) {
        this.success = success;
        this.statusCode = statusCode;
    }

    public Result(Boolean success, Integer statusCode, String message) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
    }

    public Result(Boolean success, Integer statusCode, String message, Object data) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
