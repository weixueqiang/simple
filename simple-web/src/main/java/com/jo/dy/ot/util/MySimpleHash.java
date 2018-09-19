package com.jo.dy.ot.util;

import org.apache.shiro.codec.CodecException;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
@Deprecated
public class MySimpleHash extends SimpleHash {

	private static final long serialVersionUID = 1L;

	public static final String ALGORITHM_NAME = "MD5";
	private static final int DEFAULT_ITERATIONS = 1;
	private int iterations;

	private static MySimpleHash mySimpleHash = new MySimpleHash(ALGORITHM_NAME);

	private MySimpleHash(String algorithmName) {
		super(algorithmName);
	}

	public void SimpleHash(Object source, Object salt, int hashIterations)
			throws CodecException, UnknownAlgorithmException {

		this.iterations = Math.max(DEFAULT_ITERATIONS, hashIterations);
		ByteSource saltBytes = null;
		if (salt != null) {
			saltBytes = convertSaltToBytes(salt);

		}
		ByteSource sourceBytes = convertSourceToBytes(source);
		hash(sourceBytes, saltBytes, hashIterations);
	}

	private void hash(ByteSource source, ByteSource salt, int hashIterations)
			throws CodecException, UnknownAlgorithmException {
		byte[] saltBytes = salt != null ? salt.getBytes() : null;
		byte[] hashedBytes = hash(source.getBytes(), saltBytes, hashIterations);
		setBytes(hashedBytes);
	}
	
	public static String get(Object source, Object salt, int hashIterations) {
		mySimpleHash.SimpleHash(source, salt, hashIterations);
		return mySimpleHash.toString();
	}
	
	
}
