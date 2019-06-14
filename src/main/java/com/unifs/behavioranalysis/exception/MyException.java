package com.unifs.behavioranalysis.exception;

import com.unifs.behavioranalysis.base.Resp;
import lombok.Data;

/**
 * 自定义异常

 */
@Data
public class MyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String appmsg;
    private int appcode = 500;
    
    public MyException(String appmsg) {
		super(appmsg);
		this.appmsg = appmsg;
	}
	
	public MyException(String appmsg, Throwable e) {
		super(appmsg, e);
		this.appmsg = appmsg;
	}
	
	public MyException(String appmsg, int appcode) {
		super(appmsg);
		this.appmsg = appmsg;
		this.appcode = appcode;
	}
	
	public MyException(String msg, int code, Throwable e) {
		super(msg, e);
		this.appmsg = msg;
		this.appcode = code;
	}

	public MyException(Resp resp) {
		super(resp.getMsg());
		this.appmsg = resp.getMsg();
		this.appcode = resp.getCode();
	}

	
}
