package org.open.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     * 针对字符串
     * @param strObj
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String GetMD5Code(String strObj) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(strObj,null);
    }

    /***
     * 针对对象
     * @param strObj
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String GetMD5CodeByObj(Object strObj) throws IOException {
        ByteArrayOutputStream byt=new ByteArrayOutputStream();
        ObjectOutputStream obj=new ObjectOutputStream(byt);
        obj.writeObject(strObj);
        byte[] array = byt.toByteArray();
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(new String(array),null);
    }


}

