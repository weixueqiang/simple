package com.jo.dy.ot.stream2;

import org.junit.Test;

public class InvotionFunction {

	public void simpleDo(FunctionalInteface fac) {
		fac.work();
	}

	@Test
	public void test() {
		this.simpleDo(new FuctionImpl());
		this.simpleDo(new FuctionImpl2());
	}

}
