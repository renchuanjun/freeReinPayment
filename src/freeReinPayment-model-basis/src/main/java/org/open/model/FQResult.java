package org.open.model;

import java.util.ArrayList;

public class FQResult<T> implements java.io.Serializable {

	private Exception exception;
	
	private boolean isSuccess;
	
	private T result;
	
	private String message;
	
	private String detailInfo;
	
	private String stateCode;
	
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
		//打印输出在控制台信息，如果是发布程序需要注释掉
//		System.out.println(exception.getMessage());
//		exception.printStackTrace();
	}

	public boolean getSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "FQResult [exception=" + exception + ", isSuccess=" + isSuccess
				+ ", result=" + result + ", message=" + message + "]";
	}
	
}
