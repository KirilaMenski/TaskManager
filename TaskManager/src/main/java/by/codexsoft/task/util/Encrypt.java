package by.codexsoft.task.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Encrypt {
	
	public static String encodingPassword(String password) {
		String md5Hex = DigestUtils.md5Hex(password);
		return md5Hex;
	}

}
