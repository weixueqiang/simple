package com.jo.dy.ot.stream2;

@FunctionalInterface
public interface FunctionalInteface {

	void work();

	default FunctionalInteface get(FunctionalInteface face) {
		return () -> face.work();
	}

}
