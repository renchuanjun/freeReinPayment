package com.fuqin.utils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 高级的对称的加密解密的工具类，比DES更安全
 * Created by tao.yin on 2016/9/21.
 */
public class AesUtils {

	public static final String VIPARA = "0102030405060708";  
	public static final String bm = "UTF-8";
	
	public static String encrypt(String dataPassword, String cleartext) throws Exception, Exception{  
		IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());  
	    SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");  
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
	    cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);  
	    byte[] encryptedData = cipher.doFinal(cleartext.getBytes(bm));  
	    return Base64Utils.encode(encryptedData); 
	}  
	  
	public static String decrypt(String dataPassword, String encrypted) throws Exception, Exception {
		byte[] byteMi = Base64Utils.decode(encrypted);  
	    IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());  
	    SecretKeySpec key = new SecretKeySpec(dataPassword.getBytes(), "AES");  
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
	    cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);  
	    byte[] decryptedData = cipher.doFinal(byteMi);  
	  
	    return new String(decryptedData,bm);  
		
	} 
}
