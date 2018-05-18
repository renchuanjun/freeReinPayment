package org.open.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class DownloadUtils {

	/**
	 * 下载
	 * @param path
	 * @param file_Name
	 * @throws IOException
	 */
    public static ResponseEntity<byte[]> download(String path,String file_Name) throws IOException {    
        File file=new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        String fileName=new String(file_Name.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
        byte[] readFileToByteArray = readFileToByteArray(file);
        return new ResponseEntity<byte[]>(readFileToByteArray,headers, HttpStatus.OK);    
    }
    
    /**
     * 将文件 转化 成 byte 数组
     * @param file
     * @return
     * @throws IOException
     */
    private static byte[] readFileToByteArray(File file) throws IOException{
    	InputStream in = null;
    	try {
            in = openInputStream(file);
            return toByteArray(in, file.length());
        } finally {
           closeQuietly(in);
        }
    }
    
    
    /**
     * 判断文件是否存在并可读 
     * @param file
     * @return
     * @throws IOException
     */
    private static FileInputStream openInputStream(File file) throws IOException{
    	if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canRead() == false) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }
    
    /***
     * Reads the contents of a file into a byte array.
     * The file is always closed.
     * @param input
     * @param size
     * @return
     * @throws IOException
     */
    private static byte[] toByteArray(InputStream input, long size) throws IOException {
        if(size > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + size);
        }
        return toByteArray(input, (int) size);
      }
    
    /**
     * 关闭流之前传唤成Closeable
     * @param input
     */
    private static void closeQuietly(InputStream input) {
        closeQuietly((Closeable)input);
    }
    
    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
    
    private  static byte[] toByteArray(InputStream input, int size) throws IOException {

        if (size < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + size);
        }
        if (size == 0) {
            return new byte[0];
        }
        byte[] data = new byte[size];
        int offset = 0;
        int readed;
        while (offset < size && (readed = input.read(data, offset, size - offset)) != -1) {
            offset += readed;
        }
        if (offset != size) {
            throw new IOException("Unexpected readed size. current: " + offset + ", excepted: " + size);
        }
        return data;
    }
}
