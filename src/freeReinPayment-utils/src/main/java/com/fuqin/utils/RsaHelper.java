package com.fuqin.utils;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;



public class RsaHelper {
    /**
     * 生成RSA密钥对(默认密钥长度为1024)
     * 
     * @return
     */
    public static KeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(1024);
    }

    /**
     * 生成RSA密钥对
     * 
     * @param keyLength
     *            密钥长度，范围：512～2048
     * @return
     */
    public static KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(keyLength);
            KeyPair keyPair = kpg.genKeyPair();
            try {
            	String pubKey = encodePublicKeyToXml(keyPair.getPublic()).replace("\r\n", "").replace("\r", "").replace("\n", "");
				System.out.println("公钥Str:" +pubKey);
				String priKey =encodePrivateKeyToXml(keyPair.getPrivate()).replace("\r\n", "").replace("\r", "").replace("\n", "");
				System.out.println("私钥Str:" + priKey);
				PublicKey pubKeys =  decodePublicKeyFromXml(pubKey);
				System.out.println(pubKeys);
				
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
             
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 将公钥转换为XML字符串
     * @param key
     * @return
     * @throws Exception
     */
    public static String encodePublicKeyToXml(PublicKey key) throws Exception {
        if (!RSAPublicKey.class.isInstance(key)) {
            return null;
        }
        RSAPublicKey pubKey = (RSAPublicKey) key;
        StringBuilder sb = new StringBuilder();

        sb.append("<RSAKeyValue>");
        sb.append("<Modulus>")
                .append(Base64Utils.encode(pubKey.getModulus().toByteArray()))
                .append("</Modulus>");
        sb.append("<Exponent>")
                .append(Base64Utils.encode(pubKey.getPublicExponent()
                        .toByteArray())).append("</Exponent>");
        sb.append("</RSAKeyValue>");
        return sb.toString();
    }

    /**
     * 从字符串得到公钥
     * @param xml
     * @return
     * @throws Exception
     */
    public static PublicKey decodePublicKeyFromXml(String xml) throws Exception {
        xml = xml.replaceAll("\r", "").replaceAll("\n", "");
        BigInteger modulus = new BigInteger(1, Base64Utils.decode(StringHelper
                .GetMiddleString(xml, "<Modulus>", "</Modulus>")));
        BigInteger publicExponent = new BigInteger(1,
                Base64Utils.decode(StringHelper.GetMiddleString(xml,
                        "<Exponent>", "</Exponent>")));

        RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus,
                publicExponent);

        KeyFactory keyf;
        try {
            keyf = KeyFactory.getInstance("RSA");
            return keyf.generatePublic(rsaPubKey);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从字符串得到私钥
     * @param xml
     * @return
     * @throws Exception
     */
    public static PrivateKey decodePrivateKeyFromXml(String xml) throws Exception {
        xml = xml.replaceAll("\r", "").replaceAll("\n", "");
        BigInteger modulus = new BigInteger(1, Base64Utils.decode(StringHelper
                .GetMiddleString(xml, "<Modulus>", "</Modulus>")));
        BigInteger publicExponent = new BigInteger(1,
                Base64Utils.decode(StringHelper.GetMiddleString(xml,
                        "<Exponent>", "</Exponent>")));
        BigInteger privateExponent = new BigInteger(1,
                Base64Utils.decode(StringHelper.GetMiddleString(xml, "<D>",
                        "</D>")));
        BigInteger primeP = new BigInteger(1, Base64Utils.decode(StringHelper
                .GetMiddleString(xml, "<P>", "</P>")));
        BigInteger primeQ = new BigInteger(1, Base64Utils.decode(StringHelper
                .GetMiddleString(xml, "<Q>", "</Q>")));
        BigInteger primeExponentP = new BigInteger(1,
                Base64Utils.decode(StringHelper.GetMiddleString(xml, "<DP>",
                        "</DP>")));
        BigInteger primeExponentQ = new BigInteger(1,
                Base64Utils.decode(StringHelper.GetMiddleString(xml, "<DQ>",
                        "</DQ>")));
        BigInteger crtCoefficient = new BigInteger(1,
                Base64Utils.decode(StringHelper.GetMiddleString(xml,
                        "<InverseQ>", "</InverseQ>")));

        RSAPrivateCrtKeySpec rsaPriKey = new RSAPrivateCrtKeySpec(modulus,
                publicExponent, privateExponent, primeP, primeQ,
                primeExponentP, primeExponentQ, crtCoefficient);

        KeyFactory keyf;
        try {
            keyf = KeyFactory.getInstance("RSA");
            return keyf.generatePrivate(rsaPriKey);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从私钥里得到字符串
     * @param key
     * @return
     * @throws Exception
     */
    public static String encodePrivateKeyToXml(PrivateKey key) throws Exception {
        if (!RSAPrivateCrtKey.class.isInstance(key)) {
            return null;
        }
        RSAPrivateCrtKey priKey = (RSAPrivateCrtKey) key;
        StringBuilder sb = new StringBuilder();

        sb.append("<RSAKeyValue>");
        sb.append("<Modulus>")
                .append(Base64Utils.encode(priKey.getModulus().toByteArray()))
                .append("</Modulus>");
        sb.append("<Exponent>")
                .append(Base64Utils.encode(priKey.getPublicExponent()
                        .toByteArray())).append("</Exponent>");
        sb.append("<P>")
                .append(Base64Utils.encode(priKey.getPrimeP().toByteArray()))
                .append("</P>");
        sb.append("<Q>")
                .append(Base64Utils.encode(priKey.getPrimeQ().toByteArray()))
                .append("</Q>");
        sb.append("<DP>")
                .append(Base64Utils.encode(priKey.getPrimeExponentP()
                        .toByteArray())).append("</DP>");
        sb.append("<DQ>")
                .append(Base64Utils.encode(priKey.getPrimeExponentQ()
                        .toByteArray())).append("</DQ>");
        sb.append("<InverseQ>")
                .append(Base64Utils.encode(priKey.getCrtCoefficient()
                        .toByteArray())).append("</InverseQ>");
        sb.append("<D>")
                .append(Base64Utils.encode(priKey.getPrivateExponent()
                        .toByteArray())).append("</D>");
        sb.append("</RSAKeyValue>");
        return sb.toString();
    }

    // 用公钥加密
    public static byte[] encryptData(byte[] data, PublicKey pubKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }

    // 用私钥解密
    public static byte[] decryptData(byte[] encryptedData, PrivateKey priKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据指定私钥对数据进行签名(默认签名算法为"SHA1withRSA")
     * 
     * @param data
     *            要签名的数据
     * @param priKey
     *            私钥
     * @return
     */
    public static byte[] signData(byte[] data, PrivateKey priKey) {
        return signData(data, priKey, "SHA1withRSA");
    }

    /**
     * 根据指定私钥和算法对数据进行签名
     * 
     * @param data
     *            要签名的数据
     * @param priKey
     *            私钥
     * @param algorithm
     *            签名算法
     * @return
     */
    public static byte[] signData(byte[] data, PrivateKey priKey,
            String algorithm) {
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(priKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 用指定的公钥进行签名验证(默认签名算法为"SHA1withRSA")
     * 
     * @param data
     *            数据
     * @param sign
     *            签名结果
     * @param pubKey
     *            公钥
     * @return
     */
    public static boolean verifySign(byte[] data, byte[] sign, PublicKey pubKey) {
        return verifySign(data, sign, pubKey, "SHA1withRSA");
    }

    /**
     * 
     * @param data 数据
     * @param sign 签名结果
     * @param pubKey 公钥
     * @param algorithm 签名算法
     * @return
     */
    public static boolean verifySign(byte[] data, byte[] sign,
            PublicKey pubKey, String algorithm) {
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initVerify(pubKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    public static void main(String[] args) throws Exception {
    	String pubKey = "<RSAKeyValue><Modulus>AJyDD8H3G+plGcpcN3jsHYoRQ1/Ocxu9B9x9mn493GayyxzdRc4KNTofT18pdvqeQkb7CAEAPQTeHn5YZ4dMCpr6BOs5NAqbjBoux1ANIV+DDDNFsHxlhX+0pSz0Kw4735rpqQq/p+fRg00u00AX6F/Cmh7CaIEezjb2wuRZ36cv</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
    	String priKye = "<RSAKeyValue><Modulus>AJyDD8H3G+plGcpcN3jsHYoRQ1/Ocxu9B9x9mn493GayyxzdRc4KNTofT18pdvqeQkb7CAEAPQTeHn5YZ4dMCpr6BOs5NAqbjBoux1ANIV+DDDNFsHxlhX+0pSz0Kw4735rpqQq/p+fRg00u00AX6F/Cmh7CaIEezjb2wuRZ36cv</Modulus><Exponent>AQAB</Exponent><P>APk0RGwi4d6t8SZd3iHjLN9JBTEQWpbarWBJCVDkftXDrJyEJe0gCkxy6x/ynQWcpHGv4odO6ePeiFWn1E/WTCs=</P><Q>AKDHshd5MeqN40BfSWF1pVBOoePK8EzcXhvSU3pwsSraeDg/rzGRsNYbSTWESTnBzhAAnF92Kzp5vCWN707/2w0=</Q><DP>ALoufYwYx415gPg8GxBQTJ8JS/KrGDMqb2vHRUmFXOlSBqzs4fT2Ahu5o8a7mOqvbABv+ptCruWKxWrRN4LqRP8=</DP><DQ>IP04fnaAlnbshP3IKbYg6Fxp/TpbmfmxWZYLSNkskPVoq2ntwYb6OMQ6fj+ZYr9wT3pAm5m8fvAReccorhXzVQ==</DQ><InverseQ>LwIFAwiVSW9QBz9yjiEClR8PDN8JZoIGmGoZo/Abk5hXJ6N91dc0p7wiKstZSoSi+9tP0b16cFycwysASDc+Sg==</InverseQ><D>au+5koOuVSaiynrbhAl/3VgSOqGqUGeFuYcwSdUmhBzhXmVpTm/3EjFWX+TK4E3FZw4Z1+U7y9mf6B+rDOxNyaGuPNTeccwdALC/EBekKbu9xBORdvQEBbCjqNBd4CuIfI+mVFAYI0ufN008Jj8nbi2N8qV1MVPXQshcEuL4iLE=</D></RSAKeyValue>";
    	String str = "测试加密";
    	byte[] data = str.getBytes();
    	byte[] a = encryptData(data, decodePublicKeyFromXml(pubKey));
    	String b = Base64.encodeBase64String(a);
    	System.out.println(b);
    	
    	byte[] c = Base64.decodeBase64(b);
    	byte[] d = decryptData(c, decodePrivateKeyFromXml(priKye));
    	String r = new String(d);
    	
    	System.out.println(r);
	}
}
