package com.unifs.behavioranalysis.base;

import com.unifs.behavioranalysis.enums.RespCode;
import lombok.Data;

/**
 * @创建人 张恭雨
 * @创建时间 2018/8/16
 * @描述 reustful风格返回数据信息实体类
 */
@Data //注解，自动配置getter setter方法
public class Resp {
    private int code;
    private String msg;
    private Object data;

    public Resp(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public Resp(RespCode respCode, Object data) {
        this(respCode);
        this.data = data;
    }

}
