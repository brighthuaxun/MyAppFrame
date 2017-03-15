package com.example.bowen.myappframe.protocol.bean;

import java.io.Serializable;

/**
 * @author xuhui.han
 * 请求数据结果的基类
 */
public class Response implements Serializable {

	private String returnCode;
	private String returnMsg;

	public Response(){}

	public Response(String returnCode, String returnMsg){
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

}
