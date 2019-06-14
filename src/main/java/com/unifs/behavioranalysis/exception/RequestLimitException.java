package com.unifs.behavioranalysis.exception;

/**
 * @创建人 张恭雨
 * @创建时间 2018/12/18
 * @描述：请求次数限制异常类
 **/
public class RequestLimitException extends Exception {
    private static final long serialVersionUID = 1364225358754654702L;

    public RequestLimitException(){
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message){
        super(message);
    }
}
