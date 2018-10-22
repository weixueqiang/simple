package com.jo.dy.ot.redis;

import java.util.Set;

import com.jo.dy.ot.model.DoWork;
import com.jo.dy.ot.model.PushModel;

public class SimpleDoWorkImpl implements DoWork {

	private static final long serialVersionUID = 1L;

	private PushModel pushModel;

	@Override
	public void submit() {
		Set<String> receiverSet = pushModel.getReceiverSet();
		for (String str : receiverSet) {
			System.out.println(String.format("信息接收者是:%s", str));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("SimpleDoWorkImpl ....dowork...over!");
	}

	public PushModel getPushModel() {
		return pushModel;
	}

	public void setPushModel(PushModel pushModel) {
		this.pushModel = pushModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void run() {
		submit();
	}

}
