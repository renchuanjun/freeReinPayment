package com.fuqin.http.model;

/**
 * @author zhengluyao
 * @params 
 * key:RSA加密后的AES-KEY;
 * appVersion:客户端版本号 wap端pc端可为空;
 * source:请求来源 IOS/ANDROID/WAP/PC;
 * deviceName:设备名称：张三的IPHONE;
 * deviceModel:设备类型：iPhone 8 s;
 * deviceId:设备id;
 * serviceVersion://接口版本号;
 */
public class RequestHeaderEntity {

	private String key = "";//RSA加密后的AES-KEY
	
	private String appVersion = "";//客户端版本号 wap端pc端可为空，一般不设置版本号

	private String source = "";//请求来源 IOS/ANDROID/WAP/PC

	private String deviceName = "";//设备名称：张三的IPHONE
	
	private String deviceModel = "";//设备类型：iPhone 8 s
	
	private String deviceId = "";//设备id
	
	private String serviceVersion = "";//接口版本号
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	@Override
	public String toString() {
		return "RequestHeaderEntity [key=" + key + ", appVersion=" + appVersion
				+ ", source=" + source + ", deviceName=" + deviceName
				+ ", deviceModel=" + deviceModel + ", deviceId=" + deviceId
				+ ", serviceVersion=" + serviceVersion + "]";
	}
	


	
	

	

}
