package com.jo.dy.ot.knowledge;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class GetInputStreamTest {

	/**
	 * 通过类加载器 从资源文件下(classpath)加载文件
	 * 
	 * @date 2018年9月18日 下午2:58:18
	 * @author weixueqiang
	 */
	@Test
	public void getClassPath1() throws IOException {
		InputStream in = GetInputStreamTest.class.getClassLoader().getResourceAsStream("test2.txt");
		byte[] data = new byte[1024 * 8];
		int len = 0;
		while ((len = in.read(data)) != -1) {
			System.out.println(new String(data, 0, len));
		}
		System.out.println("ok");
	}

	/**
	 * 路径前面加/表示从 从资源文件下(classpath)加载文件
	 * 
	 * @date 2018年9月18日 下午2:58:18
	 * @author weixueqiang
	 */
	@Test
	public void getClassPath2() throws IOException {
		InputStream in = GetInputStreamTest.class.getResourceAsStream("/test2.txt");
		byte[] data = new byte[1024 * 8];
		int len = 0;
		while ((len = in.read(data)) != -1) {
			System.out.println(new String(data, 0, len));
		}
		System.out.println("ok");
	}

	/**
	 * 路径前面不加/表示从 类同级目录下加载文件
	 * 
	 * @date 2018年9月18日 下午2:58:18
	 * @author weixueqiang
	 */
	@Test
	public void get() throws IOException {
		InputStream in = GetInputStreamTest.class.getResourceAsStream("test.txt");
		byte[] data = new byte[1024 * 8];
		int len = 0;
		while ((len = in.read(data)) != -1) {
			System.out.println(new String(data, 0, len));
		}
		System.out.println("ok");
	}

}
