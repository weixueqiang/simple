package com.jo.dy.ot.model;

import java.io.Serializable;

public interface DoWork extends Serializable, Runnable {

	void submit();

}
