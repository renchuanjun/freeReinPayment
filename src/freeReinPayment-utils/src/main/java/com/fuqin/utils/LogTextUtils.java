package com.fuqin.utils;

import org.apache.log4j.Logger;

public class LogTextUtils {
	//Logger实例   
    private Logger logger = null;  
   
    //将Log类封装为单例模式   
    private static LogTextUtils log;
      
     //构造函数，用于初始化Logger配置需要的属性   
    private LogTextUtils() {

         //获得当前目录路径      
        //String filePath=this.getClass().getResource("/").getPath()+"log4j.properties";
        //System.out.println(filePath);
        //logger所需的配置文件路径      
        //PropertyConfigurator.configure(filePath);
        //获得日志类logger的实例      
        logger = Logger.getLogger(LogTextUtils.class);
    }  
      
    public static LogTextUtils getLogger() {
        if(log != null) return log;  
        else return new LogTextUtils();
        
    }  

    public void wirteError(String message) {
    	logger.error(message);
	}
    
    public void wirteInfo(String message) {
    	logger.info(message);
	}
    
    public void wirteWarn(String message) {
    	logger.warn(message);
	}
    
    public void wirteDebug(String message) {
    	logger.debug(message);
	}
    
    public void wirteError(Exception ex,String[] items) {
    	StringBuffer stringBuffer = new StringBuffer();
    	if (items != null){
            for (String string : items) {
            	stringBuffer.append(string);
			}
    	}
        stringBuffer.append("\r\n错误信息:");
        stringBuffer.append(ex.getMessage());
        stringBuffer.append("\r\n错误对象:");
        stringBuffer.append(ex.toString());
        stringBuffer.append("\r\n错误轨迹:");
        
        StackTraceElement [] messages=ex.getStackTrace();
        int length=messages.length;
        for(int i=0;i<length;i++){
         stringBuffer.append(messages[i].toString());
         stringBuffer.append("\r\n");
        }
        
        //logger.error(stringBuffer.toString());
        System.out.println(stringBuffer.toString());
	}
    
}
