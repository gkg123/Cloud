package org.oracle.note.util;

import java.security.MessageDigest;


import org.apache.commons.codec.binary.Base64;


public class NoteUtil {
	public static String md5(String msg) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = msg.getBytes();
		byte[] digest = md.digest(b);
		String result = Base64.encodeBase64String(digest);
		return result; 
	}
	public static void main(String[] args) throws Exception {
		System.out.println(md5("123456"));
	}
}
