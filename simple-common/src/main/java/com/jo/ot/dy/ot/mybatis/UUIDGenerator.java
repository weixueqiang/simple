package com.jo.ot.dy.ot.mybatis;

import java.io.Serializable;

public class UUIDGenerator implements IdGenerator {

	@Override
	public Serializable generator() {
		return new ObjectId().toString();
	}

}
