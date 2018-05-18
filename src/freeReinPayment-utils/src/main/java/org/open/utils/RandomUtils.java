package org.open.utils;

public class RandomUtils {

	public static String getRandom(){
		String ran = (int)(Math.random()*(10000-1-0+0)) + "";
		return ran;
	}
}
