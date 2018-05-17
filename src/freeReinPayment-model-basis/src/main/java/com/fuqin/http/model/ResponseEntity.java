package com.fuqin.http.model;

/**
 * 
 * @author zhengluyao
 * @parameter responseHeader 请求头
 * @parameter responseData 请求体
 */
public class ResponseEntity {

	private ResponseHeaderEntity responseHeaderEntity = new ResponseHeaderEntity();
	private ResponseDataEntity responseDataEntity = new ResponseDataEntity();

	
	
	public ResponseEntity(ResponseHeaderEntity responseHeaderEntity, ResponseDataEntity responseDataEntity) {
		this.responseHeaderEntity = responseHeaderEntity;
		this.responseDataEntity = responseDataEntity;
	}



	public ResponseHeaderEntity getResponseHeader() {
		return responseHeaderEntity;
	}



	public void setResponseHeader(ResponseHeaderEntity responseHeaderEntity) {
		this.responseHeaderEntity = responseHeaderEntity;
	}



	public ResponseDataEntity getResponseData() {
		return responseDataEntity;
	}



	public void setResponseData(ResponseDataEntity responseDataEntity) {
		this.responseDataEntity = responseDataEntity;
	}



	@Override
	public String toString() {
		return "ResponseEntity [responseHeaderEntity=" + responseHeaderEntity
				+ ", responseDataEntity=" + responseDataEntity + "]";
	}








	

}
