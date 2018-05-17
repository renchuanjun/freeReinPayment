package com.fuqin.utils;

public class MyFormatErrorException extends Exception {
	
	//返回异常信息
	private String msgDes; 
	
	
	public String getMsgDes() {
		return msgDes;
	}

	public MyFormatErrorException(){  
        super();  
    }  
	
	public MyFormatErrorException(String msgDes) {
		super();
		this.msgDes = msgDes;
		System.err.println(msgDes);
	}

}
