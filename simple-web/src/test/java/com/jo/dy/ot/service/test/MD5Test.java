package com.jo.dy.ot.service.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

import com.jo.dy.ot.util.MySimpleHash;

public class MD5Test {

	@Test
	public void test1() {
		String source="zs123";
		ByteSource bytes = ByteSource.Util.bytes("salt");
		System.out.println(bytes.toString());
		Md5Hash md5Hash = new Md5Hash(source, bytes); 
		System.out.println(md5Hash.toString());
		System.out.println(md5Hash.toBase64());
		System.out.println(md5Hash.toHex());
		Sha256Hash sha256Hash = new Sha256Hash(source, bytes);
		System.out.println(sha256Hash.toString());
		System.out.println(sha256Hash.toBase64());
		System.out.println(sha256Hash.toHex());
		md5Hash = new Md5Hash(source, "salt",1); 
		System.out.println(md5Hash.toString());
	}
	@Test
	public void token() {
		String[] passwords= {"zs123"};
		for(String str:passwords) {
			ByteSource bytes = ByteSource.Util.bytes("salt");
			Md5Hash md5Hash = new Md5Hash(str, bytes); 
			System.out.println(md5Hash.toString());
		}
		
	}
	@Test
	public void test11() {
		String source="zs123";
		ByteSource bytes = ByteSource.Util.bytes("salt");
		String str = MySimpleHash.get(source, bytes, 1);
		System.out.println(str);//af3dbe92220332eacee19be7aed6f503
	}	
	
}
