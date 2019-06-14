package com.unifs.behavioranalysis.enums;

/**
 * @创建人 张恭雨
 * @创建时间 2018/8/16
 * @描述:枚举类型，返回类型状态定义
 */

public enum RespCode {

    SUCCESS(1, "请求成功"),
    DEFAULT(0,"请求失败"),
    INPRIVILEGE(403,"权限不足"),
    NOFOUND(404,"访问资源不存在"),
    ERROR(500,"服务器异常"),
    WARN(-1, "网络异常，请稍后重试");
    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.code=code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
