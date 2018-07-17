package org.open.utils;

public class MyException extends Exception {
	
	//返回异常信息
	private String msgDes; 
	
	
	public String getMsgDes() {
		return msgDes;
	}

	public MyException(){
        super();  
    }  
	
	public MyException(String msgDes) {
		super();
		this.msgDes = msgDes;
		System.err.println(msgDes);
	}

}
