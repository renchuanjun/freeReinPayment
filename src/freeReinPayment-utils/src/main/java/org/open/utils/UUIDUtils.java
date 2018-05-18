package org.open.utils;

import java.util.UUID;

public class UUIDUtils {
	
	
	/***
	 * 生成32位随机编号
	 * @return
	 */
	public static String uuid(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid.toString();
	}
}
