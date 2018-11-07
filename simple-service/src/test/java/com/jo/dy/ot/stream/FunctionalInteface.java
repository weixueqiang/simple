package com.jo.dy.ot.stream;

@FunctionalInterface
public interface FunctionalInteface {

	void work();

	default FunctionalInteface get(FunctionalInteface face) {
		return () -> face.work();
	}

}
