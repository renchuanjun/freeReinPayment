package com.fuqin.http.model;


/**
 * 
 * @author zhengluyao
 * @param data 请求参数  字符串类型，加密环境为AES加密后字符串 非加密环境为json转string后的字符串
 * 
 */
public class RequestDataEntity {

	public String data="";
	
	private String timeStamp = "";//请求时间戳

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "RequestDataEntity [data=" + data + ", timeStamp=" + timeStamp
				+ "]";
	}

	

	




}
