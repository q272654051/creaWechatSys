package com.crea.www.commons.util;

import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.util.DigestUtils;
/**
 * 使用base64加密解密
 * @author moon
 *
 */
public class Base64andMD5Util {
	/**
	 *
	 *  使用Base64加密算法加密字符串
	 *return
	 */
	public static String encodeStr(String plainText){
	    byte[] b=plainText.getBytes();
	    Base64 base64=new Base64();
	    b=base64.encode(b);
	    String s=new String(b);
	    return s;
	}
	   
	/**
	 *
	 *  使用Base64解密
	 *return
	 */
	public static String decodeStr(String encodeStr){
	    byte[] b=encodeStr.getBytes();
	    Base64 base64=new Base64();
	    b=base64.decode(b);
	    String s=new String(b);
		return s;
	}
	/**
	 * md5 16 位加密
	 * @param param
	 * @return
	 */
	public static String MD5Str16(String param){
		byte[] list=  DigestUtils.md5Digest(param.getBytes());
		return list.toString();
	}
	/**
	 * MD5 32 位加密
	 * @param param
	 * @return
	 */
	public static String MD5Str32(String param){
		String falg=  DigestUtils.md5DigestAsHex(param.getBytes());
		return falg.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(Base64andMD5Util.MD5Str32("wds123")); 
		
	}
}
