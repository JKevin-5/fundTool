package com.kevin.funds.util.Encrypt;

import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class EncryptUtil {

	/*
	 * 使用Apache工具类实现SHA-256加密
	 * @param str 加密后的报文
	 * @return
	 * */
	public static String encrypt(String str){
		MessageDigest messageDigest;
		String encodeStr="";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte [] hash = messageDigest.digest(str.getBytes("UTF-8"));
			encodeStr = Hex.encodeHexString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}
}
