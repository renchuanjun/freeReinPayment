package com.fuqin.http.model;


/**
 * 
 * @author zhengluyao
 * @param
 * responseData 返回信息 加密是为加密后字符串
 * 非 加密环境为json转string后的字符串
 *
 */
public class ResponseDataEntity {

	private String data="";
	
	private String code="";
	
	private String msg="";

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ResponseDataEntity [data=" + data + ", code=" + code + ", msg="
				+ msg + "]";
	}



	




}
