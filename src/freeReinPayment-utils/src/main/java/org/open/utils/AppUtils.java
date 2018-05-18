package org.open.utils;

import jdk.internal.dynalink.beans.StaticClass;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

public class AppUtils {

	/***
	 * 获取虚拟路径
	 * @param request
	 * @return
	 */
	public static String getVirtualPath(HttpServletRequest request){
		return request.getContextPath();
	}

	/**
	 * 判断请求是否为Ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * URL编码
	 * @param url
	 * @return
	 */
	public static String urlEncode(String url) {
		return URLEncoder.encode(url);
	}
	public static String urlEncode(String url,String encoding) throws UnsupportedEncodingException {
		return URLEncoder.encode(url,encoding);
	}
	/**
	 * Url解码
	 * @param url
	 * @return
	 */
	public static String urlDecode(String url) {
		return URLDecoder.decode(url);
	}
	
	public static String urlDecode(String url,String encoding) throws UnsupportedEncodingException {
		return URLDecoder.decode(url,encoding);
	}
	/**
	 * 获取下载文件的二进制流对象
	 * @param downloadPath
	 * @return
	 * @throws IOException
	 */
	public static byte[] getZipData(String downloadPath) throws IOException{
		byte[] zipbyte = null;
		File file = null;
		FileInputStream in = null;
		ByteArrayOutputStream outStream = null;
		try {
			file = new File(downloadPath); 
			int length = new Long(file.length()).intValue();
			zipbyte = new byte[length];
			in = new FileInputStream(file);
			outStream = new ByteArrayOutputStream();  
	        byte[] data = new byte[1024]; 
	        int count = -1;
	        while((count = in.read(data,0,1024)) != -1)  
	            outStream.write(data, 0, count);  
	          
	        data = null;  
	        zipbyte = outStream.toByteArray();
		} catch (Exception e) {
			throw e;
		}
		finally{
			if (outStream != null) {
				outStream.close();
				outStream = null;
			}
			if (in != null) {
				in.close();
				in = null;
			}
			if (file != null) {
				file = null;
			}
		}
		
		return zipbyte;
	}
	
	
	public static String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	/**
	 * 读取文件全部内容
	 * @param resourceLoader
	 * @param classpath
	 * @return
	 */
	public static String getFileContent(ResourceLoader resourceLoader,String classpath) throws IOException {
		Resource resource = resourceLoader.getResource(classpath);
		InputStream inputStream = resource.getInputStream();
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		BufferedReader reader = null;
		String tempString = "";
		StringBuffer buffer = new StringBuffer();
		try{
			reader = new BufferedReader(streamReader);

			while ((tempString = reader.readLine()) != null) {
				buffer.append(tempString);
			}
		}
		catch (IOException ex){
			throw ex;
		}
		finally {
			if (null != reader){
				reader.close();
			}
		}

		return buffer.toString();
	}
}
