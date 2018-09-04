package com.jo.dy.ot.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {

	private static String algorithm="SHA1";
	
	public static String digest(String text) {
		if (text == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest instance = MessageDigest.getInstance(algorithm);
			instance.update(text.getBytes());
			byte[] digest = instance.digest();
			for (byte data : digest) {
				String tempStr = Integer.toHexString(data & 0xff);
				sb.append(tempStr);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return sb.toString();
	}

	/**
	 * 多线程测试
	 * @date 2018年9月4日 上午10:28:12
	 * @author weixueqiang
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 200; i++) {
			Thread1 thread1 = new Thread1(i % 2 + "");
			thread1.start();
		}

	}

}

class Thread1 extends Thread {

	private String text;

	public Thread1(String text) {
		this.text = text;
	}

	@Override
	public void run() {
		String digest = SHA1.digest(text);
		boolean flag = true;
		if ("0".equals(text)) {
			flag = digest.equals("b6589fc6abdc82cf12099d1c2d4ab994e841c");
			// System.out.println(text+"->:"+digest.equals("b6589fc6abdc82cf12099d1c2d4ab994e841c")+"->"+digest);
		} else {
			flag = digest.equals("356a192b7913b04c54574d18c28d46e6395428ab");
			// System.out.println(text+"->:"+digest.equals("356a192b7913b04c54574d18c28d46e6395428ab")+"->"+digest);
		}
		if (!flag) {
			System.out.println("不安全!");
		}
	}
}
