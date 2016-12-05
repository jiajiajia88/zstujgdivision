package com.szy.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String md5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] byteDigest = MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8"));
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < byteDigest.length; offset++) {
			i = byteDigest[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
		//return Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8")));
	}
}
