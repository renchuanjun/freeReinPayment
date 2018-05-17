package com.fuqin.http.model;

/**
 * 
 * @author zhengluyao
 * @parameter requestHeader 请求头
 * @parameter requestData 请求体
 */
public class RequestEntity {

	private RequestHeaderEntity requestHeader = new RequestHeaderEntity();
	private RequestDataEntity requestData = new RequestDataEntity();

	
	
	public RequestEntity() {
		super();
	}



	public RequestEntity(RequestHeaderEntity requestHeader, RequestDataEntity requestData) {
		this.requestHeader = requestHeader;
		this.requestData = requestData;
	}



	public RequestHeaderEntity getRequestHeader() {
		return requestHeader;
	}



	public void RequestHeaderEntity(RequestHeaderEntity requestHeader) {
		this.requestHeader = requestHeader;
	}



	public RequestDataEntity getRequestDataEntity() {
		return requestData;
	}



	public void setRequestDataEntity(RequestDataEntity requestData) {
		this.requestData = requestData;
	}



	@Override
	public String toString() {
		return "RequestEntity [requestHeader=" + requestHeader
				+ ", requestData=" + requestData + "]";
	}


	

}
